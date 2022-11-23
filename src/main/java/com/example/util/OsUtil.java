package com.example.util;

import com.example.enums.OperationSystem;

public class OsUtil {
    static String osName = System.getProperty("os.name").toLowerCase();

    public static OperationSystem judgeOs() {
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

    public static String getCharSet() {
        if (judgeOs() == OperationSystem.WINDOWS) {
            return "Shift-JIS";
        } else {
            return "UTF-8";
        }
    }

}
