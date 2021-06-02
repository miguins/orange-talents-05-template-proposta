package br.com.zupedu.lucasmiguins.proposta.model.proposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String endereco;

    @NotNull
    @PositiveOrZero
    private BigDecimal salarioBruto;

    @NotBlank
    private String documento;

    private LocalDateTime dataCriacao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String nome, String email, String endereco, BigDecimal salarioBruto, String documento) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
        this.documento = documento;

        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
}
