package br.com.zupedu.lucasmiguins.proposta.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataInicio;

    @NotNull
    private LocalDateTime dataCriacao;

    @NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgenteCliente;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() { }

    public AvisoViagem(String destino, LocalDate dataTermino, LocalDate dataInicio, String ipCliente, String userAgenteCliente, Cartao cartao) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.dataInicio = dataInicio;
        this.ipCliente = ipCliente;
        this.userAgenteCliente = userAgenteCliente;
        this.cartao = cartao;

        this.dataCriacao = LocalDateTime.now();
    }
}
