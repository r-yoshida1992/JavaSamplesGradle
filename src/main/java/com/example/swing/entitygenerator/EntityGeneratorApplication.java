package com.example.swing.entitygenerator;

import com.example.swing.entitygenerator.core.SampleFrame;

import java.sql.SQLException;

public class EntityGeneratorApplication {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {
            new SampleFrame().setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
