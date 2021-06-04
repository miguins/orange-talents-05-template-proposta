package br.com.zupedu.lucasmiguins.proposta.dto.analise;

import br.com.zupedu.lucasmiguins.proposta.enumeration.EnumResultadoSolicitacaoAnalise;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AnaliseResponse {

    private String nome;
    private String documento;
    private Long idProposta;

    @Enumerated(EnumType.STRING)
    private EnumResultadoSolicitacaoAnalise resultadoSolicitacao;

    public AnaliseResponse(String nome, String documento, Long idProposta, EnumResultadoSolicitacaoAnalise resultadoSolicitacao) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public EnumResultadoSolicitacaoAnalise getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
