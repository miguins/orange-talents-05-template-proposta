package br.com.zupedu.lucasmiguins.proposta.dto.biometria;

import br.com.zupedu.lucasmiguins.proposta.model.Biometria;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import br.com.zupedu.lucasmiguins.proposta.util.validation.annotation.Base64;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

    @NotBlank
    @Base64
    private String fingerprint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaBiometriaRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(this.fingerprint, cartao);
    }
}
