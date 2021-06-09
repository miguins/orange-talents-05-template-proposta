package br.com.zupedu.lucasmiguins.proposta.external.cartoes;

import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoBloqueioRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoBloqueioResponse;
import br.com.zupedu.lucasmiguins.proposta.exception.ApiErrorException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CartaoBloqueio {

    @Autowired
    ApiCartao apiCartao;

    public CartaoBloqueioResponse notificarBloqueio(String idCartao) {

        try {
            return apiCartao.bloqueio(idCartao, new CartaoBloqueioRequest("api-proposta"));
        } catch (FeignException.FeignClientException | FeignException.FeignServerException error) {
            throw new ApiErrorException(HttpStatus.valueOf(error.status()), error.getLocalizedMessage());
        }
    }
}
