package br.com.zupedu.lucasmiguins.proposta.external.cartoes;

import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.ApiCartaoDefaultResponse;
import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.NotificacaoAvisoViagemRequest;
import br.com.zupedu.lucasmiguins.proposta.exception.ApiErrorException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NotificacaoViagem {

    @Autowired
    ApiCartao apiCartao;

    public ApiCartaoDefaultResponse notificarViagem(String idCartao, String destino, LocalDate dataTerminoViagem) {

        try {
            return apiCartao.aviso(idCartao, new NotificacaoAvisoViagemRequest(destino, dataTerminoViagem));
        } catch (FeignException.FeignClientException | FeignException.FeignServerException error) {
            throw new ApiErrorException(HttpStatus.valueOf(error.status()), error.getLocalizedMessage());
        }
    }
}
