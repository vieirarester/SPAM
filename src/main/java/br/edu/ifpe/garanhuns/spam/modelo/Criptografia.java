/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ester
 */
public class Criptografia {

    private static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    private static char[] codigoHexadecimal(byte[] text) {
        char[] saidaHexa = new char[text.length * 2];
        String textoHexa;

        for (int i = 0; i < text.length; i++) {
            textoHexa = "00" + Integer.toHexString(text[i]);
            textoHexa.toUpperCase().getChars(textoHexa.length() - 2,
                    textoHexa.length(), saidaHexa, i * 2);
        }
        return saidaHexa;
    }

    public static String criptografar(String senha) {
        if (md != null) {
            return new String(codigoHexadecimal(md.digest(senha.getBytes())));
        }
        return null;
    }
}
