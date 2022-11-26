package com.example.howtowrite;

import javafx.application.Platform;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sample {
    public static void main(String[] args) throws IOException {
        // iTunesのミュージックファイル(.m4a)のリスト
        List<Path> musicFiles = new ArrayList<>();

        // 1~10の乱数の生成
        Path start = Paths.get("F:\\OneDrive\\music\\mp3\\変換");

        // ファイルの探索
        // サブフォルダを含めてミュージックファイルだけ取り出す
        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".mp3")) {
                    musicFiles.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

        };

        Files.walkFileTree(start, visitor);

        // ミュージックファイルがなければ終了
        if (musicFiles.isEmpty()) {
            Platform.exit();
        }

        // 再生する曲を決める(ランダム)
        int i = new Random().nextInt(musicFiles.size());
        Path path = musicFiles.get(i);
        System.out.println(i);
        System.out.println(path.getFileName());
    }
}
