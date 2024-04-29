package DesignPattern.GameBuilder;

import Controller.MineSweeperGame;

public class MineSweeperGameBuilder implements GameBuilder {
    private int column;
    private int row;
    private int mines;
    private int gameMode;

    @Override
    public void setColumn(int width) {
        this.column = width;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void setMines(int mines) {
        this.mines = mines;
    }

    @Override
    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public MineSweeperGame build() {
        return new MineSweeperGame(row, column, mines, gameMode);
    }
}
