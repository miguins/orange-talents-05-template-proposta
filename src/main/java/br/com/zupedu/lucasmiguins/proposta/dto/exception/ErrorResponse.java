package br.com.zupedu.lucasmiguins.proposta.dto.exception;

import java.util.Collection;

public class ErrorResponse {

    private final Collection<String> mensagens;

    public ErrorResponse(Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}
