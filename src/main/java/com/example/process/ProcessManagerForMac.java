package com.example.process;

import com.example.process.dto.ProcessDto;
import com.example.process.dto.ProcessDtoForMac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ProcessManagerForMac implements ProcessManager{
    @Override
    public List<ProcessDto> getProcessList() {
        ProcessBuilder pb = new ProcessBuilder();

        // psコマンドの結果を取得
        Process p;
        try {
            p = pb.command(ProcessManager.getProcessCommand()).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // 全行をstreamで取得
        Stream<String> stream = br.lines();
        // header行を削除
        List<ProcessDto> dtoList = new ArrayList<>();
        AtomicInteger index = new AtomicInteger();
        stream.forEach(e -> {
            // headerは処理を飛ばす
            if (index.get() != 0) {
                dtoList.add(convertDto(e));
            }
            index.getAndIncrement();
        });
        return dtoList;
    }

    // 一行を読み込んでDtoに変換する
    private static ProcessDtoForMac convertDto(String line) {
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

        // dtoに詰め替え
        ProcessDtoForMac dto = new ProcessDtoForMac();
        for (int i = 0; i < tmpList.size(); i++) {
            setColumn(tmpList, i, dto);
        }

        return dto;
    }

    /**
     * 項目に応じた値をセットする
     */
    private static void setColumn(List<String> tmpList, int index, ProcessDtoForMac dto) {
        switch (index) {
            case 0 -> dto.setUser(tmpList.get(index));
            case 1 -> dto.setPid(tmpList.get(index));
            case 2 -> dto.setCpu(tmpList.get(index));
            case 3 -> dto.setMem(tmpList.get(index));
            case 4 -> dto.setVsz(tmpList.get(index));
            case 5 -> dto.setRss(tmpList.get(index));
            case 6 -> dto.setTt(tmpList.get(index));
            case 7 -> dto.setStat(tmpList.get(index));
            case 8 -> dto.setStarted(tmpList.get(index));
            case 9 -> dto.setTime(tmpList.get(index));
            case 10 -> dto.setCommand(tmpList.get(index));
        }
    }
}
