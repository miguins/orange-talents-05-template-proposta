package br.com.zupedu.lucasmiguins.proposta.dto.proposta;

import br.com.zupedu.lucasmiguins.proposta.dto.cartoes.VencimentoResponse;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;

import java.time.LocalDateTime;

public class PropostaCartaoDetalheResponse {

    private String id;

    private String titular;

    private LocalDateTime emitidoEm;

    private Integer limite;

    private VencimentoResponse vencimento;

    public PropostaCartaoDetalheResponse() {
    }

    public PropostaCartaoDetalheResponse(Cartao cartao) {
        this.id = cartao.getId();
        this.titular = cartao.getTitular();
        this.emitidoEm = cartao.getEmitidoEm();
        this.limite = cartao.getLimite();
        this.vencimento = new VencimentoResponse(cartao.getVencimento());
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
}
