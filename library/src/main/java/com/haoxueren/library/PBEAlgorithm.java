package com.haoxueren.library;

import android.util.Base64;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * 对称加密算法：基于口令加密-PBE算法实现
 * 使用 PBEWithSHA1AndRC2 算法进行展示
 * 参考资料：http://blog.csdn.net/u011097980/article/details/50125133
 */
public class PBEAlgorithm {

    /**
     * JAVA6支持以下任意一种算法
     * PBEWITHMD5ANDDES
     * PBEWITHMD5ANDTRIPLEDES
     * PBEWITHSHAANDDESEDE
     * PBEWITHSHA1ANDRC2_40
     * PBKDF2WITHHMACSHA1
     */
    public static final String ALGORITHM = "PBEWithSHA1AndRC2";

    /**
     * 盐初始化
     * 盐长度必须为8字节
     * @return byte[] 盐
     */
    public static byte[] initSalt() {
        // 实例化安全随机数
        SecureRandom random = new SecureRandom();
        // 产出盐
        return random.generateSeed(8);
    }

    /**
     * 转换密钥
     * @param password 密码
     * @return Key 密钥
     */
    private static Key toKey(String password) throws Exception {
        // 密钥彩礼转换
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        // 实例化
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // 生成密钥
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        // 返回生成的密钥
        return secretKey;
    }

    /**
     * 加密
     * @param data     待加密数据
     * @param password 密码
     * @param salt     盐
     * @return byte[] 加密后的数据
     */
    public static byte[] encrypt(byte[] data, String password, byte[] salt, int count) throws Exception {
        // 转换密钥
        Key key = PBEAlgorithm.toKey(password);
        // 实例化PBE参数材料
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, count);
        // 实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param data     待解密数据
     * @param password 密码
     * @param salt     盐
     * @return byte[] 解密数据
     */
    public static byte[] decrypt(byte[] data, String password, byte[] salt, int count) throws Exception {
        // 转换密钥
        Key key = PBEAlgorithm.toKey(password);
        // 实例化PBE参数材料
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, count);
        // 实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 使用PBE算法对数据进行加解密
     * 获取加密后的文本数据
     */
    public static String encryptText(String data, String password, String salt, int count) {
        try {
            byte[] dataBytes = data.getBytes("UTF-8");
            byte[] saltBytes = salt.getBytes("UTF-8");
            byte[] encryptBytes = PBEAlgorithm.encrypt(dataBytes, password, saltBytes, count); // 加密数据
            String base64Text = Base64.encodeToString(encryptBytes, Base64.DEFAULT); // Base64编码
            return base64Text; // 返回密文
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 使用PBE算法对数据进行加解密
     * 获取加密后的文本数据
     */
    public static String decryptText(String data, String password, String salt, int count) throws Exception {
        byte[] saltBytes = salt.getBytes("UTF-8");
        byte[] dataBytes = data.getBytes("UTF-8");
        byte[] base64Bytes = Base64.decode(dataBytes, Base64.DEFAULT); // Base64解码
        byte[] plainBytes = PBEAlgorithm.decrypt(base64Bytes, password, saltBytes, count); // 解密数据
        String plainText = new String(plainBytes); // 转换为文本
        return plainText; // 返回解密后的数据
    }
}
