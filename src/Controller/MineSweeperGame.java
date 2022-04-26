package Controller;


import Interfaces.IGamePlayListener;
import Models.Cell;
import Models.MineGrid;
import Interfaces.IPanel;
import Views.MineGridPanel;
import Views.StatusPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MineSweeperGame extends JFrame implements IPanel, IGamePlayListener {

    private static final String TITLE = "MineSweeper";
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 560;
    public static final int STATUS_PANEL_HEIGHT = 80;
    public static int num_rows;
    public static int num_columns;
    public static int num_bombs;
    private MineGridPanel mineGridPanel;
    private StatusPanel statusPanel;
    private MineGrid mineGrid;

    public MineSweeperGame(int num_rows, int num_columns, int num_bombs) {
        mineGrid = new MineGrid(num_rows, num_columns, num_bombs);
        this.num_rows = num_rows;
        this.num_columns = num_columns;
        this.num_bombs = num_bombs;
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setTitle(TITLE);
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

    }

    @Override
    public void addView() {
        statusPanel = new StatusPanel();
        statusPanel.setBounds(0, 0, WINDOW_WIDTH, STATUS_PANEL_HEIGHT);
        add(statusPanel);
        statusPanel.addListener(this);

        mineGridPanel = new MineGridPanel();
        mineGridPanel.setBounds(0, 80, WINDOW_WIDTH, WINDOW_HEIGHT- STATUS_PANEL_HEIGHT);
        add(mineGridPanel);
        mineGridPanel.addListener(this);
    }

    @Override
    public void addEvent() {
        WindowListener window = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int kq = JOptionPane.showConfirmDialog(MineSweeperGame.this, "Bạn có muốn thoát không?",
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
        return mineGrid.getListCell();
    }

    @Override
    public void reveal(int x, int y) {
        boolean check = mineGrid.reveal(x, y);
        if (!check) { mineGrid.revealAllCell(); }
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
        //this.dispose();
    }
}