package com.example.audio;

import com.example.audio.core.player.MyPlayer;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class AudioPlayer {
    static Player jlPlayer;
    static MyPlayer myPlayer;

    public static void main(String[] args) throws Exception {
//        methodA();
//        methodB();
        methodC();
    }

    public static void methodA() throws Exception {
        String filename = "C:\\Users\\Administrator\\OneDrive\\music\\mp3\\変換\\Pool.mp3";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
        System.setIn(bis);
        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
        }

    }

    public static void methodB() throws Exception {
        String filename = "C:\\Users\\Administrator\\OneDrive\\music\\mp3\\変換\\Pool.mp3";
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
            jlPlayer = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + filename);
            System.out.println(e.getMessage());
        }

        Runnable runnable = () -> {
            try {
                jlPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        new Thread(runnable).start();

        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
            if (jlPlayer != null) jlPlayer.close();
        }

    }

    public static void methodC() throws Exception {
        String filename = "C:\\Users\\Administrator\\OneDrive\\music\\mp3\\変換\\Pool.mp3";
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
            myPlayer = new MyPlayer(bis);
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + filename);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Runnable runnable = () -> {
            try {
                myPlayer.play();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        new Thread(runnable).start();

        Scanner sc = new Scanner(System.in);

        System.out.println("Write stop to stop the music: ");

        if (sc.nextLine().equalsIgnoreCase("stop")) {
            if (myPlayer != null) myPlayer.close();
        }

    }



}

