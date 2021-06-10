package br.com.zupedu.lucasmiguins.proposta.model;

import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumStatusBloqueio;
import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumTipoCarteira;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<AvisoViagem> viagens = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<Carteira> carteiras = new ArrayList<>();

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

    public boolean isCartaoBloqueado() {
       return this.bloqueios.stream().anyMatch(bloqueio -> bloqueio.getStatusBloqueio().equals(EnumStatusBloqueio.BLOQUEADO));
    }

    public boolean jaPossuiCartaoParaOTipo(EnumTipoCarteira tipoCarteira) {
        return this.carteiras.stream().anyMatch(carteira -> carteira.getTipoCarteira().equals(tipoCarteira));
    }
}
