package com.encrypt.cipher;


import java.security.MessageDigest;


public class MainTest {

    public static void main(String[] args) throws Exception {

        //MD5加密
//        System.out.println("MD5加密：" + DigestUtils.md5Hex("abc"));

        //sha加密
//        System.out.println("sha加密：" + DigestUtils.sha512Hex("def"));

        System.out.println(MessageDigestUtil.getByAlgorithm("abc", "RSA"));
        MessageDigestUtil.getMD5("abc");

        //加密
        String str = "abc"; // abc为要加密的字符串
        String b = Base64.encode(str.getBytes());
        System.out.println("base64加密：" + b);

        //解密
        String str2 = "YWJj"; // YWJj为要解密的字符串
        byte[] b2 = Base64.decode(str2);
        System.out.println("base64解密：" + new String(b2));


        String filepath = "D:/tmp/";

        //生成私钥公钥对
        RSAEncrypt.genKeyPair(filepath);


        System.out.println("--------------公钥加密私钥解密过程-------------------");
        String plainText = "公钥加密私钥解密";
        //公钥加密过程  
        byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), plainText.getBytes());
        String cipher = Base64.encode(cipherData);
        //私钥解密过程  
        byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), Base64.decode(cipher));
        String restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);
        System.out.println();

        System.out.println("--------------私钥加密公钥解密过程-------------------");
        plainText = "私钥加密公钥解密";
        //私钥加密过程  
        cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), plainText.getBytes());
        cipher = Base64.encode(cipherData);
        //公钥解密过程  
        res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), Base64.decode(cipher));
        restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("加密：" + cipher);
        System.out.println("解密：" + restr);
        System.out.println();

        System.out.println("---------------私钥签名过程------------------");
        String content = "这是用于签名的原始数据";
        String signstr = RSASignature.sign(content, RSAEncrypt.loadPrivateKeyByFile(filepath));
        System.out.println("签名原串：" + content);
        System.out.println("签名串：" + signstr);
        System.out.println();

        System.out.println("---------------公钥校验签名------------------");
        System.out.println("签名原串：" + content);
        System.out.println("签名串：" + signstr);

        System.out.println("验签结果：" + RSASignature.doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));
        System.out.println();

    }
}  