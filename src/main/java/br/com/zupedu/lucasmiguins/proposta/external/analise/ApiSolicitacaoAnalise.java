package br.com.zupedu.lucasmiguins.proposta.external.analise;

import br.com.zupedu.lucasmiguins.proposta.dto.analise.AnaliseRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.analise.AnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${analises.host}", name = "analises")
public interface ApiSolicitacaoAnalise {

    @PostMapping
    AnaliseResponse analisa(AnaliseRequest proposta);
}