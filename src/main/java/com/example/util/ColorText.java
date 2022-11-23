package com.example.util;

/**
 * 標準出力に色をつける
 */
public class ColorText {
    private static final String red = "\u001b[00;31m";
    private static final String green = "\u001b[00;32m";
    private static final String yellow = "\u001b[00;33m";
    private static final String purple = "\u001b[00;34m";
    private static final String pink = "\u001b[00;35m";
    private static final String cyan = "\u001b[00;36m";
    private static final String end = "\u001b[00m";

    public static String red(String text) {
        return StringUtil.concat(red, text, end);
    }

    public static String green(String text) {
        return StringUtil.concat(green, text, end);
    }

    public static String yellow(String text) {
        return StringUtil.concat(yellow, text, end);
    }

    public static String purple(String text) {
        return StringUtil.concat(purple, text, end);
    }

    public static String pink(String text) {
        return StringUtil.concat(pink, text, end);
    }

    public static String cyan(String text) {
        return StringUtil.concat(cyan, text, end);
    }
}
