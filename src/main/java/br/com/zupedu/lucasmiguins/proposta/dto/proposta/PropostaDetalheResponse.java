package br.com.zupedu.lucasmiguins.proposta.dto.proposta;

import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumEstadoProposta;
import br.com.zupedu.lucasmiguins.proposta.model.Proposta;
import br.com.zupedu.lucasmiguins.proposta.util.CryptUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PropostaDetalheResponse {

    private String nome;

    private String email;

    private String endereco;

    private BigDecimal salarioBruto;

    private String documento;

    private EnumEstadoProposta estadoProposta;

    private LocalDateTime dataCriacao;

    private PropostaCartaoDetalheResponse cartao;


    public PropostaDetalheResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salarioBruto = proposta.getSalarioBruto();
        this.documento = CryptUtils.decrypt(proposta.getDocumento());
        this.estadoProposta = proposta.getEstadoProposta();
        this.dataCriacao = proposta.getDataCriacao();

        if (proposta.possuiCartao()) {
            this.cartao = new PropostaCartaoDetalheResponse(proposta.getCartao());
        }
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

    public EnumEstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public PropostaCartaoDetalheResponse getCartao() {
        return cartao;
    }
}
