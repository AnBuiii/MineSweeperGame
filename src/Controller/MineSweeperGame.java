package Controller;


import Interfaces.IFinishGameListener;
import Interfaces.IGamePlayListener;
import Interfaces.IStatusPanelListener;
import Models.Cell;
import Models.GameDifficulty;
import Models.History;
import Models.MineGrid;
import Interfaces.IPanel;
import Views.FinishGamePanel;
import Views.MineGridPanel;
import Views.StatusPanel;

import static Views.custom.Theme.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

//import static Views.HomePanel.*;


public class MineSweeperGame extends JFrame implements IPanel, IGamePlayListener, IStatusPanelListener, IFinishGameListener {

    public static boolean isFinish;
    private int WINDOW_WIDTH;
    private int WINDOW_HEIGHT;
    private int num_rows;
    private int num_columns;
    public int num_bombs;
    //private boolean isFinish;
    private MineGridPanel mineGridPanel;
    private StatusPanel statusPanel;
    private MineGrid mineGrid;
    public Home home;
    int gameMode;
    private boolean reviewMode;
    private int reviewStep;
    private boolean hintMode;
    private int numFlag;
    private boolean isVictory;
    private boolean isSave;


    ArrayList<History> playHistory;


    public MineSweeperGame(int num_rows, int num_columns, int num_bombs, int gameMode) {
        mineGrid = new MineGrid(num_rows, num_columns, num_bombs);
        this.num_rows = num_rows;
        this.num_columns = num_columns;
        this.num_bombs = num_bombs;
        this.playHistory = new ArrayList<>();
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
        playHistory = old.playHistory;

        init();
        addView();
        addEvent();



        //System.out.println(mineGrid.getCells().length);
        mineGridPanel.updateGrid();
    }



