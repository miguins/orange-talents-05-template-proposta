package br.com.zupedu.lucasmiguins.proposta.model;

import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumStatusBloqueio;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgenteCliente;

    @NotNull
    private LocalDateTime dataCriacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumStatusBloqueio statusBloqueio;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() { }

    public Bloqueio(String ipCliente, String userAgenteCliente, Cartao cartao) {
        this.ipCliente = ipCliente;
        this.userAgenteCliente = userAgenteCliente;
        this.cartao = cartao;

        this.statusBloqueio = EnumStatusBloqueio.BLOQUEADO;
        this.dataCriacao = LocalDateTime.now();
    }

    public EnumStatusBloqueio getStatusBloqueio() {
        return statusBloqueio;
    }
}
