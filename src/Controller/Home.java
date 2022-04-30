package Controller;

import Interfaces.IHomeListener;
import Interfaces.IStartGameListener;
import Models.GameDifficulty;
import Views.HomePanel;
import Interfaces.IPanel;
import Views.NewGamePanel;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame implements IPanel, IHomeListener, IStartGameListener {
    private HomePanel homePanel;
    private NewGamePanel newGameMenu;
    private MineSweeperGame mineSweeperGame;

    public Home(){
        init();
        addView();
        addEvent();
    }


    @Override
    public void init() {
        setSize(400,600);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        homePanel = new HomePanel();
        homePanel.setBounds(0,0,400,600);
        homePanel.setBackground(new Color(239,235,232));
        homePanel.addListener(this);

        newGameMenu = new NewGamePanel();
        newGameMenu.setBounds(50,160, 300,150);
        newGameMenu.setBackground(new Color(239,235,232));
        newGameMenu.setForeground(new Color(104,159,57));
        newGameMenu.setVisible(false);
        newGameMenu.addListener(this);


    }
    @Override
    public void addView() {
        add(newGameMenu);
        add(homePanel);
    }

    @Override
    public void addEvent() {
    }

    @Override
    public void continueGame() {
        setVisible(false);
        mineSweeperGame.setVisible(true);
    }

    @Override
    public void reDrawHome() {
        homePanel.addView();
        closeGameMenu();
    }

    @Override
    public boolean isGameFinish() {
        if (mineSweeperGame == null) return false;
        return mineSweeperGame.isFinished();
    }

    @Override
    public void openGameMenu(int x, int y) {
        newGameMenu.setBounds(x,y,300,150);
        newGameMenu.setVisible(true);
    }

    @Override
    public void closeGameMenu() {
        newGameMenu.setVisible(false);
    }

    @Override
    public boolean isGameMenuOpen() {
        return newGameMenu.isVisible();
    }

    @Override
    public void startGame(GameDifficulty gameDifficulty) {
        if(mineSweeperGame != null){
            mineSweeperGame.dispose();
        }
         mineSweeperGame = switch (gameDifficulty){
             case BEGINNER -> new MineSweeperGame(9,9,10);
             case INTERMEDIATE -> new MineSweeperGame(16,16,40);
             case EXPERT -> new MineSweeperGame(24,24,99);
             default -> new MineSweeperGame(0,0,0);
        };
        reDrawHome();
        this.setVisible(false);
        mineSweeperGame.setHome(this);
        mineSweeperGame.setVisible(true);
    }

    @Override
    public void closeHomePanel() {
        this.setVisible(false);
    }
}
