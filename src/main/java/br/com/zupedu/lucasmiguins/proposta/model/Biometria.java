package br.com.zupedu.lucasmiguins.proposta.model;

import br.com.zupedu.lucasmiguins.proposta.util.validation.annotation.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Base64
    @Lob // Aceitar tamanho grande de string
    private String fingerprint;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    private LocalDateTime dataCriacao;

    @Deprecated
    public Biometria() { }

    public Biometria(String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;

        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
}
