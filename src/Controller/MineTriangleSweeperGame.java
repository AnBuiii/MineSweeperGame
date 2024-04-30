package Controller;

import DesignPattern.GameTemplate.MineTriangleGrid;
import Views.MineTriangleGridPanel;


import static Views.custom.Theme.*;

public class MineTriangleSweeperGame extends MineSweeperTemplate {


    public MineTriangleSweeperGame(int num_rows, int num_columns, int num_bombs) {
        super(num_rows, num_columns, num_bombs, 4);
        mineGrid = new MineTriangleGrid(num_rows, num_columns, num_bombs);
    }

    @Override
    public void setSize() {
        setSize((num_columns % 2 == 0) ? num_columns / 2 * CELL_SIZE : (num_columns + 1) / 2 * CELL_SIZE, num_rows * CELL_SIZE + STATUS_PANEL_HEIGHT);
    }

    @Override
    public void addMineGrid() {
        mineGridPanelTemplate = new MineTriangleGridPanel(num_rows, num_columns);
        mineGridPanelTemplate.setBounds(0, STATUS_PANEL_HEIGHT, getWidth(), getHeight() - STATUS_PANEL_HEIGHT);
        add(mineGridPanelTemplate);
        mineGridPanelTemplate.addListener(this);
    }


    @Override
    public void reGame() {
        dispose();
        home.playInGameMusic();
        home.startTriangleGame();
    }
}
