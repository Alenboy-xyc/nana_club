package com.shanzhu.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author shanzhu
 * @description 数据库加密util
 * @create 2024/4/29
 */
public class DruidEncryptUtil {
    private static String publicKey;
    private static String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            publicKey = keyPair[1];
            System.out.println("privateKey:" + privateKey);
            System.out.println("publicKey:" + publicKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
    //加密
    public static String encrypt(String plainText) throws Exception {
        System.out.println("私有key："+ privateKey);
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypt: " + encrypt);
        return encrypt;
    }
    //解密
    public static String decrypt(String plainText) throws Exception {
        System.out.println("公共key："+ publicKey);
        String decrypt = ConfigTools.decrypt(publicKey, plainText);
        System.out.println("decrypt: " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("123456");
        decrypt(encrypt);
    }
}
