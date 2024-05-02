package DesignPattern.GameBuilder;

import Controller.MineSweeperGame;
import DesignPattern.GameTemplate.MineGrid;
import DesignPattern.GameTemplate.MineTriangleGrid;
import Views.MineGridPanel;
import Views.MineGridPanelTemplate;
import Views.MineTriangleGridPanel;

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
        MineGridPanelTemplate mineGridPanel;
        MineGrid mineGrid;
        if (gameMode == 4) {
            mineGridPanel = new MineTriangleGridPanel(row, column);
            mineGrid = new MineTriangleGrid(row, column, mines);
        } else {
            mineGridPanel = new MineGridPanel(row, column);
            mineGrid = new MineGrid(row, column, mines);
        }

        return new MineSweeperGame(row, column, mines, gameMode, mineGridPanel, mineGrid);
    }
}
