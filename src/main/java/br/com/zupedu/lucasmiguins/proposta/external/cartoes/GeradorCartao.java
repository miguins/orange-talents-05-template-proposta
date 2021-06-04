package br.com.zupedu.lucasmiguins.proposta.external.cartoes;

import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoRequest;
import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.CartaoResponse;
import br.com.zupedu.lucasmiguins.proposta.exception.ApiErrorException;
import br.com.zupedu.lucasmiguins.proposta.model.Proposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class GeradorCartao {

    @Autowired
    ApiCartao apiCartao;

    public CartaoResponse gerar(Proposta proposta) {

        try {
            return apiCartao.cadastro(new CartaoRequest(proposta.getId(), proposta.getNome(), proposta.getDocumento()));
        } catch (FeignException.FeignClientException | FeignException.FeignServerException error) {
            throw new ApiErrorException(HttpStatus.valueOf(error.status()), error.getLocalizedMessage());
        }
    }
}
