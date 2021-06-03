package br.com.zupedu.lucasmiguins.proposta.external.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${analises.host}", name = "analises")
public interface SolicitacaoAnalise {

    @PostMapping
    AnaliseResponse analisa(AnaliseRequest proposta);
}