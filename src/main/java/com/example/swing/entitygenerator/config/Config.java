package com.example.swing.entitygenerator.config;

import com.example.json.JsonConverter;
import lombok.Getter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Getter
public class Config {
    private String url;
    private String user;
    private String password;

    public static Config getInstance() {
        try {
            return JsonConverter.convertToObject(Files.lines(Paths.get("src/main/resources/setting.json"), StandardCharsets.UTF_8)
                    .collect(Collectors.joining(System.lineSeparator())), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("setting.jsonが不正です。");
        }
    }

}
