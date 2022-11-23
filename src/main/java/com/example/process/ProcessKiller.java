package com.example.process;

import com.example.process.dto.ProcessDto;
import com.example.util.ColorText;
import com.example.util.StringUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ProcessKiller {
    private static final Scanner sc = new Scanner(System.in);
    private static final ProcessBuilder pb = new ProcessBuilder();

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        ProcessManager manager = ProcessManager.getInstance();
        // イメージ毎にmapを作成
        Map<String, List<ProcessDto>> processMap = new TreeMap<>();
        manager.getProcessList().forEach(e -> {
            List<ProcessDto> current = processMap.getOrDefault(e.getName(), new ArrayList<>());
            if (current.isEmpty()) {
                processMap.put(e.getName(), current);
            }
            current.add(e);
        });

        // 添え字付きでプロセス一覧を出力
        AtomicInteger index = new AtomicInteger();
        processMap.forEach((k, v) ->
                System.out.printf("[%d] : %s : pid -> {%s}%n", index.getAndIncrement(), k,
                        v.stream().map(ProcessDto::getId).collect(Collectors.joining(","))));

        killProcess(processMap);
    }

    /**
     * プロセスのkillを行う
     */
    private static void killProcess(Map<String, List<ProcessDto>> processMap) {
        // プロセスを入力で選択させる
        System.out.print(ColorText.green("プロセスを選択してください : "));
        String imageIndex = sc.next();

        try {
            // 対象のプロセスを取得
            String targetKey = processMap.keySet().stream().toList().get(Integer.parseInt(imageIndex));
            if (killConfirm(targetKey)) {
                List<ProcessDto> target = processMap.get(targetKey);
                target.forEach(e -> {
                    try {
                        pb.command(ProcessManager.getKillCommand(e.getId())).start();
                    } catch (IOException ex) {
                        System.out.printf(ColorText.red("プロセス番号[%s]のkillに失敗しました。%n"), e.getId());
                    }
                    System.out.printf(ColorText.red("プロセス番号[%s]をkillしました。%n"), e.getId());
                });
            }
        } catch (NumberFormatException e) {
            System.out.println(ColorText.red("[ERROR] 数値で入力してください。"));
            killProcess(processMap);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ColorText.red("[ERROR] 無効なインデックスが入力されました。"));
            killProcess(processMap);
        } finally {
            sc.close();
        }
    }

    /**
     * プロセスのkill確認を行う
     */
    private static boolean killConfirm(String targetKey) {
        boolean checkResult = false;
        System.out.print(ColorText.red(StringUtil.concat(targetKey, "をkillします。よろしいですか？(Y/N) : ")));
        String answer = sc.next();
        if (List.of("Y", "N").contains(answer.toUpperCase())) {
            checkResult = true;
        } else {
            System.out.println(ColorText.yellow("[WARN] Y or N で入力してください。"));
            killConfirm(targetKey);
        }
        return checkResult;
    }

}
