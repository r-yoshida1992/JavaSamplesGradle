package com.example.process;

import com.example.enums.OperationSystem;
import com.example.process.dto.ProcessDto;
import com.example.util.OsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessManager {
    static OperationSystem os = OsUtil.judgeOs();
    static ProcessBuilder pb = new ProcessBuilder();

    /**
     * プロセス取得コマンドをOS毎に取得する
     * @return List<String>
     */
    public static List<String> getProcessCommand() {
        return switch (os) {
            case WINDOWS -> List.of("tasklist", "/v");
            case MAC, LINUX -> List.of("ps", "aux");
        };
    }

    /**
     * killコマンドをOS毎に取得する
     * @param pid String
     * @return List<String>
     */
    public static List<String> getKillCommand(String pid) {
        return switch (os) {
            case WINDOWS -> List.of("taskkill", "/f", "/pid", pid);
            case MAC, LINUX -> List.of("kill", "-9", pid);
        };
    }

    /**
     * 全プロセスのリストを取得する
     * @return List<ProcessDto>
     */
    public static List<ProcessDto> getProcessList() {
        try {
            return ProcessDto.getInstance().convertDtoList(
                    new String(pb.command(ProcessManager.getProcessCommand()).start()
                            .getInputStream().readAllBytes(), OsUtil.getCharSet()).lines());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 全プロセスを機能毎のMapとして取得する
     * @return Map<String, List<ProcessDto>>
     */
    public static Map<String, List<ProcessDto>> getProcessMap() {
        Map<String, List<ProcessDto>> processMap = new TreeMap<>();
        getProcessList().forEach(e -> {
            List<ProcessDto> current = processMap.getOrDefault(e.getName(), new ArrayList<>());
            if (current.isEmpty()) {
                processMap.put(e.getName(), current);
            }
            current.add(e);
        });
        return processMap;
    }
}
