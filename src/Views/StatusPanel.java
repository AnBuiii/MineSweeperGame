package Views;

import Controller.MineSweeperGame;
import Interfaces.IPanel;
import Interfaces.IStatusPanelListener;
import Models.GameDifficulty;
import Models.MineGrid;
import Views.custom.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.lang.Thread;
import static Views.custom.Theme.*;

public class StatusPanel extends JPanel implements IPanel {
    private JLabel lbNumCellUnrevealed;
    private JLabel lbNotify;
    private JButton btnRestart;
    private JLabel backBtn;
    private JLabel clockLb;
    private JLabel timeLb;
    private JLabel flagLb;
    private JLabel numFlagLb;
    private JLabel hintBtn;

    private GridBagConstraints gbc;

    private IStatusPanelListener listener;
    public int time;
    public long sec;

    public StatusPanel() {
        init();
        addView();
        addEvent();
    }
    public class Clock extends Thread{
        public Clock(){	}
        public void run() {
            while (true) {
                sec++;
                String S = String.valueOf(sec);
                timeLb.setText(S);
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
    @Override
    public void init() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND);
        backBtn = new JLabel(BACK);
        flagLb = new JLabel(FLAG);
        //numFlagLb = new JLabel("00");
        String mine = String.valueOf(MineGrid.num_mines);
        numFlagLb = new JLabel(mine);
        clockLb = new JLabel(CLOCK);
        timeLb = new JLabel();
        Clock clock = new Clock();
        clock.start();
        hintBtn = new JLabel(HINT);
        gbc = new GridBagConstraints();
    }
    @Override
    public void addView() {
        //backBtn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        backBtn.setOpaque(true);
        backBtn.setBackground(BACKGROUND);
        backBtn.setFont(new Font("VNI", Font.BOLD, 30));

        //flagLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        flagLb.setOpaque(true);
        flagLb.setBackground(BACKGROUND);
        flagLb.setFont(new Font("VNI", Font.PLAIN, 25));

        //numFlagLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        numFlagLb.setOpaque(true);
        numFlagLb.setBackground(BACKGROUND);
        numFlagLb.setFont(new Font("VNI", Font.PLAIN, 23));

        //clockLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        clockLb.setOpaque(true);
        clockLb.setBackground(BACKGROUND);
        clockLb.setFont(new Font("VNI", Font.PLAIN, 25));

        //timeLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        timeLb.setOpaque(true);
        timeLb.setBackground(BACKGROUND);
        timeLb.setFont(new Font("VNI", Font.PLAIN, 25));

        //hintBtn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        hintBtn.setOpaque(true);
        hintBtn.setBackground(BACKGROUND);
        hintBtn.setFont(new Font("VNI", Font.PLAIN, 25));


        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(backBtn,gbc);

        gbc.gridx = 1;
        add(new JLabel(), gbc);

        gbc.gridx = 2;
        add(flagLb,gbc);

        gbc.gridx = 3;
        add(numFlagLb,gbc);

        gbc.gridx = 4;
        add(clockLb, gbc);

        gbc.gridx = 5;
        add(timeLb, gbc);

        gbc.gridx = 6;
        add(new JLabel(), gbc);

        gbc.gridx = 7;
        add(hintBtn, gbc);


    }

    @Override
    public void addEvent() {
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {

            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listener.back();

            }
        });

    }

    public void addListener(IStatusPanelListener event) {
        listener = event;
    }

    /*public void updateStatus(int numSquareUnrevealed) {
        lbNumCellUnrevealed.setText("Số ô chưa mở: " + numSquareUnrevealed);
        if (numSquareUnrevealed == MineGrid.num_mines) {
            lbNotify.setText("THẮNG");
            lbNotify.setForeground(Color.blue);
       } else if (numSquareUnrevealed == 0) {
            lbNotify.setText("THUA");
            lbNotify.setForeground(Color.red);
       }
    }*/

    public void load() {
        addEvent();
    }
}