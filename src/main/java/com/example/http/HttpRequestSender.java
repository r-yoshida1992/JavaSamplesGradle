package com.example.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestSender {
    /**
     * URLを指定してGetリクエストを送信する
     *
     * @param urlStr URL文字列
     * @return Html
     */
    public static String sendGet(String urlStr) throws IOException, InterruptedException {
        int statusCode;
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(HttpRequest
                        .newBuilder(URI.create(urlStr))
                        .build(), HttpResponse.BodyHandlers.ofString());
        if ((statusCode = response.statusCode()) == HttpURLConnection.HTTP_OK) {
            return response.body();
        } else {
            throw new RuntimeException(String.format("http request failed. status code = %d", statusCode));
        }
    }
}