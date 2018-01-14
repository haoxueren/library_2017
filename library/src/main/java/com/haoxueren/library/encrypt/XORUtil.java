package com.haoxueren.library.encrypt;

import android.util.Base64;

/**
 * Created by Haoxueren on 2018/1/14.
 */
public class XORUtil {

    /** 对文本进行异或运算； */
    public static String xor(String text, int factor) {
        if (text == null) return null;
        StringBuilder builder = new StringBuilder();
        char[] chars = text.toCharArray();
        for (char c : chars) {
            char encode = (char) (c ^ factor);
            builder.append(encode);
        }
        return builder.toString();
    }

    /**
     * 先对文本进行异或运算，再进行Base64编码
     */
    public static String encode(String text, int factor) {
        String xor = XORUtil.xor(text, factor);
        return Base64.encodeToString(xor.getBytes(), Base64.DEFAULT);
    }

    /** 先对文本进行Base64解码，再进行异或运算 */
    public static String decode(String text, int factor) {
        byte[] decodeBytes = Base64.decode(text, Base64.DEFAULT);
        String decodeText = new String(decodeBytes);
        return XORUtil.xor(decodeText, factor);
    }
}
