package br.com.zupedu.lucasmiguins.proposta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    @NotBlank
    private String id;

    @NotNull
    private Integer dia;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento() { }

    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
