package br.com.zupedu.lucasmiguins.proposta.exception.handler;

import br.com.zupedu.lucasmiguins.proposta.dto.exception.ErrorResponse;
import br.com.zupedu.lucasmiguins.proposta.exception.ApiErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ApiHandlerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildFieldErrorsResponse(fieldErrors));
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ErrorResponse> handleApiErroException(ApiErrorException apiErrorException) {
        Collection<String> mensagens = new ArrayList<>();
        mensagens.add(apiErrorException.getReason());

        ErrorResponse erroPadronizado = new ErrorResponse(mensagens);
        return ResponseEntity.status(apiErrorException.getHttpStatus()).body(erroPadronizado);
    }

    private ErrorResponse buildFieldErrorsResponse(List<FieldError> fieldErrors) {
        Collection<String> mensagens = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
            mensagens.add(message);
        });

        return new ErrorResponse(mensagens);
    }
}
