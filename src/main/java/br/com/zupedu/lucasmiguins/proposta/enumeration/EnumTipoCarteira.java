package br.com.zupedu.lucasmiguins.proposta.enumeration;

public enum EnumTipoCarteira {

    PAYPAL("paypal"),
    SAMSUNG_PAY("samsungpay");

    EnumTipoCarteira(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
