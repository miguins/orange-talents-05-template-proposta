package br.com.zupedu.lucasmiguins.proposta.external.analise;

import br.com.zupedu.lucasmiguins.proposta.exception.ApiErrorException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class AnaliseProposta {

    @Autowired
    SolicitacaoAnalise solicitacao;

    public AnaliseResponse analisa(@Valid AnaliseRequest proposta) {

        try {

            return solicitacao.analisa(proposta);
        }catch (FeignException.FeignClientException | FeignException.FeignServerException error) {
            throw new ApiErrorException(HttpStatus.valueOf(error.status()), error.getLocalizedMessage());
        }
    }
}
