package br.com.zupedu.lucasmiguins.proposta.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    // Número do cartão
    private String id;

    @NotBlank
    private String titular;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotNull
    private Integer limite;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    private Vencimento vencimento;

    @NotNull
    @OneToOne
    private Proposta proposta;

    @Deprecated
    public Cartao() { }

    public Cartao(String id, String titular, LocalDateTime emitidoEm, Integer limite, Vencimento vencimento, @Valid Proposta proposta) {
        this.id = id;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
        this.proposta = proposta;

        this.vencimento = vencimento;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public Integer getLimite() {
        return limite;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }
}
