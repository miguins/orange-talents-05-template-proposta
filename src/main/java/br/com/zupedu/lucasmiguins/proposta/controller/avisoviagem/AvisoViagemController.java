package br.com.zupedu.lucasmiguins.proposta.controller.avisoviagem;

import br.com.zupedu.lucasmiguins.proposta.dto.avisoviagem.NovoAvisoViagemRequest;
import br.com.zupedu.lucasmiguins.proposta.model.AvisoViagem;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import br.com.zupedu.lucasmiguins.proposta.util.HttpRequestUtil;
import br.com.zupedu.lucasmiguins.proposta.util.persistence.ExecutorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/viagens")
public class AvisoViagemController {

    @Autowired
    ExecutorTransacao executorTransacao;

    @PostMapping("/cartao/{id}")
    public ResponseEntity<?> cadastro(@PathVariable("id") String idCartao, HttpServletRequest servletRequest,
                                    @RequestBody @Valid NovoAvisoViagemRequest request) {

        Optional<Cartao> cartao = executorTransacao.getManager()
                .createQuery("from Cartao c where c.id = :pId")
                .setParameter("pId", idCartao)
                .getResultStream().findFirst();

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String clientIpAddress = HttpRequestUtil.getClientIpAddress(servletRequest);
        String clientUserAgent = HttpRequestUtil.getClientUserAgent(servletRequest);

        AvisoViagem novoAvisoViagem = request.toModel(cartao.get(), clientIpAddress, clientUserAgent);
        executorTransacao.salvaEComita(novoAvisoViagem);

        return ResponseEntity.ok().build();
    }
}
