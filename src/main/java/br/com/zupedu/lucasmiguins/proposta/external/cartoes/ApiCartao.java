package br.com.zupedu.lucasmiguins.proposta.external.cartoes;

import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoBloqueioRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoBloqueioResponse;
import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${cartoes.host}", name = "Cartoes")
public interface ApiCartao {

    @PostMapping
    CartaoResponse cadastro(CartaoRequest request);

    @PostMapping("/{id}/bloqueios")
    CartaoBloqueioResponse bloqueio(@PathVariable("id") String idCartao, CartaoBloqueioRequest request);
}
