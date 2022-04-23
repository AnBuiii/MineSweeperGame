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
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 560;
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
        statusPanel.setBounds(0, 0, 600, 80);
        add(statusPanel);
        statusPanel.addListener(this);

        mineGridPanel = new MineGridPanel();
        mineGridPanel.setBounds(0, 80, 600, 480);
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
        mineGrid = new MineGrid();
        mineGridPanel.updateGrid();
    }
}