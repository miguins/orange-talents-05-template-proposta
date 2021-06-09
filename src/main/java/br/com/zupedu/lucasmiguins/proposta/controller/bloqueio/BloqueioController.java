package br.com.zupedu.lucasmiguins.proposta.controller.bloqueio;

import br.com.zupedu.lucasmiguins.proposta.dto.exception.ErrorResponse;
import br.com.zupedu.lucasmiguins.proposta.external.cartoes.CartaoBloqueio;
import br.com.zupedu.lucasmiguins.proposta.model.Bloqueio;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import br.com.zupedu.lucasmiguins.proposta.util.HttpRequestUtil;
import br.com.zupedu.lucasmiguins.proposta.util.persistence.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bloqueios")
public class BloqueioController {

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    CartaoBloqueio cartaoBloqueio;

    @PostMapping("/cartao/{id}")
    public ResponseEntity<?> cadastro(@PathVariable("id") String idCartao, HttpServletRequest request) {

        HttpRequestUtil.getClientIpAddress(request);

        Optional<Cartao> cartao = executorTransacao.getManager()
                .createQuery("from Cartao c where c.id = :pId")
                .setParameter("pId", idCartao)
                .getResultStream().findFirst();

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (cartao.get().isCartaoBloqueado()) {
            Collection<String> mensagens = new ArrayList<>();
            mensagens.add("O cartão informado já se encontra bloqueado");
            ErrorResponse erro = new ErrorResponse(mensagens);

            return ResponseEntity.unprocessableEntity().body(erro);
        }

        String clientIpAddress = HttpRequestUtil.getClientIpAddress(request);
        String clientUserAgent = HttpRequestUtil.getClientUserAgent(request);

        this.cartaoBloqueio.notificarBloqueio(idCartao);

        Bloqueio novoBloqueio = new Bloqueio(clientIpAddress, clientUserAgent, cartao.get());
        executorTransacao.salvaEComita(novoBloqueio);

        return ResponseEntity.ok().build();
    }
}
