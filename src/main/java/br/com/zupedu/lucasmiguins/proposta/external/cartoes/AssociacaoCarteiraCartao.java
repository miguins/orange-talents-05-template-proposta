package br.com.zupedu.lucasmiguins.proposta.external.cartoes;

import br.com.zupedu.lucasmiguins.proposta.dto.carteira.CarteiraCartaoResponse;
import br.com.zupedu.lucasmiguins.proposta.dto.carteira.NovaCarteiraCartaoRequest;
import br.com.zupedu.lucasmiguins.proposta.exception.ApiErrorException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AssociacaoCarteiraCartao {

    @Autowired
    ApiCartao apiCartao;

    public CarteiraCartaoResponse gerar(String idCartao, NovaCarteiraCartaoRequest request) {

        try {
            return apiCartao.carteira(idCartao, request);
        } catch (FeignException.FeignClientException | FeignException.FeignServerException error) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível associar o cartão a carteira.");
        }
    }
}
