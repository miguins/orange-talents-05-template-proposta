package br.com.zupedu.lucasmiguins.proposta.controller.biometria;

import br.com.zupedu.lucasmiguins.proposta.dto.biometria.NovaBiometriaRequest;
import br.com.zupedu.lucasmiguins.proposta.model.Biometria;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import br.com.zupedu.lucasmiguins.proposta.util.persistence.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/biometrias")
public class BiometriaController {

    @Autowired
    ExecutorTransacao executorTransacao;

    @PostMapping("/{id}")
    public ResponseEntity<?> cadastro(@PathVariable("id") String idCartao,
                                      @RequestBody @Valid NovaBiometriaRequest request, UriComponentsBuilder uricb) {

        Optional<Cartao> cartao = executorTransacao.getManager()
                .createQuery("from Cartao c where c.id = :pId")
                .setParameter("pId", idCartao)
                .getResultStream().findFirst();

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Biometria novaBiometria = request.toModel(cartao.get());
        executorTransacao.salvaEComita(novaBiometria);

        URI uri = uricb.path("/api/v1/biometrias/{id}").buildAndExpand(novaBiometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
