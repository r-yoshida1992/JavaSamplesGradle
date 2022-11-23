package com.example.process;

import com.example.process.dto.ProcessDto;
import com.example.process.dto.ProcessDtoForMac;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ProcessManagerForLinux implements ProcessManager{
    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        // ProcessBuilderのインスタンスを作成
        ProcessBuilder pb = new ProcessBuilder();

        // psコマンドの結果を取得
        Process p = pb.command("ps", "au").start();
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // 全行をstreamで取得
        Stream<String> stream = br.lines();
        // header行を削除
        List<ProcessDtoForMac> dtoList = new ArrayList<>();
        AtomicInteger index = new AtomicInteger();
        stream.forEach(e -> {
            // headerは処理を飛ばす
            if (index.get() != 0) {
                dtoList.add(scanLine(e));
            }
            index.getAndIncrement();
        });
        System.out.println(dtoList);
    }

    // 一行を読み込んでDtoに変換する
    private static ProcessDtoForMac scanLine(String line) {
        ProcessDtoForMac dto = new ProcessDtoForMac();
        IntStream is = line.chars();
        StringBuilder sb = new StringBuilder();
        List<String> tmpList = new ArrayList<>();
        AtomicInteger offset = new AtomicInteger();
        is.forEach(e -> {
            if (e != 32 || offset.get() > 9) {
                sb.append((char) e);
            } else if (sb.length() > 0) {
                tmpList.add(sb.toString());
                offset.getAndIncrement();
                sb.delete(0, sb.length());
            }
        });

        // 最後のコマンドはここで拾う
        tmpList.add(sb.toString());
        sb.delete(0, sb.length());

        // 検証用コード
        tmpList.forEach(System.out::println);
//        System.out.println(sb.toString());
//        System.out.println(line);
//        System.out.println(offset);
        return dto;
    }

    @Override
    public List<ProcessDto> getProcessList() {
        return null;
    }
}
