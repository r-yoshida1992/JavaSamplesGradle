package com.example.process;

import com.example.enums.OperationSystem;
import com.example.process.dto.ProcessDtoForWindows;
import com.example.util.OsUtil;

import java.util.List;

public interface ProcessManager {
    OperationSystem os = OsUtil.judgeOs();

    static ProcessManager getInstance() {
        return switch (os) {
            case WINDOWS -> new ProcessManagerForWindows();
            case MAC -> new ProcessManagerForMac();
            case LINUX -> new ProcessManagerForLinux();
        };
    }

    static List<String> getProcessCommand() {
        return switch (os) {
            case WINDOWS -> List.of("tasklist", "/v");
            case MAC, LINUX -> List.of("ps", "aux");
        };
    }

    static List<String> getKillCommand(String pid) {
        return switch (os) {
            case WINDOWS -> List.of("taskkill", "/f", "/pid", pid);
            case MAC, LINUX -> List.of("ps", "aux");
        };
    }

    List<ProcessDtoForWindows> getProcessList();
}
