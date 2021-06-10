package br.com.zupedu.lucasmiguins.proposta.dto.carteira;

import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumTipoCarteira;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import br.com.zupedu.lucasmiguins.proposta.model.Carteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCarteiraCartaoRequest {

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumTipoCarteira tipoCarteira;

    @NotBlank
    @Email
    private String email;

    public NovaCarteiraCartaoRequest(EnumTipoCarteira tipoCarteira, String email) {
        this.tipoCarteira = tipoCarteira;
        this.email = email;
    }

    public EnumTipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

    public Carteira toModel(Cartao cartao, String associacaoId) {
        return new Carteira(tipoCarteira, email, cartao, associacaoId);
    }
}
