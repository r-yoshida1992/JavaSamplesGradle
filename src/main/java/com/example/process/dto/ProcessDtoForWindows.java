package com.example.process.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ProcessDtoForWindows extends ProcessDto {
    // イメージ名
    private String imageName;
    // プロセス番号
    private String pid;
    // セッション名
    private String sessionName;
    // セッション
    private String session;
    // メモリ使用量
    private String memUsage;
    // ステータス
    private String status;
    // ユーザー名
    private String userName;
    // 累積CPU時間
    private String time;
    // ウィンドウタイトル
    private String windowTitle;

    public void setImageName(String imageName) {
        this.imageName = imageName;
        this.setName(imageName);
    }

    public void setPid(String pid) {
        this.pid = pid;
        this.setId(pid);
    }

    public void setColumn(List<String> tmpList, int index, ProcessDto baseDto) {
        ProcessDtoForWindows dto = (ProcessDtoForWindows) baseDto;
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

    @Override
    public List<ProcessDto> convertDtoList(Stream<String> stream) {
        // header行を削除(2行)
        List<String> lines = new LinkedList<>(Objects.requireNonNull(stream).toList());
        IntStream.rangeClosed(1, 2).forEach(e -> lines.remove(0));

        // 区切るポイントを取得してリストから削除
        List<Integer> splitPoint = getSplitPoint(lines.get(0));
        lines.remove(0);

        // プロセスのdtoリストを作成する
        List<ProcessDto> processList = new ArrayList<>();

        // 一行ずつ解析してDtoに変換する
        lines.forEach(line -> {
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
                dto.setColumn(tmpList, i, dto);
            }
            processList.add(dto);
        });

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
}
