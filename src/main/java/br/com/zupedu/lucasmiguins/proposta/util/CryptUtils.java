package br.com.zupedu.lucasmiguins.proposta.util;

import org.springframework.security.crypto.encrypt.Encryptors;

public class CryptUtils {

    private static final String PASSWORD = "senhasecretacrypt";
    private static final String SALT = "73616C747365637265746F";

    public static String encrypt(String value) {
        return Encryptors.delux(PASSWORD, SALT).encrypt(value);
    }

    public static String decrypt(String cryptedValue) {
        return Encryptors.delux(PASSWORD, SALT).decrypt(cryptedValue);
    }
}
