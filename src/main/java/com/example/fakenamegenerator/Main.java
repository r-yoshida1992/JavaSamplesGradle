package com.example.fakenamegenerator;

import com.example.fakenamegenerator.dto.enums.FirstNameMale;
import com.example.fakenamegenerator.dto.enums.LastName;

import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    final static int count = 10000; // generate count.
    final static Random r = new Random();
    final static LastName[] lastNames = LastName.values();
    final static FirstNameMale[] firstNameMales = FirstNameMale.values();
    final static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        sb.append("姓\t名\t姓カナ\t名カナ\n");
        IntStream.range(0, count).forEach(i -> {
            LastName lastName = lastNames[r.nextInt(lastNames.length)];
            FirstNameMale firstNameMale = firstNameMales[r.nextInt(firstNameMales.length)];
            sb.append(
                            String.join(
                                    "\t",
                                    lastName.getKanji(),
                                    firstNameMale.getKanji(),
                                    lastName.getKana(),
                                    firstNameMale.getKana()))
                    .append("\n");
        });
        System.out.print(sb);
    }
}