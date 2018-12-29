package com.encrypt.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MessageDigestUtil
 * @Description
 * @Author vn0gh5b
 * @Date 2018/12/29 13:24
 **/
public class MessageDigestUtil {

    public static String getMD5(String a) {
        return encrypt(a, "MD5");
    }

    public static String getSHA(String a) {
        return encrypt(a, "SHA");
    }

    public static String getSHA1(String a) {
        return encrypt(a, "SHA-1");
    }

    public static String getSHA256(String a) {
        return encrypt(a, "SHA-256");
    }

    public static String getSHA512(String a) {
        return encrypt(a, "SHA-512");
    }

    public static String getByAlgorithm(String a, String d) {
        return encrypt(a, d);
    }

    private static String encrypt(String a, String d) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(d);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(a.getBytes());
        byte[] s = digest.digest();
        String z = "";
        for (int i = 0; i < s.length; i++) {
            z += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
        }
        return z;
    }

}
