package com.example.dummy;

/**
 * DummyのLoading文言をコンソールに出力する
 */
public class DummyLoadingText {
    public void run() {
        String loadingText = null;
        for (int i = 0; i <= 100; i++) {
            loadingText = String.format("\r\u001b[00;36mLoading... %3d%%\u001b[00m", i);
            System.out.print(loadingText);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("\r%s", " ".repeat(loadingText.length()));
        System.out.println("\rDone!");
    }
}
