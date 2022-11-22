package com.example.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class CsvLoader {
    /**
     * 指定したパスのcsvファイルをStringの二次元配列で返却します
     *
     * @param path      読み込む対象のcsvのパス
     * @param delimiter 区切り文字
     * @param charCode  文字コード
     * @return CSVをStringの二次元配列とした値
     */
    public static String[][] load(String path, String delimiter, Charset charCode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path, charCode))) {
            return br.lines().map(l -> l.split(delimiter)).toArray(String[][]::new);
        }
    }

}
