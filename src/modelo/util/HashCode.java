/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author myhouse
 */
public class HashCode {
    
    private final Cipher cifraDES;
    private final SecretKeySpec chaveDES;
    
    private final String chaveencriptacao = EncryptMD5.encrypt("passmanager@#encrypt");
    private final String contrasenha = "secunde#@encrypt";
    
    public HashCode() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException {
        cifraDES = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        chaveDES = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
    }
    
    public String codifica(String textopuro) throws Exception {
        cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES,new IvParameterSpec(contrasenha.getBytes("UTF-8")));
        return Base64.getEncoder().encodeToString(cifraDES.doFinal(textopuro.getBytes("UTF-8")));
    }

    public String decodifica(String textoencriptado) throws Exception{
        cifraDES.init(Cipher.DECRYPT_MODE, chaveDES,new IvParameterSpec(contrasenha.getBytes("UTF-8")));
        return new String(cifraDES.doFinal(Base64.getDecoder().decode(textoencriptado)));
    }
}
