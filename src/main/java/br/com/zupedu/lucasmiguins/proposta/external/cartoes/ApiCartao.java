package br.com.zupedu.lucasmiguins.proposta.external.cartoes;

import br.com.zupedu.lucasmiguins.proposta.dto.carteira.CarteiraCartaoResponse;
import br.com.zupedu.lucasmiguins.proposta.dto.carteira.NovaCarteiraCartaoRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${cartoes.host}", name = "Cartoes")
public interface ApiCartao {

    @PostMapping
    CartaoResponse cadastro(CartaoRequest request);

    @PostMapping("/{id}/bloqueios")
    ApiCartaoDefaultResponse bloqueio(@PathVariable("id") String idCartao, CartaoBloqueioRequest request);

    @PostMapping("/{id}/avisos")
    ApiCartaoDefaultResponse aviso(@PathVariable("id") String idCartao, NotificacaoAvisoViagemRequest request);

    @PostMapping("/{id}/carteiras")
    CarteiraCartaoResponse carteira(@PathVariable("id") String idCartao, NovaCarteiraCartaoRequest request);
}
