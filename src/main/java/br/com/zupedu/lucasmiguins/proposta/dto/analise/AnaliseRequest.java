package br.com.zupedu.lucasmiguins.proposta.dto.analise;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnaliseRequest {

    @NotNull
    private Long idProposta;

    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    public AnaliseRequest(Long idProposta, String nome, String documento) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
