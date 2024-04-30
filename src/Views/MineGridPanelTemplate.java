package Views;

import Interfaces.IGamePlayListener;
import Controller.MineSweeperGame;
import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Models.MineLabel;

import javax.swing.*;
import java.awt.*;

public abstract class MineGridPanelTemplate extends JPanel implements IPanel {
    protected int num_rows;
    protected int num_columns;
    protected IGamePlayListener listener; //
    private MineSweeperGame game;


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