package Views;

import Interfaces.IPanel;
import Views.custom.RoundedBorder;
import Views.custom.Theme;

import javax.swing.*;

import java.awt.*;
import java.util.jar.JarEntry;

import static Views.HomePanel.WINDOW_HEIGHT;
import static Views.HomePanel.WINDOW_WIDTH;

public class StatisticsPanel extends JPanel implements IPanel {
    JComboBox<String> gameMode ;
    JPanel displayPn;

    JLabel gamesLb;
    JLabel games;
    JLabel minesLb;
    JLabel mines;
    JLabel totalTimeLb;
    JLabel totalTime;
    JLabel minTimeLb;
    JLabel minTime;
    JLabel performLb;
    JLabel perform;
    JLabel victoryLb;
    JLabel victory;

    public StatisticsPanel(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        setLayout(null);
        setBackground(Theme.FOREGROUND);

        String[] header = {"BEGINNER", "INTERMEDIATE", "EXPERT", "TRIANGLE"};
        gameMode = new JComboBox<>(header);

        gamesLb = new JLabel();
        games = new JLabel();
        minesLb = new JLabel();
        mines = new JLabel();
        totalTimeLb = new JLabel();
        totalTime = new JLabel();
        minTimeLb = new JLabel();
        minTime = new JLabel();
        performLb = new JLabel();
        perform = new JLabel();
        victoryLb = new JLabel();
        victory = new JLabel();

        displayPn = new JPanel();
        displayPn.setLayout(new GridLayout(6,2));
        displayPn.setBorder(new RoundedBorder(10));
    }

    @Override
    public void addView() {
        gameMode.setBounds(100,100,150,20);
        gameMode.setFont(Theme.FONT);
        gameMode.setForeground(Theme.BACKGROUND);
        add(gameMode);

        displayPn.setBounds(20,200,300,150);
        displayPn.setBackground(Theme.FOREGROUND);
        displayPn.setForeground(Theme.BACKGROUND);
        add(displayPn);
    }

    @Override
    public void addEvent() {

    }
}
