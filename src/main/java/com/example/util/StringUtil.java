package com.example.util;

import java.nio.charset.Charset;
import java.util.function.BiFunction;

import static com.example.constants.Charsets.SJIS;
import static com.example.constants.Charsets.UTF_8;

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
     * スネークケースからキャメルケースの文字列に変換する
     */
    public static String snakeToCamel(String snake, boolean isFirstCharLower) {
        // スネークケースでない場合は小文字にして返却
        if (!snake.contains("_")) {
            return snake.toLowerCase();
        }
        String[] splitWord = snake.split("_");
        StringBuilder camel = new StringBuilder();
        for (String s : splitWord) {
            // 区切り文字の連結などの場合、length = 0となる為ため、無視。
            if (s.length() == 0) {
                continue;
            }
            camel.append(camel.length() == 0 && isFirstCharLower
                    ? s.toLowerCase()
                    : concat(s.substring(0, 1).toUpperCase(), s.substring(1).toLowerCase()));
        }
        return camel.toString();
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

    /**
     * sjisに変換
     */
    public static String convertSJis(String str, Charset srcCharset) {
        return changeCharset(str, srcCharset, SJIS);
    }

    /**
     * utf-8に変換
     */
    public static String convertUtf8(String str, Charset srcCharset) {
        return changeCharset(str, srcCharset, UTF_8);
    }

}
