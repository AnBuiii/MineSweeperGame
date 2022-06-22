package Controller;

import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Interfaces.IStatisticPanelListener;
import Interfaces.IStatusPanelListener;
import Models.Player;
import Views.StatisticsPanel;

import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;

import static Views.custom.Theme.BOMB;
import static Views.custom.Theme.TITLE;

public class Statistics extends JFrame implements IPanel, IStatisticPanelListener {
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
    public Home home;
    Player player;
    private StatisticsPanel statisticspanel;


    Statistics(Player player){
        this.player = player;
        init();
        addView();
        addEvent();

    }

    @Override
    public void init() {
        setSize(WIDTH, HEIGHT);
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
        statisticspanel = new StatisticsPanel(player);
        statisticspanel.setBounds(0,0,WIDTH,HEIGHT);
        statisticspanel.addListener(this);
        add(statisticspanel);
    }

    @Override
    public void addEvent() {

    }

    @Override
    public void back() {
        this.setVisible(false);
        home.reDrawHome();
        home.setVisible(true);
    }

    @Override
    public void delete(int mode) {

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    public void setHome(Home home){
        this.home = home;
        statisticspanel.addEventButtonListener(home.getSoundEventButton());
    }

   /* @Override
    public void playSoundHoverButton() {
        Music musicGame = new Music();
        Clip soundHoverCell = musicGame.SoundHoverButton();
        soundHoverCell.start();

    }

    @Override
    public void playSoundClickButton() {
        Music musicGame = new Music();
        Clip soundClickCell = musicGame.SoundClickButton();
        soundClickCell.start();
    }*/
}
