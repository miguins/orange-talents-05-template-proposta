package br.com.zupedu.lucasmiguins.proposta.controller.proposta;

import br.com.zupedu.lucasmiguins.proposta.dto.exception.ErrorResponse;
import br.com.zupedu.lucasmiguins.proposta.dto.proposta.NovaPropostaRequest;
import br.com.zupedu.lucasmiguins.proposta.model.proposta.Proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaController {

    @Autowired
    EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uricb) {

        if (!request.existeDocumento(em)) {
            Proposta novaProposta = request.toModel();
            em.persist(novaProposta);

            URI uri = uricb.path("/api/v1/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();

            return ResponseEntity.created(uri).build();
        } else {
            Collection<String> mensagens = new ArrayList<>();
            mensagens.add("Já existe uma Proposta com o documento informado!");
            ErrorResponse erro = new ErrorResponse(mensagens);

            return ResponseEntity.unprocessableEntity().body(erro);
        }
    }
}
