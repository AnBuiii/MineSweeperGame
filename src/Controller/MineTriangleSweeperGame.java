package Controller;

import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Interfaces.IStatusPanelListener;
import Models.Cell;
import Models.MineGrid;
import Views.MineGridPanel;
import Views.MineTriangleGridPanel;
import Views.StatusPanel;

import javax.swing.*;

import static Views.custom.Theme.CELL_SIZE;
import static Views.custom.Theme.STATUS_PANEL_HEIGHT;

public class MineTriangleSweeperGame extends JFrame implements IPanel, IGamePlayListener, IStatusPanelListener {
    private int WINDOW_WIDTH;
    private int WINDOW_HEIGHT;
    private int num_rows;
    private int num_columns;
    private int num_bombs;
    private boolean isFinish;
    private MineGridPanel mineGridPanel;
    private MineTriangleGridPanel mineTriangleGridPanel;
    private StatusPanel statusPanel;
    private MineGrid mineGrid;
    public Home home;

    MineTriangleSweeperGame(int num_rows, int num_columns, int num_bombs){
        mineGrid = new MineGrid(num_rows, num_columns, num_bombs);
        this.num_rows = num_rows;
        this.num_columns = num_columns;
        this.num_bombs = num_bombs;

        WINDOW_WIDTH = num_columns * CELL_SIZE;
        WINDOW_HEIGHT = num_rows * CELL_SIZE + STATUS_PANEL_HEIGHT;

        init();
        addView();
        addEvent();
    };

    @Override
    public Cell[][] getListCell() {
        return new Cell[0][];
    }

    @Override
    public void reveal(int x, int y) {

    }

    @Override
    public void flag(int x, int y) {

    }

    @Override
    public void init() {

    }

    @Override
    public void addView() {

    }

    @Override
    public void addEvent() {

    }

    @Override
    public void back() {

    }

    @Override
    public void hint() {

    }

    @Override
    public void restart() {

    }
}
