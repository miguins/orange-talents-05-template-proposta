package br.com.zupedu.lucasmiguins.proposta.controller.proposta;

import br.com.zupedu.lucasmiguins.proposta.dto.exception.ErrorResponse;
import br.com.zupedu.lucasmiguins.proposta.dto.proposta.NovaPropostaRequest;
import br.com.zupedu.lucasmiguins.proposta.external.analise.AnaliseProposta;
import br.com.zupedu.lucasmiguins.proposta.dto.analise.AnaliseResponse;
import br.com.zupedu.lucasmiguins.proposta.model.Proposta;
import br.com.zupedu.lucasmiguins.proposta.util.persistence.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaController {

    @Autowired
    ExecutorTransacao executorTransacao;

    @Autowired
    AnaliseProposta analiseProposta;

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uricb) {

        if (request.existeDocumento(executorTransacao.getManager())) {
            Collection<String> mensagens = new ArrayList<>();
            mensagens.add("Já existe uma Proposta com o documento informado!");
            ErrorResponse erro = new ErrorResponse(mensagens);

            return ResponseEntity.unprocessableEntity().body(erro);
        }

        Proposta novaProposta = request.toModel();
        executorTransacao.salvaEComita(novaProposta);

        AnaliseResponse response = this.analiseProposta.analisa(novaProposta);
        novaProposta.ajustaEstado(response.getResultadoSolicitacao());
        executorTransacao.atualizaEComita(novaProposta);

        URI uri = uricb.path("/api/v1/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
