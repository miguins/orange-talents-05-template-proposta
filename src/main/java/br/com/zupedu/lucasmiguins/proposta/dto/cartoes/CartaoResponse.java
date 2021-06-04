package br.com.zupedu.lucasmiguins.proposta.dto.cartoes;

import java.time.LocalDateTime;

public class CartaoResponse {

    private String id;

    private String titular;

    private LocalDateTime emitidoEm;

    private Integer limite;

    private VencimentoResponse vencimento;

    private String idProposta;

    public CartaoResponse(String id, String titular, LocalDateTime emitidoEm, Integer limite, VencimentoResponse vencimento, String idProposta) {
        this.id = id;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public Integer getLimite() {
        return limite;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
