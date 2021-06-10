package br.com.zupedu.lucasmiguins.proposta.model;

import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumTipoCarteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumTipoCarteira tipoCarteira;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @NotBlank
    private String associacaoId;

    @Deprecated
    public Carteira() { }

    public Carteira(EnumTipoCarteira tipoCarteira, String email, Cartao cartao, String associacaoId) {
        this.tipoCarteira = tipoCarteira;
        this.email = email;
        this.cartao = cartao;
        this.associacaoId = associacaoId;
    }

    public Long getId() {
        return id;
    }

    public EnumTipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }
}
