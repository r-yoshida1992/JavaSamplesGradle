package com.example.game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CliTyping {
    private static final Scanner sc = new Scanner(System.in);

    private static final String[] words = {"hello", "cat", "dog", "heart", "active", "window", "commit", "private", "public"};

    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        double wLength = 0;

        for (String word : words) {
            double bbb = System.currentTimeMillis();
            System.out.printf("\r\u001b[00;36m%s\u001b[00m -> ", word);
            String str = sc.nextLine();
            if (word.equals(str)) {
                double aaa = System.currentTimeMillis();
                System.out.printf("\r\u001b[00;33m%s\u001b[00m\n", "OK");
                System.out.println("kpm -> " + BigDecimal.valueOf(word.length() / ((aaa - bbb) / 1000) * 60).setScale(0, RoundingMode.DOWN));
            } else {
                System.out.printf("\r\u001b[00;31m%s\u001b[00m\n", "NG");
            }
            wLength += word.length();
        }
        double end = System.currentTimeMillis();
        System.out.println("time -> " + ((end - start) / 1000));
        System.out.println("kpm -> " + new BigDecimal(wLength / ((end - start) / 1000) * 60).setScale(0, RoundingMode.DOWN));
        System.out.println("kps -> " + new BigDecimal(wLength / ((end - start) / 1000)).setScale(2, RoundingMode.DOWN));
    }
}
