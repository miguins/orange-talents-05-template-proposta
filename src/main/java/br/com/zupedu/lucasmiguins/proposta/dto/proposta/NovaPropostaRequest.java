package br.com.zupedu.lucasmiguins.proposta.dto.proposta;

import br.com.zupedu.lucasmiguins.proposta.model.proposta.Proposta;
import br.com.zupedu.lucasmiguins.proposta.util.validation.annotation.CPFOrCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class NovaPropostaRequest {

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
    @CPFOrCNPJ
    private String documento;

    public NovaPropostaRequest(String nome, String email, String endereco, BigDecimal salarioBruto, String documento) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
        this.documento = documento;
    }

    public Proposta toModel() {
        return new Proposta(this.nome,  this.email, this.endereco, this.salarioBruto, this.documento);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    public String getDocumento() {
        return documento;
    }
}
