package Controller;

import Interfaces.IPanel;
import Models.Player;

import javax.swing.*;

import static Views.custom.Theme.BOMB;
import static Views.custom.Theme.TITLE;

public class Statistics extends JFrame implements IPanel {
    Player player;
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

    }

    @Override
    public void addEvent() {

    }
}
