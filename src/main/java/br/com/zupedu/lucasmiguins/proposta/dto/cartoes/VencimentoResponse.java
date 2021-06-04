package br.com.zupedu.lucasmiguins.proposta.dto.cartoes;

import br.com.zupedu.lucasmiguins.proposta.model.Vencimento;

import java.time.LocalDateTime;

public class VencimentoResponse {

    private String id;

    private Integer dia;

    private LocalDateTime dataDeCriacao;

    public Vencimento toModel() {
        return new Vencimento(id, dia, dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
