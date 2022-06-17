package Controller;


import Interfaces.IGamePlayListener;
import Interfaces.IStatusPanelListener;
import Models.Cell;
import Models.MineGrid;
import Interfaces.IPanel;
import Views.MineGridPanel;
import Views.StatusPanel;

import static Views.custom.Theme.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//import static Views.HomePanel.*;


public class MineSweeperGame extends JFrame implements IPanel, IGamePlayListener, IStatusPanelListener {

    private int WINDOW_WIDTH;
    private int WINDOW_HEIGHT;
    private int num_rows;
    private int num_columns;
    private int num_bombs;
    private boolean isFinish;
    private MineGridPanel mineGridPanel;
    private StatusPanel statusPanel;
    private MineGrid mineGrid;
    public Home home;
    int gameMode;


    public MineSweeperGame(int num_rows, int num_columns, int num_bombs, int gameMode) {
        mineGrid = new MineGrid(num_rows, num_columns, num_bombs);
        this.num_rows = num_rows;
        this.num_columns = num_columns;
        this.num_bombs = num_bombs;
        this.gameMode = gameMode;

        WINDOW_WIDTH = num_columns * CELL_SIZE;
        WINDOW_HEIGHT = num_rows * CELL_SIZE + STATUS_PANEL_HEIGHT;

        init();
        addView();
        addEvent();
    }

    public MineSweeperGame(MineSweeperGame old){
        WINDOW_WIDTH = old.WINDOW_WIDTH;
        WINDOW_HEIGHT = old.WINDOW_HEIGHT;
        num_rows = old.num_rows;
        num_columns = old.num_columns;
        num_bombs = old.num_bombs;
        isFinish = old.isFinish;
        mineGrid = old.mineGrid;
        gameMode = old.gameMode;

        init();
        addView();
        addEvent();


        for(int x = 0; x< mineGrid.getCells().length; x++){
            for(int y = 0; y< mineGrid.getCells()[0].length; y++){
                System.out.print(mineGrid.getCells()[x][y].getNumMineAround()+ " ");
            }
        }
        //System.out.println(mineGrid.getCells().length);
        mineGridPanel.updateGrid();
        System.out.println("hmmm");
    }

    @Override
    public void init() {
        setTitle(TITLE+BOMB);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        isFinish = false;
    }

    @Override
    public void addView() {
        statusPanel = new StatusPanel();
        statusPanel.setBounds(0, 0, WINDOW_WIDTH, STATUS_PANEL_HEIGHT);
        add(statusPanel);
        statusPanel.addListener(this);


        mineGridPanel = new MineGridPanel();
        mineGridPanel.setBounds(0, STATUS_PANEL_HEIGHT, WINDOW_WIDTH , WINDOW_HEIGHT - STATUS_PANEL_HEIGHT);
        add(mineGridPanel);
        mineGridPanel.addListener(this);



    }

    @Override
    public void addEvent() {
        WindowListener window = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int kq = JOptionPane.showConfirmDialog(MineSweeperGame.this, "Bạn sẽ mất dữ liệu phần mới chơi, tiếp tục?",
                        "Thông báo", JOptionPane.YES_NO_OPTION);
                if (kq == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        };
        addWindowListener(window);
    }

    @Override
    public Cell[][] getListCell() {
        return mineGrid.getCells();
    }

    @Override
    public void reveal(int x, int y) {
        if(!mineGrid.isPlayed) mineGrid.firstMove(x, y);
        boolean check = mineGrid.reveal(x, y);
        if (!check) {
            mineGrid.revealAllCell();
            isFinish = true;
        }
        mineGridPanel.updateGrid();
        int numSquareClosed = mineGridPanel.getNumCellUnRevealed();
        statusPanel.updateStatus(numSquareClosed);
    }

    @Override
    public void flag(int x, int y) {
        mineGrid.flag(x, y);
        mineGridPanel.updateGrid();
    }

    @Override
    public void restart() {
        mineGrid = new MineGrid(num_rows, num_columns,num_bombs);
        mineGridPanel.updateGrid();
    }

    @Override
    public void back() {
        this.setVisible(false);
        home.reDrawHome();
        home.setVisible(true);
    }

    @Override
    public void hint() {

    }
    public boolean isFinished(){
        return isFinish;
    }
    public void setHome(Home home){
        this.home = home;
    }
}