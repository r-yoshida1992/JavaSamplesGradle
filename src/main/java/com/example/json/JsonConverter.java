package com.example.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Json変換
 */
public class JsonConverter {

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * jsonをオブジェクトに変換する
     */
    public static <T> T convertToObject(String json, Class<T> responseClass) {
        try {
            return mapper.readValue(json, responseClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * オブジェクトをjsonに変換する
     */
    public static <T> String convertToJson(T dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
