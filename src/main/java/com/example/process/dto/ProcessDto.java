package com.example.process.dto;

import com.example.enums.OperationSystem;
import com.example.util.OsUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Data
public abstract class ProcessDto {
    private String id;
    private String name;

    /**
     * OS毎にインスタンス取得
     * @return ProcessDto
     */
    public static ProcessDto getInstance() {
        OperationSystem os = OsUtil.judgeOs();
        return switch (os) {
            case WINDOWS -> new ProcessDtoForWindows();
            case MAC -> new ProcessDtoForMac();
            case LINUX -> new ProcessDtoForLinux();
        };
    }

    /**
     * 項目をセットする
     * @param tmpList　List<String>
     * @param index　int
     * @param dto　ProcessDto
     */
    abstract public void setColumn(List<String> tmpList, int index, ProcessDto dto);

    /**
     * プロセスコマンドの出力結果をDtoのListに変換する
     * @param stream Stream<String>
     * @return List<ProcessDto>
     */
    abstract public List<ProcessDto> convertDtoList(Stream<String> stream);

    /**
     * プロセスコマンドの出力結果をDtoのListに変換する
     * ※ MacとLinuxは処理が同様なのでここに定義してそれぞれで呼び出す
     * @param stream Stream<String>
     * @return List<ProcessDto>
     */
    public static List<ProcessDto> convertDtoListForMacOrLinux(Stream<String> stream) {
        // header行を削除1行
        List<String> lines = new LinkedList<>(Objects.requireNonNull(stream).toList());
        lines.remove(0);

        // プロセスのdtoリストを作成する
        List<ProcessDto> processList = new ArrayList<>();
        lines.forEach(line -> {
            StringBuilder sb = new StringBuilder();
            List<String> tmpList = new ArrayList<>();
            AtomicInteger offset = new AtomicInteger();
            line.chars().forEach(e -> {
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

            // dtoに詰め替え
            ProcessDto dto = ProcessDto.getInstance();
            for (int i = 0; i < tmpList.size(); i++) {
                dto.setColumn(tmpList, i, dto);
            }

            processList.add(dto);

        });
        return processList;
    }
}