    @Override
    public void init() {
        setTitle(TITLE+BOMB);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setUndecorated(true);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(),  ARCW_FORM, ARCH_FORM));
        setLayout(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        isFinish = false;
        reviewMode = false;
        hintMode = false;
        reviewStep = 0;
        numFlag = num_bombs;
        isVictory = true;
        isSave = false;

    }

    @Override
    public void addView() {
        statusPanel = new StatusPanel(num_bombs);
        statusPanel.setBounds(0, 0, WINDOW_WIDTH, STATUS_PANEL_HEIGHT);
        add(statusPanel);
        statusPanel.addListener(this);
        statusPanel.setParentFrame(this);


        mineGridPanel = new MineGridPanel(num_rows, num_columns);
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
        if(!isReviewMode()) {
            playHistory.add(new History(x, y, 1));
        }
        if(!mineGrid.isCellRevealed(x,y) && !mineGrid.isCellFlagged(x, y)){
            home.playSoundClickCell();
        }
        if(mineGrid.numCellPlayed == 0) {
            System.out.println("ok");
            mineGrid.firstMove(x, y);
        }
        boolean check;
        check = mineGrid.reveal(x, y);
        if (!check ) {
            isVictory = false;
            mineGrid.revealAllCell();
            isFinish = true;
        }
        if(isVictory()) isFinish = true;

        if(isFinish && !reviewMode){
            openFinishGame();
        }
        mineGridPanel.updateGrid();

        int numSquareClosed = mineGridPanel.getNumCellUnRevealed();
        statusPanel.updateStatus(numSquareClosed);

    }
    void openFinishGame(){
        if(!isSave) {
            home.savingData(this);
            isSave = true;
        }
        this.setForeground(new Color(1.0f,1.0f,1.0f,0));
        FinishGamePanel finishGamePanel = new FinishGamePanel(isVictory);
        finishGamePanel.setVisible(true);
        finishGamePanel.addListener(this);
        finishGamePanel.setLocationRelativeTo(this);
        finishGamePanel.addEventButtonListener(this.home.getEventSoundButton());
        if(isVictory){
            home.playSoundWinGame();
        }else {
            home.playSoundLoseGame();
        }
        this.disable();
    }

    @Override
    public void flag(int x, int y) {

        if(!mineGrid.isCellRevealed(x,y) && numFlag != 0){
            home.playSoundSocketFlag();
        }
        if(!getListCell()[x][y].isFlagged() && numFlag ==0 && !reviewMode ) return;
        mineGrid.flag(x, y);
        if(!getListCell()[x][y].isFlagged()) numFlag++;
        else numFlag--;
        if(!reviewMode)statusPanel.setNumFlag(numFlag);

        if(!reviewMode) playHistory.add(new History(x, y, 3));
        mineGridPanel.updateGrid();

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
        home.playSoundClickCell();
        mineGrid.revealHint(x,y);
        if(!reviewMode) playHistory.add(new History(x, y, 2));
        mineGridPanel.updateGrid();
        if(isVictory()){
            isFinish = true;
        }
        if(isFinish && !reviewMode){
            openFinishGame();
        }
    }

    @Override
    public void restart() {
        mineGrid = new MineGrid(num_rows, num_columns,num_bombs);
        mineGridPanel.updateGrid();
    }

    @Override
    public void reviewNext() {
        if(reviewStep == playHistory.size()) return;
        if(playHistory.get(reviewStep).move == 1) reveal(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
        if(playHistory.get(reviewStep).move == 2) revealHint(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
        if(playHistory.get(reviewStep).move == 3) flag(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
        reviewStep++;
        if(reviewStep == playHistory.size()) return;
        mineGridPanel.mark(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);

    }

    @Override
    public void reviewPrevious() {
        if(reviewStep == 0) return;
        mineGrid.unRevealAllCells();
        mineGridPanel.updateGrid();
        reviewStep--;
        for(int i = 0; i < reviewStep; i++){
            if(playHistory.get(i).move == 1) reveal(playHistory.get(i).x, playHistory.get(i).y);
            if(playHistory.get(i).move == 2) revealHint(playHistory.get(i).x, playHistory.get(i).y);
            if(playHistory.get(i).move == 3) flag(playHistory.get(i).x, playHistory.get(i).y);
        }
        mineGridPanel.mark(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
    }

    @Override
    public void back() {
        this.setVisible(false);
        home.reDrawHome();
        home.playStartGameMusic();
        home.setVisible(true);
    }

    @Override
    public void hint() {
        hintMode = !hintMode;
    }
    public boolean isFinished(){
        return isFinish;
    }
    public void setHome(Home home){
        this.home = home;
        statusPanel.addEventButtonListener(home.getEventSoundButton());
    }
    public boolean isVictory(){
        return mineGrid.isVictory();
    }
    public int getTime(){
        return statusPanel.getTime();
    }

    @Override
    public void closeFinishGame() {
        enable();
        requestFocus();
    }

    @Override
    public void reGame() {
        home.playInGameMusic();
        dispose();
        switch (gameMode){
            case 1:
                home.startGame(GameDifficulty.BEGINNER);
                break;
            case 2:
                home.startGame(GameDifficulty.INTERMEDIATE);
                break;
            case 3:
                home.startGame(GameDifficulty.EXPERT);
                break;
            case 0:
                System.out.println("?");
                home.startCustomGame(num_rows,num_columns,num_bombs);
                break;
        }
    }

    @Override
    public void reviewMode() {
        reviewMode = true;
        statusPanel.reviewMode();
        mineGrid.unRevealAllCells();
        mineGridPanel.updateGrid();
        mineGridPanel.mark(playHistory.get(reviewStep).x, playHistory.get(reviewStep).y);
    }

    @Override
    public void openTutorial() {
        back();
        home.openTutorial();
    }

    @Override
    public void openStatistic() {
        back();
        home.openStatistic();
    }

    public void startClock() {
        statusPanel.startClock();
    }

    public void killClock() {
        statusPanel.killClock();
    }
}