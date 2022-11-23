package com.example.process.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Stream;

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

    public void setColumn(List<String> tmpList, int index, ProcessDto baseDto) {
        ProcessDtoForMac dto = (ProcessDtoForMac) baseDto;
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

    @Override
    public List<ProcessDto> convertDtoList(Stream<String> stream) {
        return ProcessDto.convertDtoListForMacOrLinux(stream);
    }
}
