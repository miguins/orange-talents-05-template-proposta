package br.com.zupedu.lucasmiguins.proposta.model.proposta;

import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumResultadoSolicitacaoAnalise;
import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumEstadoProposta;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private EnumEstadoProposta estadoProposta;

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

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void ajustaEstado(EnumResultadoSolicitacaoAnalise estadoAnalise) {
        this.estadoProposta = estadoAnalise.ajusta();
    }
}
