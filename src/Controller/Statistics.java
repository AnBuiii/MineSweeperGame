package Controller;

import Interfaces.IPanel;
import Interfaces.IStatisticPanelListener;
import Models.Player;
import Views.StatisticsPanel;

import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

import static Views.custom.Theme.ARCH_FORM;
import static Views.custom.Theme.ARCW_FORM;

public class Statistics extends JFrame implements IPanel, IStatisticPanelListener {
    public static int WIDTH = 500;
    public static int HEIGHT = 500;
    public Home home;
    private Player player;
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
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(),  ARCW_FORM, ARCH_FORM));
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

    @Override
    public void deletePlayer() {
        dispose();
        home.deletePlayer();
    }

    public void setHome(Home home){
        this.home = home;
        statisticspanel.addEventButtonListener(home.getEventSoundButton());
    }

}
