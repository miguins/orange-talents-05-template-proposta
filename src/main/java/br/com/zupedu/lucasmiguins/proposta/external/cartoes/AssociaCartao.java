package br.com.zupedu.lucasmiguins.proposta.external.cartoes;

import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoResponse;
import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumEstadoProposta;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import br.com.zupedu.lucasmiguins.proposta.model.Proposta;
import br.com.zupedu.lucasmiguins.proposta.util.persistence.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociaCartao {

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    GeradorCartao geradorCartao;

    @Scheduled(fixedDelay=5000)
    public void associar() {

        //noinspection unchecked
        List<Proposta> propostas = executorTransacao.getManager().createQuery("from Proposta p where p.cartao is null and p.estadoProposta = :pEstadoProposta")
                .setParameter("pEstadoProposta", EnumEstadoProposta.ELEGIVEL)
                .getResultList();

        if (!propostas.isEmpty()) {
            propostas.forEach(this::associarCartaoProposta);
        }
    }

    private void associarCartaoProposta(Proposta proposta) {

        CartaoResponse response = geradorCartao.gerar(proposta);

        Cartao cartao = new Cartao(response.getId(), response.getTitular(), response.getEmitidoEm(), response.getLimite(), response.getVencimento().toModel(), proposta);

        proposta.paraCartao(cartao);
        executorTransacao.atualizaEComita(proposta);
    }
}
