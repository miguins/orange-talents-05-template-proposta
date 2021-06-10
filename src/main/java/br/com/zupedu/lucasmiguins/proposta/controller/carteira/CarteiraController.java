package br.com.zupedu.lucasmiguins.proposta.controller.carteira;

import br.com.zupedu.lucasmiguins.proposta.dto.carteira.CarteiraCartaoResponse;
import br.com.zupedu.lucasmiguins.proposta.dto.carteira.NovaCarteiraCartaoRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.exception.ErrorResponse;
import br.com.zupedu.lucasmiguins.proposta.external.cartoes.AssociacaoCarteiraCartao;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import br.com.zupedu.lucasmiguins.proposta.model.Carteira;
import br.com.zupedu.lucasmiguins.proposta.util.persistence.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carteiras")
public class CarteiraController {

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    AssociacaoCarteiraCartao associacaoCarteiraCartao;

    @PostMapping("/cartao/{id}")
    public ResponseEntity<?> cadastro(@PathVariable("id") String idCartao,
                                      UriComponentsBuilder uricb,
                                      @RequestBody @Valid NovaCarteiraCartaoRequest request) {

        Optional<Cartao> cartao = executorTransacao.getManager()
                .createQuery("from Cartao c where c.id = :pId")
                .setParameter("pId", idCartao)
                .getResultStream().findFirst();

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (cartao.get().jaPossuiCartaoParaOTipo(request.getTipoCarteira())) {
            Collection<String> mensagens = new ArrayList<>();
            mensagens.add("O cartão informado já está vinculado a esta carteira.");
            ErrorResponse erro = new ErrorResponse(mensagens);

            return ResponseEntity.unprocessableEntity().body(erro);
        }

        CarteiraCartaoResponse associacaoResponse = associacaoCarteiraCartao.gerar(idCartao, request);

        Carteira novaCarteira = request.toModel(cartao.get(), associacaoResponse.getId());
        executorTransacao.salvaEComita(novaCarteira);

        URI uri = uricb.path("/api/v1/carteiras/{id}").buildAndExpand(novaCarteira.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
