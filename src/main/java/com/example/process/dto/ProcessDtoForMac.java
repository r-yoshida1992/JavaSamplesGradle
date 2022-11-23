package com.example.process.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ProcessDtoForMac extends ProcessDto {
    // プロセスの所有ユーザー
    private String user;
    // プロセス番号
    private String pid;
    // CPU占有率
    private String cpu;
    // 実メモリ占有率
    private String mem;
    // 仮想メモリ使用量(kb単位)
    private String vsz;
    // 物理メモリ使用量(kb単位)
    private String rss;
    // 制御端末
    private String tt;
    // プロセスの状態
    private String stat;
    // 起動時刻
    private String started;
    // 累積CPU時間
    private String time;
    // コマンド
    private String command;

    public void setCommand(String command) {
        this.command = command;
        setName(command);
    }

    public void setPid(String pid) {
        this.pid = pid;
        setId(pid);
    }
}
