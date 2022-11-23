package com.example.swing;

import javax.swing.*;
import java.awt.*;

public class SwingSample {
    public static void main(String[] args) {
        JFrame f = new JFrame("テスト");

        // サイズ設定
        f.setSize(1000,1000);

        // 閉じたらアプリ終了
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // コンテナの取得
        Container container = f.getContentPane();

        // ボタンの作成
        JButton button = new JButton("ボタン");

        container.add(button, BorderLayout.CENTER);
        button = new JButton("ボタン");
        container.add(button, BorderLayout.SOUTH);
        button = new JButton("ボタン");
        container.add(button, BorderLayout.NORTH);


        f.setVisible(true);
    }
}
