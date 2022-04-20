package Controller;


import Models.Cell;
import Models.MineGrid;
import Views.IPanel;
import Views.MineGridPanel;
import Views.StatusPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MineSweeperGame extends JFrame implements IPanel, IListener {
    private static final String TITLE = "MineSweeper";
    public static final int WINDOW_WIDTH = 730;
    public static final int WINDOW_HEIGHT = 600;
    private MineGridPanel mineGridPanel;
    private StatusPanel statusPanel;
    private MineGrid mineGrid;

    public MineSweeperGame() {
        mineGrid = new MineGrid();
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setTitle(TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
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
        statusPanel.setBounds(10, 0, 700, 60);
        add(statusPanel);
        statusPanel.addListener(this);

        mineGridPanel = new MineGridPanel();
        mineGridPanel.setBounds(10, 60, 700, 500);
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
    public Cell[][] getListSquare() {
        return mineGrid.getListSquare();
    }

    @Override
    public void reveal(int x, int y) {
        boolean check = mineGrid.play(x, y);
        if (!check) { mineGrid.showAllSquares(); }
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
        mineGrid = new MineGrid();
        mineGridPanel.updateGrid();
    }
}