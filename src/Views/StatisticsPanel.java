package Views;

import Controller.Statistics;
import Interfaces.IPanel;
import Interfaces.IStatisticPanelListener;
import Models.Player;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static Views.custom.Theme.*;

public class StatisticsPanel extends JPanel implements IPanel {
    IStatisticPanelListener listener;
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
    JLabel modeLb;
    JLabel mode;
    JLabel backBtn;
    JLabel windowName;
    JLabel deleteBtn;

    Player player;
    public StatisticsPanel(Player player){
        this.player = player;
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        setLayout(null);
        setBackground(FOREGROUND);

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
        modeLb = new JLabel();
        mode = new JLabel();
        backBtn = new JLabel();
        windowName = new JLabel();
        deleteBtn = new JLabel();
        displayPn = new JPanel();
        displayPn.setLayout(new GridLayout(7,2));

    }

    @Override
    public void addView() {

        createJLabelView(modeLb, "BEGINNER", JLabel.LEFT);
        createJLabelView(mode, ">" , JLabel.LEFT    );
        createJLabelView(gamesLb, "Games", JLabel.LEFT);
        createJLabelView(games, String.valueOf(player.totalGames[0]), JLabel.RIGHT);
        createJLabelView(minesLb, "Mines", JLabel.LEFT);
        createJLabelView(mines, String.valueOf(player.totalBomb), JLabel.RIGHT);
        createJLabelView(totalTimeLb, "Total Time", JLabel.LEFT);
        createJLabelView(totalTime, "0:0", JLabel.RIGHT);
        createJLabelView(minTimeLb, "Shortest Time", JLabel.LEFT);
        createJLabelView(minTime, "0:0", JLabel.RIGHT);
        createJLabelView(performLb,"Performance", JLabel.LEFT);
        createJLabelView(perform, "0%", JLabel.RIGHT);
        createJLabelView(victoryLb, "Victory", JLabel.LEFT);
        createJLabelView(victory, "0", JLabel.RIGHT);
        createJLabelView(backBtn, BACK, JLabel.CENTER);
        viewPlayerRecord(1);

        backBtn.setText(BACK);
        backBtn.setFont(new Font("VNI", Font.PLAIN, 30));
        backBtn.setForeground(BACKGROUND);

        deleteBtn.setText(BIN);
        deleteBtn.setFont(new Font("VNI", Font.PLAIN, 30));
        deleteBtn.setForeground(BACKGROUND);

        windowName.setText("STATISTICS");
        windowName.setFont(new Font("VNI", Font.PLAIN, 30));
        windowName.setForeground(BACKGROUND);


        backBtn.setBounds(10,10,30,30);
        windowName.setBounds(70,10,200,30);
        deleteBtn.setBounds(460,10,100,30);

        add(backBtn);
        add(windowName);
        add(deleteBtn);
        displayPn.add(modeLb);
        displayPn.add(mode);
        displayPn.add(gamesLb);
        displayPn.add(games);
        displayPn.add(minesLb);
        displayPn.add(mines);
        displayPn.add(totalTimeLb);
        displayPn.add(totalTime);
        displayPn.add(minTimeLb);
        displayPn.add(minTime);
        displayPn.add(performLb);
        displayPn.add(perform);
        displayPn.add(victoryLb);
        displayPn.add(victory);

        displayPn.setBounds(20,80, Statistics.WIDTH - 2*20,Statistics.HEIGHT - 80 - 20);
        displayPn.setBackground(FOREGROUND);
        displayPn.setForeground(BACKGROUND);
        add(displayPn);

    }

    @Override
    public void addEvent() {
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listener.back();

            }
        });
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        mode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

    }
    private void createJLabelView(JLabel jLabel, String text, int alignment){
        Font font = new Font("VNI", Font.PLAIN, 20);
        jLabel.setText(text);
        jLabel.setFont(font);
        jLabel.setHorizontalAlignment(alignment);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setOpaque(true);
        jLabel.setBackground(FOREGROUND);
        jLabel.setForeground(BACKGROUND);
    }
    public void addListener(IStatisticPanelListener listener){
        this.listener = listener;
    }
    public void viewPlayerRecord(int gameMode){
        games.setText(String.valueOf(player.totalGames[gameMode]));
        mines.setText(String.valueOf(player.totalBomb[gameMode]));
        totalTime.setText(player.totalTime[gameMode]/60 + ":" + player.totalTime[gameMode]%60);
        minTime.setText(player.shortestFinishTime[gameMode]/60 + ":" + player.shortestFinishTime[gameMode]%60);
        perform.setText(player.performance[gameMode]*100 + "%");
        victory.setText(String.valueOf(player.totalVictoryGame[gameMode]));
    }
}
