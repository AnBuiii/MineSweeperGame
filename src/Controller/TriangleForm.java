package Controller;

import Interfaces.IHomeListener;
import Interfaces.IPanel;
import Interfaces.IStartGameListener;
import Models.Cell;
import Models.GameDifficulty;
import Views.HomePanel;
import Views.NewGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

public class TriangleForm extends JFrame implements IPanel, IHomeListener, IStartGameListener {
    private HomePanel homePanel;
    private NewGamePanel newGameMenu;
    private MineTriangleSweeperGame mineTriangleSweeperGame;

    public TriangleForm(){
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

//        try {
//            FileInputStream fileIn = new FileInputStream("oldgame.txt");
//            ObjectInputStream ojIn = new ObjectInputStream(fileIn);
//            MineTriangleSweeperGame obj = (MineTriangleSweeperGame) ojIn.readObject();
//            mineTriangleSweeperGame = new MineTriangleSweeperGame(obj);
//            mineTriangleSweeperGame.setTriangleForm(this);
//            ojIn.close();
//            fileIn.close();
//
//        } catch (Exception ex) {
//
//            mineTriangleSweeperGame = null;
//        }

        add(newGameMenu);
        add(homePanel);


    }
    @Override
    public void addView() {

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
                    if(mineTriangleSweeperGame !=null) if(!mineTriangleSweeperGame.isFinished()){
                        cells = mineTriangleSweeperGame.getListCell();
                       // save = mineTriangleSweeperGame;
                    }
                    ojOut.writeObject(save);
                    ojOut.close();
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        };
        addWindowListener(wd);
    }

    @Override
    public void continueGame() {
        setVisible(false);
        mineTriangleSweeperGame.setVisible(true);
    }

    @Override
    public void reDrawHome() {
        homePanel.addView();
        newGameMenu.setVisible(false);
//        closeGameMenu();
    }

    @Override
    public boolean isGameFinish() {
        if (mineTriangleSweeperGame == null) return true;
        return mineTriangleSweeperGame.isFinished();
    }

    @Override
    public NewGamePanel getNewGameMenu() {
        return newGameMenu;
    }

    @Override
    public void startGame(GameDifficulty gameDifficulty) {
        if(mineTriangleSweeperGame != null){
            mineTriangleSweeperGame.dispose();
        }
        mineTriangleSweeperGame = switch (gameDifficulty){
            case BEGINNER -> new MineTriangleSweeperGame(16, 31, 40, 2);
            default -> throw new IllegalStateException("Unexpected value: " + gameDifficulty);
        };
        reDrawHome();
        this.setVisible(false);
        mineTriangleSweeperGame.setTriangleForm(this);
        mineTriangleSweeperGame.setVisible(true);
    }

    @Override
    public void closeHomePanel() {
        this.setVisible(false);
    }
}
