package com.example.audio;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Az Music Player - とても単純なミュージックプレーヤー
 *
 * @author Azumi Hirata
 *
 */
public class SamplePlayer extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // iTunesのミュージックファイル(.m4a)のリスト
        List<Path> musicFiles = new ArrayList<>();

        // iTunes Mediaフォルダ
        // Windowsの場合はここ
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

        // MediaPlayer
        // ミュージックの再生・停止・音量調節など
        Media media = new Media(path.toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // MediaView
        // MediaPlayerを画面に埋め込む
        MediaView mediaView = new MediaView(mediaPlayer);

        // Image
        // 適当に五線譜と音符の画像を用意する
        Image image = new Image("https://eternalcollegest.com/wp-content/uploads/2020/04/gorira-3-44.jpg");

        // ImageView
        // Imageを画面に埋め込む
        ImageView imageView = new ImageView(image);

        // 曲名を取得する
        String name = path.getFileName().toString();

        // ラベル : 曲名
        Label label = new Label("Now playing: " + name);
        label.setFont(new Font(24.0));

        // button
        Button btn = new Button("ボタン");
        btn.setMaxWidth(100);
        btn.setMaxHeight(50);
        // イベントの登録（匿名クラスを使用）
        btn.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                mediaPlayer.pause();
                mediaPlayer.setStartTime(new Duration(15000.0));
                mediaPlayer.play();
                // メッセージを表示
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("イベント");
//                alert.setContentText(String.valueOf(mediaPlayer.getCurrentRate()));
//                alert.show();

            }
        });
        // ラベル : 曲名
        Label label2 = new Label("current: ");
        label2.setFont(new Font(24.0));

        // レイアウト
        VBox vbox = new VBox(imageView, label, mediaView, btn);


        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Az Music Player - " + name);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}