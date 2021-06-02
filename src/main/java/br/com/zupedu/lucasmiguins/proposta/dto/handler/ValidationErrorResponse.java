package br.com.zupedu.lucasmiguins.proposta.dto.handler;

public class ValidationErrorResponse {

    private String field;
    private String error;

    public ValidationErrorResponse(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
