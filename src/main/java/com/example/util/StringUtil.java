package com.example.util;

import java.nio.charset.Charset;

public class StringUtil {

    /**
     * 文字列を結合する
     */
    public static String concat(String... strings) {
        return String.join("", strings);
    }

    /**
     * キャメルケースをスネークケースに変換する
     */
    public static String camelToSnake(String camelCaseStr) {
        StringBuilder sb = new StringBuilder();
        for (char c : camelCaseStr.toCharArray()) {
            if (isUpperCaseChar(c)) {
                if (sb.length() > 0) {
                    sb.append('_');
                }
                sb.append(toLowerCaseChar(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * スネークケースをキャメルケースに変換する
     */
    public static String snakeToCamel(String snakeCaseStr) {
        StringBuilder sb = new StringBuilder();
        boolean isNextUpper = false;
        for (char c : snakeCaseStr.toCharArray()) {
            if (c == '_') {
                isNextUpper = true;
                continue;
            }
            if (isNextUpper) {
                sb.append(toUpperCaseChar(c));
                isNextUpper = false;
            } else {
                sb.append(sb.length() == 0 ? toUpperCaseChar(c) : toLowerCaseChar(c));
            }
        }
        return sb.toString();
    }

    /**
     * char型を大文字に変換する
     */
    public static char toUpperCaseChar(char c) {
        return isLowerCaseChar(c) ? (char) (c & 0x5f) : c;
    }

    /**
     * char型を小文字に変換する
     */
    public static char toLowerCaseChar(char c) {
        return isUpperCaseChar(c) ? (char) (c | 0x20) : c;
    }

    /**
     * char型が大文字アルファベットか判定する
     */
    public static boolean isUpperCaseChar(char c) {
        return 0x41 <= c && c <= 0x5A;
    }

    /**
     * char型が小文字アルファベットか判定する
     */
    public static boolean isLowerCaseChar(char c) {
        return 0x61 <= c && c <= 0x7A;
    }

    /**
     * 文字コードを変更する
     */
    public static String changeCharset(String str, Charset srcCharset, Charset destCharset) {
        return new String(str.getBytes(srcCharset), destCharset);
    }

}
