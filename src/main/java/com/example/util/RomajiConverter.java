package com.example.util;

import com.example.enums.RomajiDictionary;

/**
 * 「ひらがな」または「カタカナ」の文字列をローマ字に変換する
 */
public class RomajiConverter {

    public static String convert(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            sb.append(RomajiDictionary.getRomaji(c));
        }
        return sb.toString();
    }

}