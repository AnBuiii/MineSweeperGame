package Controller;

import Interfaces.IFinishGameListener;
import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Interfaces.IStatusPanelListener;
import Models.Cell;
import Models.GameDifficulty;
import Models.History;
import Models.MineTriangleGrid;
import Views.FinishGamePanel;
import Views.MineTriangleGridPanel;
import Views.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.ArrayList;

import static Views.custom.Theme.*;
import static Views.custom.Theme.BOMB;

public class MineTriangleSweeperGame extends JFrame implements IPanel, IGamePlayListener, IStatusPanelListener, IFinishGameListener {
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;
    private final int num_rows;
    private final int num_columns;
    private final int num_bombs;
    private boolean isFinish;
    private MineTriangleGridPanel mineTriangleGridPanel;
    private StatusPanel statusPanel;
    private MineTriangleGrid mineTriangleGrid;
    public Home home;
    ArrayList<History> playHistory;
    boolean reviewMode;
    boolean hintMode;
    private int reviewStep;



    public MineTriangleSweeperGame(int num_rows, int num_columns, int num_bombs) {
        mineTriangleGrid = new MineTriangleGrid(num_rows, num_columns, num_bombs);
        this.num_rows = num_rows;
        this.num_columns = num_columns;
        this.num_bombs = num_bombs;
        this.playHistory = new ArrayList<>();

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
        reviewMode = false;
        hintMode = false;
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
        if(!isReviewMode()){
            playHistory.add(new History(x, y, 1));
        }
        if(!mineTriangleGrid.isCellRevealed(x,y)){
            home.playSoundClickCell();
        }
        if(mineTriangleGrid.numCellPlayed == 0) {
            System.out.println("ok");
            mineTriangleGrid.firstMove(x, y);
        }

        if(!mineTriangleGrid.isPlayed) mineTriangleGrid.firstMove(x, y);
        boolean check = mineTriangleGrid.reveal(x, y);
        if (!check ) {
            mineTriangleGrid.revealAllCell();
            isFinish = true;
        }
        if(isVictory()){
            isFinish = true;

        }
        if(isFinish && !isReviewMode()){
            openFinishGame();
        }
        mineTriangleGridPanel.updateTriangleGridPanel();
    }

    @Override
    public void flag(int x, int y) {
        if(!mineTriangleGrid.isCellRevealed(x,y)){
            home.playSoundSocketFlag();
        }
        mineTriangleGrid.flag(x, y);
        if(!reviewMode) playHistory.add(new History(x, y, 3));
        mineTriangleGridPanel.updateTriangleGridPanel();
    }

    @Override
    public boolean isReviewMode() {
        return reviewMode;
    }

    @Override
    public boolean isHintMode() {
        return hintMode;
    }

    @Override
    public void revealHint(int x, int y) {
        mineTriangleGrid.revealHint(x,y);
        if(!reviewMode) playHistory.add(new History(x, y, 2));
        mineTriangleGridPanel.updateTriangleGridPanel();
    }

    @Override
    public void restart() {
        mineTriangleGrid = new MineTriangleGrid(num_rows, num_columns,num_bombs);
        mineTriangleGridPanel.updateTriangleGridPanel();
    }

    @Override
    public void reviewNext() {
        if(reviewStep == playHistory.size()) return;
        if(playHistory.get(reviewStep).move == 1) reveal(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
        if(playHistory.get(reviewStep).move == 2) revealHint(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
        if(playHistory.get(reviewStep).move == 3) flag(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
        reviewStep++;
        if(reviewStep == playHistory.size()) return;
        mineTriangleGridPanel.mark(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
    }

    @Override
    public void reviewPrevious() {
        if(reviewStep == 0) return;
        mineTriangleGrid.unRevealAllCells();
        mineTriangleGridPanel.updateTriangleGridPanel();
        reviewStep--;
        for(int i = 0; i < reviewStep; i++){
            reveal(playHistory.get(i).x, playHistory.get(i).y);
        }
        mineTriangleGridPanel.mark(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
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
        hintMode = !hintMode;
    }
    void openFinishGame(){
        //home.closeMusic();
        this.setForeground(new Color(1.0f,1.0f,1.0f,0));
        FinishGamePanel finishGamePanel = new FinishGamePanel(isVictory());
        finishGamePanel.setVisible(true);
        finishGamePanel.addListener(this);
        finishGamePanel.addEventButtonListener(this.home.getEventSoundButton());
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
        statusPanel.addEventButtonListener(home.getEventSoundButton());
    }

    @Override
    public void closeFinishGame() {
        enable();
        requestFocus();
    }

    @Override
    public void reGame() {
        dispose();
        home.playInGameMusic();
        home.startTriangleGame();
    }

    @Override
    public void reviewMode() {
        reviewMode = true;
        statusPanel.reviewMode();
        mineTriangleGrid.unRevealAllCells();
        mineTriangleGridPanel.updateTriangleGridPanel();
        mineTriangleGridPanel.mark(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
    }

}
