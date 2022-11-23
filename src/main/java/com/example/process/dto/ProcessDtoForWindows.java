package com.example.process.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
