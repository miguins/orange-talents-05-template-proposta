package br.com.zupedu.lucasmiguins.proposta.external.analise;

import br.com.zupedu.lucasmiguins.proposta.dto.analise.AnaliseRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.analise.AnaliseResponse;
import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumResultadoSolicitacaoAnalise;
import br.com.zupedu.lucasmiguins.proposta.model.Proposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class AnaliseProposta {

    @Autowired
    ApiSolicitacaoAnalise solicitacao;

    public AnaliseResponse analisa(@Valid Proposta proposta) {
        AnaliseResponse result;

        try {

            result = solicitacao.analisa(new AnaliseRequest(proposta.getId(), proposta.getNome(), proposta.getDocumento()));
        } catch (FeignException.FeignClientException | FeignException.FeignServerException error) {
            result = new AnaliseResponse(proposta.getNome(), proposta.getDocumento(), proposta.getId(), EnumResultadoSolicitacaoAnalise.COM_RESTRICAO);
        }

        return result;
    }
}
