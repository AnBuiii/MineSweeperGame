package Controller;

import Interfaces.IHomeListener;
import Interfaces.IStartGameListener;
import Models.Cell;
import Models.GameDifficulty;

import Views.HomePanel;
import Interfaces.IPanel;
import Views.NewGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

public class Home extends JFrame implements IPanel, IHomeListener, IStartGameListener {
    private HomePanel   homePanel;
    private NewGamePanel newGameMenu;
    private MineSweeperGame mineSweeperGame;

    private MineTriangleSweeperGame mineTriangleSweeperGame;

    public Home(){
        init();
        addView();
        addEvent();
        homePanel.addView();
    }


    @Override
    public void init() {
        setSize(400,600);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        try {
            FileInputStream fileIn = new FileInputStream("oldgame.txt");
            ObjectInputStream ojIn = new ObjectInputStream(fileIn);
            MineSweeperGame obj = (MineSweeperGame) ojIn.readObject();
            mineSweeperGame = new MineSweeperGame(obj);
            mineSweeperGame.setHome(this);
            ojIn.close();
            fileIn.close();


        } catch (Exception ex) {
            mineSweeperGame = null;
        }



    }
    @Override
    public void addView() {
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

        add(newGameMenu);
        add(homePanel);

    }

    @Override
    public void addEvent() {
        WindowListener wd = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Cell[][] cells = null;
                    MineSweeperGame save = null;
                    FileOutputStream fileOut = new FileOutputStream("oldGame.txt");
                    ObjectOutputStream ojOut = new ObjectOutputStream(fileOut);
                    if(mineSweeperGame!=null)
                        if(!mineSweeperGame.isFinished()){
                            cells = mineSweeperGame.getListCell();
                            save = mineSweeperGame;
                            System.out.println("ua");
                            ojOut.writeObject(save);
                            ojOut.close();
                            fileOut.close();
                        }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(1);
            }
        };
        addWindowListener(wd);
    }

    @Override
    public void continueGame() {
        setVisible(false);
        if(mineTriangleSweeperGame != null) mineTriangleSweeperGame.setVisible(true);
        if(mineSweeperGame != null) mineSweeperGame.setVisible(true);
    }

    @Override
    public void reDrawHome() {
        homePanel.addView();
        newGameMenu.setVisible(false);
//        closeGameMenu();
    }

    @Override
    public boolean isGameFinish() {


        if(mineSweeperGame != null){
            return mineSweeperGame.isFinished();
        }
        if(mineTriangleSweeperGame != null){
            return mineTriangleSweeperGame.isFinished();
        }
        return true;
    }

    @Override
    public void startTriangleGame() {
        if(mineSweeperGame != null){
            mineSweeperGame.dispose();
            mineSweeperGame = null;
        }
        if(mineTriangleSweeperGame != null){
            mineTriangleSweeperGame.dispose();
        }
        mineTriangleSweeperGame = new MineTriangleSweeperGame(16, 31, 65, 2);

        reDrawHome();
        this.setVisible(false);
        mineTriangleSweeperGame.setTriangleForm(this);
        mineTriangleSweeperGame.setVisible(true);
    }

    @Override
    public NewGamePanel getNewGameMenu() {
        return newGameMenu;
    }

    @Override
    public void startGame(GameDifficulty gameDifficulty) {
        if(mineTriangleSweeperGame != null){
            mineTriangleSweeperGame.dispose();
            mineTriangleSweeperGame = null;

        }
        if(mineSweeperGame != null){
            mineSweeperGame.dispose();
        }
         mineSweeperGame = switch (gameDifficulty){
             case BEGINNER -> new MineSweeperGame(9,9,10, 1);
             case INTERMEDIATE -> new MineSweeperGame(16,16,40, 1);
             case EXPERT -> new MineSweeperGame(16,30,99, 1);
             default -> new MineSweeperGame(0,0,0, 1);
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
