package com.example.process;

import lombok.Data;

import java.math.BigDecimal;

@Data
public final class ProcessDto {
    private String user;
    private int pid;
    private BigDecimal cpu;
    private BigDecimal mem;
    private BigDecimal vsz;
    private BigDecimal rss;
    private String tt;
    private String stat;
    private String started;
    private String time;
    private String command;
}
