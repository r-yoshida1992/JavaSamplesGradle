package com.example.process;

import com.example.process.dto.ProcessDto;
import com.example.process.dto.ProcessDtoForWindows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ProcessManagerForWindows implements ProcessManager{
    private static final ProcessBuilder pb = new ProcessBuilder();

    /**
     * 全プロセスのリストを取得する
     *
     * @return List<ProcessDtoForWindows>
     */
    @Override
    public List<ProcessDto> getProcessList() {
        Process p;
        Stream<String> stream = null;
        try {
            // コマンドの結果を取得
            p = pb.command(ProcessManager.getProcessCommand()).start();
            stream = new String(p.getInputStream().readAllBytes(), "Shift-JIS").lines();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // header行を削除(2行)
        List<String> lines = new LinkedList<>(Objects.requireNonNull(stream).toList());
        IntStream.rangeClosed(1, 2).forEach(e -> lines.remove(0));

        // 区切るポイントを取得してリストから削除
        List<Integer> splitPoint = getSplitPoint(lines.get(0));
        lines.remove(0);

        // プロセスのdtoリストを作成する
        List<ProcessDto> processList = new ArrayList<>();
        lines.forEach(e -> processList.add(convertDto(e, splitPoint)));

        return processList;
    }

    /**
     * 区切るポイントをlistにして返す
     *
     * @param line String
     * @return List<Integer>
     */
    private static List<Integer> getSplitPoint(String line) {
        List<Integer> splitPoint = new ArrayList<>();
        AtomicInteger idx = new AtomicInteger();
        IntConsumer consumer = e -> {
            if (e == ' ') {
                splitPoint.add(idx.get());
            }
            idx.getAndIncrement();
        };
        line.chars().forEach(consumer);
        return splitPoint;
    }

    /**
     * 一行を読み込んでDtoに変換する
     *
     * @param line       String
     * @param splitPoint List<Integer>
     * @return ProcessDtoForWindows
     */
    private static ProcessDtoForWindows convertDto(String line, List<Integer> splitPoint) {
        AtomicInteger idx = new AtomicInteger();
        StringBuilder sb = new StringBuilder();
        List<String> tmpList = new ArrayList<>();
        line.chars().forEach(e -> {
            if (splitPoint.contains(idx.getAndIncrement())) {
                tmpList.add(sb.toString().trim());
                sb.delete(0, sb.length());
            } else {
                sb.append((char) e);
            }
        });

        // 最後のカラムはここで拾う
        tmpList.add(sb.toString().trim());

        // dtoに詰め替え
        ProcessDtoForWindows dto = new ProcessDtoForWindows();
        for (int i = 0; i < tmpList.size(); i++) {
            setColumn(tmpList, i, dto);
        }

        return dto;
    }

    /**
     * 項目に応じた値をセットする
     */
    private static void setColumn(List<String> tmpList, int index, ProcessDtoForWindows dto) {
        switch (index) {
            case 0 -> dto.setImageName(tmpList.get(index));
            case 1 -> dto.setPid(tmpList.get(index));
            case 2 -> dto.setSessionName(tmpList.get(index));
            case 3 -> dto.setSession(tmpList.get(index));
            case 4 -> dto.setMemUsage(tmpList.get(index));
            case 5 -> dto.setStatus(tmpList.get(index));
            case 6 -> dto.setUserName(tmpList.get(index));
            case 7 -> dto.setTime(tmpList.get(index));
            case 8 -> dto.setWindowTitle(tmpList.get(index));
        }
    }
}
