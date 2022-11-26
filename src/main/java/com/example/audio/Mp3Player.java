package com.example.audio;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/**
 * 起動時に以下の設定をvmオプションに追加する
 * 「--module-path {javafx-sdk/lib} --add-modules javafx.controls,javafx.fxml,javafx.media」
 */
public class Mp3Player extends Application {

    MediaPlayer mediaPlayer;
    private String fileName;

    public static void main(String[] args) {
        Mp3Player player = new Mp3Player();
        player.setFileName("C:\\Users\\Administrator\\OneDrive\\music\\mp3\\0MUSIC0SOUL'd OUT0Single Collection006 1,000,000 MONSTERS ATTACK.mp3");
        player.playMusic();

    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void playMusic() {
        launch(fileName);
    }

    /**
     * mp3ファイルの再生
     * @param primaryStage Stage
     */
    @Override
    public void start(Stage primaryStage) {
        mediaPlayer = new MediaPlayer(new Media(new File(getParameters().getRaw().get(0)).toURI().toString()));
        mediaPlayer.play();
    }
}
