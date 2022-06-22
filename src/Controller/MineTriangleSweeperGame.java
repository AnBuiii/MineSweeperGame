package Controller;

import Interfaces.IFinishGameListener;
import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Interfaces.IStatusPanelListener;
import Models.Cell;
import Models.GameDifficulty;
import Models.MineTriangleGrid;
import Views.MineTriangleGridPanel;
import Views.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static Views.custom.Theme.*;
import static Views.custom.Theme.BOMB;

public class MineTriangleSweeperGame extends JFrame implements IPanel, IGamePlayListener, IStatusPanelListener, IFinishGameListener {
    private int WINDOW_WIDTH;
    private int WINDOW_HEIGHT;
    private int num_rows;
    private int num_columns;
    private int num_bombs;
    private boolean isFinish;
    private MineTriangleGridPanel mineTriangleGridPanel;
    private StatusPanel statusPanel;
    private MineTriangleGrid mineTriangleGrid;
    public Home home;



    public MineTriangleSweeperGame(int num_rows, int num_columns, int num_bombs) {
        mineTriangleGrid = new MineTriangleGrid(num_rows, num_columns, num_bombs);
        this.num_rows = num_rows;
        this.num_columns = num_columns;
        this.num_bombs = num_bombs;

        WINDOW_HEIGHT = num_rows * CELL_SIZE + STATUS_PANEL_HEIGHT;
        if(num_columns % 2 == 0){
            WINDOW_WIDTH = num_columns / 2 * CELL_SIZE;
        }else {
            WINDOW_WIDTH = (num_columns + 1) / 2 * CELL_SIZE;
        }


        init();
        addView();
        addEvent();
    }


    public MineTriangleSweeperGame(MineTriangleSweeperGame old){
        mineTriangleGrid = old.mineTriangleGrid;
        WINDOW_WIDTH = old.WINDOW_WIDTH;
        WINDOW_HEIGHT = old.WINDOW_HEIGHT;
        num_rows = old.num_rows;
        num_columns = old.num_columns;
        num_bombs = old.num_bombs;
        isFinish = old.isFinish;

        init();
        addView();
        addEvent();

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

        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        mineTriangleGridPanel = new MineTriangleGridPanel(num_rows, num_columns);
        mineTriangleGridPanel.setBounds(0, STATUS_PANEL_HEIGHT, WINDOW_WIDTH, WINDOW_HEIGHT- STATUS_PANEL_HEIGHT);
        add(mineTriangleGridPanel);
        mineTriangleGridPanel.addListener(this);
//

    }

    @Override
    public void addEvent() {
        WindowListener window = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int kq = JOptionPane.showConfirmDialog(MineTriangleSweeperGame.this, "Bạn sẽ mất dữ liệu vừa chơi, tiếp tục?",
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
        return mineTriangleGrid.getCells();
    }

    @Override
    public void reveal(int x, int y) {

        if(!mineTriangleGrid.isCellRevealed(x,y)){
            home.playSoundClickCell();
        }

        if(!mineTriangleGrid.isPlayed) mineTriangleGrid.firstMove(x, y);
        boolean check = mineTriangleGrid.reveal(x, y);
        if (!check) {
            mineTriangleGrid.revealAllCell();
            isFinish = true;
        }
        if(isVictory()){
            isFinish = true;

        }
        if(isFinish){
            openFinishGame();
        }
        mineTriangleGridPanel.updateTriangleGridPanel();
      //  int numSquareClosed = mineTriangleGridPanel.getNumCellUnRevealed();
      //  statusPanel.updateStatus(numSquareClosed);
    }

    @Override
    public void flag(int x, int y) {
        if(!mineTriangleGrid.isCellRevealed(x,y)){
            home.playSoundSocketFlag();
        }
        mineTriangleGrid.flag(x, y);
        mineTriangleGridPanel.updateTriangleGridPanel();
    }

    @Override
    public void restart() {
        mineTriangleGrid = new MineTriangleGrid(num_rows, num_columns,num_bombs);
        mineTriangleGridPanel.updateTriangleGridPanel();
    }

    @Override
    public void reviewNext() {

    }

    @Override
    public void back() {
        this.setVisible(false);
        home.playStartGameMusic();
        home.reDrawHome();
        home.setVisible(true);
    }

    @Override
    public void hint() {

    }
    void openFinishGame(){
        //home.closeMusic();
        this.setForeground(new Color(1.0f,1.0f,1.0f,0));
        FinishGame finishGame = new FinishGame(isVictory());
        finishGame.setVisible(true);
        finishGame.addListener(this);
        finishGame.addEventButtonListener(this.home.getSoundEventButton());
        if(isVictory()){
            home.playSoundWinGame();
        }else {
            home.playSoundLoseGame();
        }
        this.disable();
    }

    public boolean isVictory(){
        return mineTriangleGrid.isVictory();
    }

    public boolean isFinished(){
        return isFinish;
    }
    public void setHome(Home home){
        this.home = home;
        statusPanel.addEventButtonListener(home.getSoundEventButton());
    }

    @Override
    public void closeFinishGame() {
        enable();
        requestFocus();
    }

    @Override
    public void reGame() {
        dispose();
        home.closeMusic();
        home.startGame(GameDifficulty.BEGINNER);

    }

    @Override
    public void reviewMode() {

    }
}
