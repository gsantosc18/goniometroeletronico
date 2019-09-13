package modelo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMD5 {

    public static String encrypt(String word) throws NoSuchAlgorithmException{
       MessageDigest m = MessageDigest.getInstance("MD5");
       m.update(word.getBytes(),0,word.length());
       return new BigInteger(1,m.digest()).toString(16);
    }
    
}
