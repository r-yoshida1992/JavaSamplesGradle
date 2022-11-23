package com.example.util;

import com.example.enums.OperationSystem;

public class OsUtil {
    public static OperationSystem judgeOs() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("win")) {
            return OperationSystem.WINDOWS;
        } else if (osName.startsWith("mac")) {
            return OperationSystem.MAC;
        } else if (osName.startsWith("linux")) {
            return OperationSystem.LINUX;
        } else {
            throw new RuntimeException("Unsupported os.");
        }
    }

}
