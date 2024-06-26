package DesignPattern.GameTemplate;

import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Interfaces.ISoundEventButton;

import javax.swing.*;

public abstract class MineGridPanelTemplate extends JPanel implements IPanel {
    protected int num_rows;
    protected int num_columns;
    protected IGamePlayListener listener; //
//    private MineSweeperGame game;


    private ISoundEventButton eventButton;

    public MineGridPanelTemplate(int rows, int columns) {
        this.num_rows = rows;
        this.num_columns = columns;
        init();
        addView();
        addEvent();
    }



    public abstract void updateGrid();
    public abstract void addListener(IGamePlayListener event);
    public abstract void mark(int x, int y);
}