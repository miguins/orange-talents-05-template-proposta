package br.com.zupedu.lucasmiguins.proposta.enumeration;

public enum EnumResultadoSolicitacaoAnalise {

    COM_RESTRICAO{
        public EnumEstadoProposta ajusta() {
            return EnumEstadoProposta.NAO_ELEGIVEL;
        }
    },

    SEM_RESTRICAO{
        public EnumEstadoProposta ajusta() {
            return EnumEstadoProposta.ELEGIVEL;
        }
    };


    public abstract EnumEstadoProposta ajusta();
}
