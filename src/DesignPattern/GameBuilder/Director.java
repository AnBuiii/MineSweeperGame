package DesignPattern.GameBuilder;

import Views.MineGridPanel;
import Views.MineTriangleGridPanel;

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

    public void constructTriangleGame(GameBuilder builder) {
        builder.setRow(16);
        builder.setColumn(31);
        builder.setMines(45);
        builder.setGameMode(4);
    }

    public void constructCustomLevel(GameBuilder builder) {
        // custom field
    }
}