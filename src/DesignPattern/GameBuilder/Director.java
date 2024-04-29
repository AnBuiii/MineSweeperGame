package DesignPattern.GameBuilder;

import Models.GameDifficulty;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

// switch (gameMode) {
//        case 1:
//        home.startGame(GameDifficulty.BEGINNER);
//                break;
//                        case 2:
//                        home.startGame(GameDifficulty.INTERMEDIATE);
//                break;
//                        case 3:
//                        home.startGame(GameDifficulty.EXPERT);
//                break;
//                        case 0:
//                        System.out.println("?");
//                home.startCustomGame(num_rows, num_columns, num_bombs);
//                break;
//                        }

public class Director {

    public void constructBeginnerLevel(GameBuilder builder) {
        builder.setRow(9);
        builder.setColumn(9);
        builder.setMines(10);
        builder.setGameMode(1);
    }

    public void constructIntermediateLevel(GameBuilder builder) {
        builder.setRow(16);
        builder.setColumn(16);
        builder.setMines(40);
        builder.setGameMode(2);
    }

    public void constructExperienceLevel(GameBuilder builder) {
        builder.setRow(16);
        builder.setColumn(30);
        builder.setMines(99);
        builder.setGameMode(3);
        // 16, 30, 99
    }

    public void constructCustomLevel(GameBuilder builder) {
        // custom field
    }
}