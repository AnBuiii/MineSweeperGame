package Controller;

import Interfaces.IPanel;
import Models.Player;
import Views.StatisticsPanel;

import javax.swing.*;

import static Views.custom.Theme.BOMB;
import static Views.custom.Theme.TITLE;

public class Statistics extends JFrame implements IPanel {
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
        setSize(500, 800);
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
        statisticspanel = new StatisticsPanel();
        statisticspanel.setBounds(0,0,500,800);
        add(statisticspanel);
    }

    @Override
    public void addEvent() {

    }
}
