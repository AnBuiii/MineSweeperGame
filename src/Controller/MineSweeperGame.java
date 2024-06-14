package Controller;


import DesignPattern.GameState.*;
import DesignPattern.GameTemplate.MineGrid;
import DesignPattern.GameTemplate.MineGridPanelTemplate;
import Interfaces.IFinishGameListener;
import Interfaces.IGamePlayListener;
import Interfaces.IStatusPanelListener;
import Models.*;
import Interfaces.IPanel;
import Views.*;

import static Views.custom.Theme.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;


public class MineSweeperGame extends JFrame implements IPanel, IGamePlayListener, IStatusPanelListener, IFinishGameListener {

    protected final int num_rows;
    protected final int num_columns;
    protected int num_bombs;
    protected State state;
    public MineGridPanelTemplate mineGridPanel;
    public StatusPanel statusPanel;
    public MineGrid mineGrid;
    public Home home;
    public ArrayList<History> playHistory;
    int gameMode;
    JPanel frontPn;

    public MineSweeperGame(int num_rows, int num_columns, int num_bombs, int gameMode, MineGridPanelTemplate mineGridPanel, MineGrid mineGrid) {
        this.mineGrid = mineGrid;
        this.num_rows = num_rows;
        this.num_columns = num_columns;
        this.num_bombs = num_bombs;
        this.playHistory = new ArrayList<>();
        this.gameMode = gameMode;
        this.state = new PlayingState(this);
        this.mineGridPanel = mineGridPanel;

        init();
        addView();
        addEvent();
    }

    public void updateState(State state) {
        this.state = state;
    }

    @Override
    public void init() {
        setTitle(TITLE + BOMB);
        setSize();
        setUndecorated(true);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), ARCW_FORM, ARCH_FORM));
        setLayout(null);
    }

    public void setSize() {
        if (gameMode == 4) {
            setSize((num_columns % 2 == 0) ? num_columns / 2 * CELL_SIZE : (num_columns + 1) / 2 * CELL_SIZE, num_rows * CELL_SIZE + STATUS_PANEL_HEIGHT);
        } else {
            setSize(num_columns * CELL_SIZE, num_rows * CELL_SIZE + STATUS_PANEL_HEIGHT);
        }
    }

    @Override
    public void addView() {
        frontPn = new JPanel();
        frontPn.setBounds(getBounds());
        add(frontPn);
        frontPn.setOpaque(true);
        frontPn.setBackground(new Color(0, 0, 0, 100));
        frontPn.setVisible(false);

        statusPanel = new StatusPanel(num_bombs, this);
        statusPanel.setBounds(0, 0, getWidth(), STATUS_PANEL_HEIGHT);
        add(statusPanel);
        statusPanel.addListener(this);
        statusPanel.setParentFrame(this);

        this.mineGridPanel.setBounds(0, STATUS_PANEL_HEIGHT, getWidth(), getHeight() - STATUS_PANEL_HEIGHT);
        this.mineGridPanel.addListener(this);
        add(this.mineGridPanel);
    }

    @Override
    public State getGameState() {
        return state;
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

    public void finishingGame() {
        home.savingData(this);
        statusPanel.finishGame();
        frontPn.setVisible(true);
        if (isVictory()) {
            home.playSoundWinGame();
        } else {
            home.playSoundLoseGame();
        }
        openFinishGamePanel();
    }

    public void openFinishGamePanel() {
        this.setForeground(new Color(1.0f, 1.0f, 1.0f, 0));
        FinishGamePanel finishGamePanel = new FinishGamePanel(isVictory());
        finishGamePanel.setVisible(true);
        finishGamePanel.addListener(this);
        finishGamePanel.setLocationRelativeTo(this);
        finishGamePanel.addEventButtonListener(this.home.getEventSoundButton());
        this.setEnabled(false);
    }

    @Override
    public boolean isReviewMode() {
        return state instanceof ReviewState;
    }


    @Override
    public void back() {
        this.setVisible(false);
        home.reDrawHome();
        home.playStartGameMusic();
        home.setVisible(true);
    }

    @Override
    public void setHint() {
        if (state instanceof PlayingState) {
            state = new HintState(this);
        } else {
            state = new PlayingState(this);
        }
    }

    public boolean isFinished() {
        return state instanceof FinishState || state instanceof ReviewState;
    }

    public void setHome(Home home) {
        this.home = home;
        statusPanel.addEventButtonListener(home.getEventSoundButton());
    }

    public boolean isVictory() {
        return mineGrid.isVictory();
    }

    public int getTime() {
        return statusPanel.getTime();
    }

    @Override
    public void closeFinishGame() {
        setEnabled(true);
        frontPn.setVisible(false);
        requestFocus();
    }

    @Override
    public void reGame() {
        home.playInGameMusic();
        dispose();
        switch (gameMode) {
            case 1:
                home.startGame(GameDifficulty.BEGINNER);
                break;
            case 2:
                home.startGame(GameDifficulty.INTERMEDIATE);
                break;
            case 3:
                home.startGame(GameDifficulty.EXPERT);
                break;
            case 4:
                home.startGame(GameDifficulty.TRIANGLE);
                break;
            case 0:
                System.out.println("?");
//                home.startCustomGame(num_rows, num_columns, num_bombs);
                break;
        }
    }

    @Override
    public void reviewMode() {
        state = new ReviewState(this);
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