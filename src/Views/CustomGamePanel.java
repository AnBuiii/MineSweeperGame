package Views;

import Interfaces.ICustomGameListener;
import Interfaces.IPanel;
import Views.custom.RoundedButton;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.BACKGROUND;
import static Views.custom.Theme.FOREGROUND;

public class CustomGamePanel extends JDialog implements IPanel {
    ICustomGameListener listener;
    JLabel closeBtn;
    JLabel newGameLb;
    JLabel rowLb;
    JLabel columnLb;
    JLabel mineLb;
    JTextField rowTf;
    JTextField columnTf;
    JTextField mineTf;
    RoundedButton playBtn;

    public CustomGamePanel(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        getContentPane().setBackground(BACKGROUND);
        setSize(400, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeBtn = new JLabel("x");
        newGameLb = new JLabel("New Game");
        rowLb = new JLabel("Rows");
        columnLb = new JLabel("Columns");
        mineLb = new JLabel("Mines");
        rowTf = new JTextField("9");
        columnTf = new JTextField("9");
        mineTf = new JTextField("9");
        playBtn = new RoundedButton(10);
    }

    @Override
    public void addView() {
        closeBtn.setBounds(370,0,30,30);
        closeBtn.setFont(new Font("VNI", Font.PLAIN, 20));
        add(closeBtn);

//        newGameLb.setBounds(10,10,100,30);
//        newGameLb.setFont(new Font("VNI", Font.PLAIN, 20));
//        add(newGameLb);

        rowLb.setBounds(100,15,100,30);
        rowLb.setFont(new Font("VNI", Font.PLAIN, 20));
        add(rowLb);

        rowTf.setBounds(100,50,50,30);
        rowTf.setBackground(BACKGROUND);
        rowTf.setHorizontalAlignment(SwingConstants.CENTER);
        rowTf.setFont(new Font("VNI", Font.PLAIN, 20));
        add(rowTf);

        columnLb.setBounds(250,15,100,30);
        columnLb.setFont(new Font("VNI", Font.PLAIN, 20));
        add(columnLb);

        columnTf.setBounds(250,50,50,30);
        columnTf.setFont(new Font("VNI", Font.PLAIN, 20));
        columnTf.setBackground(BACKGROUND);
        columnTf.setHorizontalAlignment(SwingConstants.CENTER);
        add(columnTf);

        mineLb.setBounds(175,100,100,30);
        mineLb.setFont(new Font("VNI", Font.PLAIN, 20));
        add(mineLb);

        mineTf.setBounds(175,135,50,30);
        mineTf.setFont(new Font("VNI", Font.PLAIN, 20));
        mineTf.setBackground(BACKGROUND);
        mineTf.setHorizontalAlignment(SwingConstants.CENTER);
        add(mineTf);

        playBtn.setBounds(150,200,100,50);
        playBtn.setBackground(BACKGROUND);
        playBtn.setFont(new Font("VNI", Font.BOLD, 25));
        playBtn.setText("Play");
        playBtn.setHorizontalAlignment(SwingConstants.CENTER);
        add(playBtn);

//        newGameLb.setBounds(10,10,100,30);
//        newGameLb.setFont(new Font("VNI", Font.PLAIN, 20));
    }

    @Override
    public void addEvent() {
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
//                eventButton.playSoundHoverButton();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                eventButton.playSoundClickButton();
                dispose();
                listener.closeCustomPanel();
            }
        });
        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listener.startCustomGame(Integer.parseInt(rowTf.getText()), Integer.parseInt(columnTf.getText()), Integer.parseInt(mineTf.getText()));
                dispose();
                listener.closeCustomPanel();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                playBtn.setBackground(FOREGROUND);
                playBtn.setForeground(BACKGROUND);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                playBtn.setForeground(FOREGROUND);
                playBtn.setBackground(BACKGROUND);
            }
        });
    }
    public void addListener(ICustomGameListener listener){
        this.listener = listener;
    }

}
