package DesignPattern.GameBuilder;

import Models.GameDifficulty;

public class Director {
    GameDifficulty gameDifficulty;

    public Director(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    private void constructBeginnerLevel(GameBuilder builder) {
        builder.setRow(9);
        builder.setColumn(9);
        builder.setMines(10);
        builder.setGameMode(1);
    }

    private void constructIntermediateLevel(GameBuilder builder) {
        builder.setRow(16);
        builder.setColumn(16);
        builder.setMines(40);
        builder.setGameMode(2);
    }

    private void constructExperienceLevel(GameBuilder builder) {
        builder.setRow(16);
        builder.setColumn(30);
        builder.setMines(99);
        builder.setGameMode(3);
        // 16, 30, 99
    }

    private void constructTriangleGame(GameBuilder builder) {
        builder.setRow(16);
        builder.setColumn(31);
        builder.setMines(45);
        builder.setGameMode(4);
    }

    public void constructGame(GameBuilder builder) {
        switch (gameDifficulty) {
            case BEGINNER:
                constructBeginnerLevel(builder);
                break;
            case INTERMEDIATE:
                constructIntermediateLevel(builder);
                break;
            case EXPERT:
                constructExperienceLevel(builder);
                break;
            case TRIANGLE:
                constructTriangleGame(builder);
                break;
//            default:
//                director.constructExperienceLevel(builder);
//                break;
        }
    }

    public void constructCustomLevel(GameBuilder builder) {
        // custom field
    }
}