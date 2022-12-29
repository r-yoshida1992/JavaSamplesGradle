package com.example.converter;

import com.example.string.StringUtil;

public class ByteConverter {
    public static void main(String[] args) {
        for (int i = 0; i < 13500; i++) {
            System.out.println(new String(char2byte_1((char) i)).equals(new String(char2byte_2((char) i))));
        }
    }

    /**
     * 文字をbyteに変換する
     */
    static byte[] char2byte_1(char c) {
        if (c <= 0x7f) return new byte[]{(byte) c}; // 1 byte
        String binaryStr = Integer.toBinaryString(c);
        if (c <= 0x7ff) {
            // 2 byte
            binaryStr = StringUtil.concat(StringUtil.repeat("0", 11 - binaryStr.length()), binaryStr);
            String byte1 = StringUtil.concat("110", binaryStr.substring(0, 5));
            String byte2 = StringUtil.concat("10", binaryStr.substring(5, 11));
            return new byte[]{
                    (byte) Short.parseShort(byte1, 2),
                    (byte) Short.parseShort(byte2, 2)};
        } else {
            // 3 byte
            binaryStr = StringUtil.concat(StringUtil.repeat("0", 16 - binaryStr.length()), binaryStr);
            String byte1 = StringUtil.concat("1110", binaryStr.substring(0, 4));
            String byte2 = StringUtil.concat("10", binaryStr.substring(4, 10));
            String byte3 = StringUtil.concat("10", binaryStr.substring(10, 16));
            return new byte[]{
                    (byte) Short.parseShort(byte1, 2),
                    (byte) Short.parseShort(byte2, 2),
                    (byte) Short.parseShort(byte3, 2)};
        }
    }

    /**
     * char2byte_1のリファクタリング
     */
    static byte[] char2byte_2(char c) {
        return c <= 0x7f
                ? new byte[]{(byte) c}
                : c <= 0x7ff
                ? new byte[]{(byte) (192 + (c / 64)),
                (byte) (128 + (c % 64))}
                : new byte[]{(byte) (224 + (c / 4096)),
                (byte) (128 + (((c + 2048) / 64) - 32) % 64),
                (byte) (128 + (c % 64))};
    }
}
