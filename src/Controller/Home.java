package Controller;

import DesignPattern.GameBuilder.Director;
import DesignPattern.GameBuilder.MineSweeperGameBuilder;
import Interfaces.*;
import Models.GameDifficulty;

import Models.Music;
import Models.Player;
import Views.CustomGamePanel;
import Views.HomePanel;
import Views.ToolBarPanel;
import Views.NewGamePanel;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.io.*;

import static Views.custom.Theme.*;

public class Home extends JFrame implements IPanel, IHomeListener, IStartGameListener, ISoundEventButton, ICustomGameListener, IToolBarListener {

    private ToolBarPanel toolBarPanel;
    private HomePanel homePanel;
    private NewGamePanel newGameMenu;
    private MineSweeperGame mineSweeperGame;
    private Player player;

    private JPanel opacity;
    public Music musicGame = new Music();
    ;
    private Clip musicPlayer;

    private Director director;

    public Home() {
        init();
        addView();
        addEvent();
        homePanel.addView();
        playStartGameMusic();
    }


    @Override
    public void init() {
        setSize(400, 640);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), ARCW_FORM, ARCH_FORM));


//        try {
//            FileInputStream fileIn = new FileInputStream("oldGame.txt");
//            ObjectInputStream ojIn = new ObjectInputStream(fileIn);
//            MineSweeperGame obj = (MineSweeperGame) ojIn.readObject();
//
//            mineSweeperGame = new MineSweeperGame(obj);
//            mineSweeperGame.setHome(this);
//            mineSweeperGame.dispose();
//            ojIn.close();
//            fileIn.close();
//        } catch (Exception ex) {
//            mineSweeperGame = null;
//        }

        try {
            FileInputStream fileIn = new FileInputStream("PlayerRecord.txt");
            ObjectInputStream ojIn = new ObjectInputStream(fileIn);
            Player obj = (Player) ojIn.readObject();
            player = new Player(obj);
            ojIn.close();
            fileIn.close();
            System.out.println(player.totalGames[1]);
        } catch (Exception ex) {
            player = new Player();
        }

    }

    @Override
    public void addView() {

        opacity = new JPanel();
        opacity.setBounds(0, 0, getWidth(), getHeight());
        opacity.setVisible(false);
        opacity.setBackground(new Color(0, 0, 0, 100));

        toolBarPanel = new ToolBarPanel();
        toolBarPanel.setBounds(0, 0, 400, 40);
        toolBarPanel.setBackground(BACKGROUND);
        toolBarPanel.addListener(this);
        toolBarPanel.addEventButtonListener(this);
        toolBarPanel.setHome(this);


        homePanel = new HomePanel();
        homePanel.setBounds(0, toolBarPanel.getY(), 400, this.getHeight() - toolBarPanel.getHeight());
        homePanel.setBackground(new Color(239, 235, 232));
        homePanel.addListener(this);
        homePanel.addEventButtonListener(this);

        newGameMenu = new NewGamePanel();
        newGameMenu.setBounds(50, 160 + toolBarPanel.getY(), 300, 150);
        newGameMenu.setBackground(new Color(239, 235, 232));
        newGameMenu.setForeground(new Color(104, 159, 57));
        newGameMenu.setVisible(false);
        newGameMenu.addListener(this);
        newGameMenu.addEventButtonListener(this);

        add(opacity);
        add(toolBarPanel);
        add(newGameMenu);
        add(homePanel);

    }


    @Override
    public void addEvent() {

        WindowListener wd = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                try {
//                    closeMusic();
//                    MineSweeperGame save;
//                    FileOutputStream fileOut = new FileOutputStream("oldGame.txt");
//                    ObjectOutputStream ojOut = new ObjectOutputStream(fileOut);
//                    if(mineSweeperGame!=null)
//                        if(!mineSweeperGame.isFinished()){
//                            save = mineSweeperGame;
//                            ojOut.writeObject(save);
//                            ojOut.close();
//                            fileOut.close();
//                        }
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
                System.exit(1);
            }
        };
        addWindowListener(wd);
    }

    @Override
    public void continueGame() {
        setVisible(false);
        playInGameMusic();
        if (mineSweeperGame != null) {
            mineSweeperGame.setVisible(true);
            mineSweeperGame.startClock();
        }
    }

    @Override
    public void reDrawHome() {

        homePanel.addView();
        newGameMenu.setVisible(false);
//        closeGameMenu();
    }

    @Override
    public boolean isGameFinish() {
        if (mineSweeperGame != null) {
            return mineSweeperGame.isFinished();
        }
        return true;
    }

    @Override
    public void openSetting() {
        setDim();
        setEnabled(false);
        Setting setting = new Setting();
        setting.setLocationRelativeTo(this);
        setting.setVisible(true);
        setting.setHome(this);
        //  this.setVisible(false);
    }

    @Override
    public void openStatistic() {
        Statistics statistics = new Statistics(player);
        statistics.setLocationRelativeTo(this);
        statistics.setVisible(true);
        statistics.setHome(this);
        this.setVisible(false);
    }

    public void openTutorial() {
        Tutorial tutorial = new Tutorial();
        tutorial.setLocationRelativeTo(this);
        tutorial.setVisible(true);
        tutorial.setHome(this);
        this.setVisible(false);
    }


    @Override
    public NewGamePanel getNewGameMenu() {
        return newGameMenu;
    }

    @Override
    public void startGame(GameDifficulty gameDifficulty) {
        if (mineSweeperGame != null) {
            mineSweeperGame.dispose();
            mineSweeperGame.killClock();
            mineSweeperGame = null;
        }

        director = new Director(gameDifficulty);
        MineSweeperGameBuilder builder = new MineSweeperGameBuilder();
        director.constructGame(builder);
        mineSweeperGame = builder.build();

        reDrawHome();
        this.setVisible(false);
        mineSweeperGame.setHome(this);
        mineSweeperGame.setVisible(true);
        playInGameMusic();
    }

    public void savingData(MineSweeperGame game) {
        game.killClock();
        System.out.println("saving...");
        if (player == null) {
            player = new Player();
        }
        player.totalGames[4]++;
        player.totalBomb[4] += game.num_bombs;
        player.totalTime[4] += game.getTime();
        if (game.isVictory() && (game.getTime() < player.shortestFinishTime[4])) {
            player.shortestFinishTime[4] = game.getTime();
        }
        if (game.isVictory()) player.totalVictoryGame[4]++;
        player.performance[4] = (float) player.totalVictoryGame[4] / (float) player.totalGames[4];

        try {
            FileOutputStream fileOut = new FileOutputStream("PlayerRecord.txt");
            ObjectOutputStream ojOut = new ObjectOutputStream(fileOut);
            ojOut.writeObject(player);
            ojOut.close();
            fileOut.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void closeHomePanel() {
        this.setVisible(false);
    }

    @Override
    public void openCustomPanel() {
        CustomGamePanel customGamePanel = new CustomGamePanel();
        customGamePanel.setVisible(true);
        customGamePanel.addListener(this);
        this.setEnabled(false);
    }

//    public void savingData(MineSweeperGame game) {
//
//        if (!game.isFinished()) return;
//        game.killClock();
//        System.out.println("saving...");
//        if (player == null) {
//            player = new Player();
//        }
//        player.totalGames[game.gameMode]++;
//        player.totalBomb[game.gameMode] += game.num_bombs;
//        player.totalTime[game.gameMode] += game.getTime();
//        if (player.shortestFinishTime[game.gameMode] == 0) player.shortestFinishTime[game.gameMode] = game.getTime();
//        else if (game.isVictory() && (game.getTime() < player.shortestFinishTime[game.gameMode])) {
//            player.shortestFinishTime[game.gameMode] = game.getTime();
//        }
//
//        if (game.isVictory()) player.totalVictoryGame[game.gameMode]++;
//        player.performance[game.gameMode] = (float) player.totalVictoryGame[game.gameMode] / (float) player.totalGames[game.gameMode];
//
//        try {
//            FileOutputStream fileOut = new FileOutputStream("PlayerRecord.txt");
//            ObjectOutputStream ojOut = new ObjectOutputStream(fileOut);
//            ojOut.writeObject(player);
//            ojOut.close();
//            fileOut.close();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }

    public void closeMusic() {
        musicGame.closeMusic();
    }

    public void playInGameMusic() {
        //play in game music
        if (musicGame.startGameMusic != null) {
            musicGame.startGameMusic.stop();
        }
        musicGame.startMusicGame(musicGame.inGameMusic);
    }

    public void playStartGameMusic() {

        if (musicGame.inGameMusic != null) {
            musicGame.inGameMusic.stop();
        }
        musicGame.startMusicGame(musicGame.startGameMusic);

    }

    public void playSoundClickCell() {
        musicGame.startSoundEffect(musicGame.soundClickCell);
    }

    public void playSoundSocketFlag() {
        musicGame.startSoundEffect(musicGame.soundSocketFlag);
    }

    public void playSoundLoseGame() {
        musicGame.inGameMusic.stop();
        musicGame.startSoundEffect(musicGame.soundLoseGame);
    }

    public void playSoundWinGame() {
        musicGame.inGameMusic.stop();
        musicGame.startSoundEffect(musicGame.soundWinGame);
    }

    @Override
    public void playSoundHoverButton() {
        musicGame.startSoundEffect(musicGame.soundHoverButton);

    }

    @Override
    public void playSoundClickButton() {
        musicGame.startSoundEffect(musicGame.soundClickButton);
    }

    public ISoundEventButton getEventSoundButton() {
        return this;
    }

    @Override
    public void closeCustomPanel() {
        this.setEnabled(true);
        requestFocus();
    }

    @Override
    public void startCustomGame(int rows, int columns, int mines) {
        if (mineSweeperGame != null) {
            mineSweeperGame.dispose();
            mineSweeperGame.killClock();
        }
//        mineSweeperGame = new MineSweeperGame(rows, columns, mines, 0);
//        reDrawHome();
//        this.setVisible(false);
//        mineSweeperGame.setHome(this);
//        mineSweeperGame.setVisible(true);
//        playInGameMusic();
    }

    public void deletePlayer() {
        player = new Player();
        try {
            FileOutputStream fileOut = new FileOutputStream("PlayerRecord.txt");
            ObjectOutputStream ojOut = new ObjectOutputStream(fileOut);
            ojOut.writeObject(player);
            ojOut.close();
            fileOut.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


//        Statistics statistics = new Statistics(player);
//        statistics.setVisible(true);
//        statistics.setHome(this);
//        statistics.setLocationRelativeTo(this);
        openStatistic();
    }

    public void setDim() {
        opacity.setVisible(!opacity.isVisible());
    }

    public void unTargetSettingLb() {
        homePanel.reTargetSettingLb();

    }

    @Override
    public void minimizeWindow() {
        this.setState(Frame.ICONIFIED);
    }

    @Override
    public void quitGame() {
        System.exit(0);
    }
}
