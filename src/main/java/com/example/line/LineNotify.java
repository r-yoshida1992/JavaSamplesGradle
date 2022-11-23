package com.example.line;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LineNotify {
    public static void main(String[] args) {
        // FIXME : Line Notifyのマイページから発行したトークンを設定
        LineNotify lineNotify = new LineNotify("fix me...");
        lineNotify.notify("つうちでーす");
        System.out.println("DONE.");
    }

    private final String token;

    public LineNotify(String token) {
        this.token = token;
    }

    public void notify(String message) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://notify-api.line.me/api/notify");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Authorization", "Bearer " + token);
            try (OutputStream os = connection.getOutputStream(); PrintWriter writer = new PrintWriter(os)) {
                writer.append("message=").append(URLEncoder.encode(message, StandardCharsets.UTF_8)).flush();
                try (InputStream is = connection.getInputStream();
                     BufferedReader r = new BufferedReader(new InputStreamReader(is))) {
                    String res = r.lines().collect(Collectors.joining());
                    if (!res.contains("\"message\":\"ok\"")) {
                        System.out.println(res);
                    }
                }
            }        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
