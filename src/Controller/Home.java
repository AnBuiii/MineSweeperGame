package Controller;

import Interfaces.IHomeListener;
import Interfaces.ISoundEventButton;
import Interfaces.IStartGameListener;
import Models.GameDifficulty;

import Models.Player;
import Views.HomePanel;
import Interfaces.IPanel;
import Views.NewGamePanel;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

public class Home extends JFrame implements IPanel, IHomeListener, IStartGameListener, ISoundEventButton {
    private HomePanel   homePanel;
    private NewGamePanel newGameMenu;
    private MineSweeperGame mineSweeperGame;
    private MineTriangleSweeperGame mineTriangleSweeperGame;
    private Player player;

    private Music musicGame = new Music();;
    private Clip musicPlayer;

    public Home(){
        init();
        addView();
        addEvent();
        homePanel.addView();
        playStartGameMusic();
    }


    @Override
    public void init() {
         setSize(400,600);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        try {
            FileInputStream fileIn = new FileInputStream("oldGame.txt");
            ObjectInputStream ojIn = new ObjectInputStream(fileIn);
            MineSweeperGame obj = (MineSweeperGame) ojIn.readObject();

            mineSweeperGame = new MineSweeperGame(obj);
            mineSweeperGame.setHome(this);
            mineSweeperGame.dispose();
            ojIn.close();
            fileIn.close();
        } catch (Exception ex) {
            mineSweeperGame = null;
        }

        try{
            FileInputStream fileIn = new FileInputStream("PlayerRecord.txt");
            ObjectInputStream ojIn = new ObjectInputStream(fileIn);
            Player obj = (Player) ojIn.readObject();
            player = new Player(obj);
            ojIn.close();
            fileIn.close();
            System.out.println(player.totalGames[1]);
        } catch (Exception ex){
            player = new Player();
        }

    }
    @Override
    public void addView() {

        homePanel = new HomePanel();
        homePanel.setBounds(0,0,400,600);
        homePanel.setBackground(new Color(239,235,232));
        homePanel.addListener(this);
        homePanel.addEventButtonListener(this);

        newGameMenu = new NewGamePanel();
        newGameMenu.setBounds(50,160, 300,150);
        newGameMenu.setBackground(new Color(239,235,232));
        newGameMenu.setForeground(new Color(104,159,57));
        newGameMenu.setVisible(false);
        newGameMenu.addListener(this);
        newGameMenu.addEventButtonListener(this);

        add(newGameMenu);
        add(homePanel);

    }

    @Override
    public void addEvent() {
        WindowListener wd = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                try {
                    closeMusic();
                    MineSweeperGame save;
                    FileOutputStream fileOut = new FileOutputStream("oldGame.txt");
                    ObjectOutputStream ojOut = new ObjectOutputStream(fileOut);
                    if(mineSweeperGame!=null)
                        if(!mineSweeperGame.isFinished()){
                            save = mineSweeperGame;
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
        playInGameMusic();
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
    public void openSetting() {
      Setting setting = new Setting();
        setting.setVisible(true);
        setting.setHome(this);
        setting.setBounds(this.getBounds());
        this.setVisible(false);
    }

    @Override
    public void openStatistic() {
        Statistics statistics = new Statistics(player);
        statistics.setVisible(true);
        statistics.setHome(this);
        this.setVisible(false);
    }
    public void openTutorial() {
        Tutorial tutorial = new Tutorial();
        tutorial.setVisible(true);
        tutorial.setHome(this);
        this.setVisible(false);
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
        mineTriangleSweeperGame = new MineTriangleSweeperGame(16, 31, 50); // nhớ nhập số cột là số lẻ mới vẽ đúng được

        reDrawHome();
        this.setVisible(false);
        mineTriangleSweeperGame.setHome(this);
        mineTriangleSweeperGame.setVisible(true);
        playInGameMusic();
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
             case BEGINNER -> new MineSweeperGame(9,9,10);
             case INTERMEDIATE -> new MineSweeperGame(16,16,40);
             case EXPERT -> new MineSweeperGame(16,30,99);
             default -> new MineSweeperGame(0,0,0);
        };

        reDrawHome();
        this.setVisible(false);
        mineSweeperGame.setHome(this);
        mineSweeperGame.setVisible(true);
        playInGameMusic();

    }

    @Override
    public void closeHomePanel() {
        this.setVisible(false);
    }
    public void savingData(MineSweeperGame game){
        if(!game.isFinished()) return;
        System.out.println("saving...");
        if(player == null){
            player = new Player();
        }
        player.totalGames[game.gameMode]++;
        player.totalBomb[game.gameMode] += game.num_bombs;
        player.totalTime[game.gameMode] += game.getTime();
        if(game.isVictory() && (game.getTime() < player.shortestFinishTime[game.gameMode])){
            player.shortestFinishTime[game.gameMode] = game.getTime();
        }
        if(game.isVictory()) player.totalVictoryGame[game.gameMode]++;
        player.performance[game.gameMode] = (float) player.totalVictoryGame[game.gameMode]/(float) player.totalGames[game.gameMode];

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

    public void closeMusic(){
        if(musicPlayer!= null &&  (musicPlayer.isOpen() || musicPlayer.isActive() || musicPlayer.isRunning())){
            musicPlayer.stop();
            musicPlayer.close();
        }
        musicPlayer = null;
    }
    public void playInGameMusic(){
        //play in game music
        closeMusic();
        musicPlayer = musicGame.InGameMusic();
        musicPlayer.start();
    }
    public void playStartGameMusic(){
        closeMusic();
        musicPlayer = musicGame.startGameMusic();
        musicPlayer.start();
    }
    public void playSoundClickCell(){
        Clip soundClickCell = musicGame.SoundClickCell();
        soundClickCell.start();
    }

    public void playSoundSocketFlag(){
        Clip soundClickCell = musicGame.SoundSocketFlag();
        soundClickCell.start();
    }
    public void playSoundLoseGame(){
        closeMusic();
        musicPlayer = musicGame.SoundLoseGame();
        musicPlayer.start();
    }

    public void playSoundWinGame(){
        closeMusic();
        musicPlayer = musicGame.SoundWinGame();
        musicPlayer.start();
    }

    @Override
    public void playSoundHoverButton() {
       Clip soundHoverCell = musicGame.SoundHoverButton();
            soundHoverCell.start();

    }

    @Override
    public void playSoundClickButton() {
        Clip soundClickCell = musicGame.SoundClickButton();
            soundClickCell.start();
    }
    public ISoundEventButton getEventSoundButton(){
        return this;
    }
}
