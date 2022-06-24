package Views;

import Interfaces.IFinishGameListener;
import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Views.custom.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import static Views.custom.Theme.*;

public class FinishGamePanel extends JDialog implements IPanel {

     boolean isVictory;
    JLabel closeBtn;
    JLabel result1Lb;
    JLabel result2Lb;
    RoundedButton newGameBtn;
    RoundedButton someThingBtn;
    RoundedButton reviewBtn;

    IFinishGameListener listener;
    ISoundEventButton eventButton;
    public FinishGamePanel(boolean isVictory){
        this.isVictory = isVictory;
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        getContentPane().setBackground(FOREGROUND);
        setSize(200, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLocation(getX(), getY()+10);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(),  ARCW_FORM, ARCH_FORM));
        setLayout(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeBtn = new JLabel(CLOSE);
        result1Lb = new JLabel();
        result2Lb = new JLabel();
        newGameBtn = new RoundedButton(10);
        someThingBtn = new RoundedButton(10);
        reviewBtn = new RoundedButton(10);

    }

    @Override
    public void addView() {
        Font font = new Font("VNI", Font.PLAIN, 20);
        closeBtn.setBounds(170,0,30,30);
        closeBtn.setFont(font);
        add(closeBtn);

        result1Lb.setFont(new Font("VNI", Font.PLAIN, 20));
        result1Lb.setBounds(0,50,200,30);
        result1Lb.setHorizontalAlignment(SwingConstants.CENTER);
        if(isVictory) result1Lb.setText("YOU WON " + SMILE);
        else result1Lb.setText("YOU LOST " + CRY);
        add(result1Lb);

        result2Lb.setFont(new Font("VNI", Font.PLAIN, 13));
        result2Lb.setBounds(0,80,200,30);
        result2Lb.setBackground(BACKGROUND);
        result2Lb.setHorizontalAlignment(SwingConstants.CENTER);
        if(isVictory) result2Lb.setText("Too easy huh!? ");
        else result2Lb.setText("Maybe to hard for you, right?");
        add(result2Lb);

        newGameBtn.setFont(new Font("VNI", Font.PLAIN, 20));
        newGameBtn.setBounds(20,130,160,40);
        newGameBtn.setFocusPainted(false);
        newGameBtn.setText("New Game");
        newGameBtn.setHorizontalAlignment(SwingConstants.CENTER);
        newGameBtn.setBackground(FOREGROUND);
        newGameBtn.setForeground(BACKGROUND);
        add(newGameBtn);

        someThingBtn.setFont(new Font("VNI", Font.PLAIN, 20));
        someThingBtn.setBounds(20,180,160,40);
        someThingBtn.setFocusPainted(false);
        if(isVictory) someThingBtn.setText("Statistics");
        else someThingBtn.setText("Tutorial");
        someThingBtn.setHorizontalAlignment(SwingConstants.CENTER);
        someThingBtn.setBackground(FOREGROUND);
        someThingBtn.setForeground(BACKGROUND);
        add(someThingBtn);

        reviewBtn.setFont(new Font("VNI", Font.PLAIN, 20));
        reviewBtn.setBounds(20,230,160,40);
        reviewBtn.setFocusPainted(false);
        reviewBtn.setText("Review Game");
        reviewBtn.setHorizontalAlignment(SwingConstants.CENTER);
        reviewBtn.setBackground(FOREGROUND);
        reviewBtn.setForeground(BACKGROUND);
        add(reviewBtn);

    }

    @Override
    public void addEvent() {
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                closeBtn.setForeground(Color.RED);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                dispose();
                listener.closeFinishGame();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                closeBtn.setForeground(Color.BLACK);
            }
        });
        newGameBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(newGameBtn);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                eventButton.playSoundClickButton();
                listener.closeFinishGame();
                listener.reGame();
                dispose();
                super.mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(newGameBtn);
            }
        });
        reviewBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(reviewBtn);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.closeFinishGame();
                listener.reviewMode();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(reviewBtn);
            }
        });
        someThingBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(someThingBtn);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                eventButton.playSoundClickButton();
                listener.closeFinishGame();
                listener.openTutorial();
                super.mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(someThingBtn);
            }
        });
    }

    public void addListener(IFinishGameListener listener){
        this.listener = listener;
    }
    public void addEventButtonListener(ISoundEventButton eventButton){
        this.eventButton = eventButton;
    }

    public void target(Component c){
        c.setBackground(new Color(192,188,185));
        c.setForeground(new Color(104,159,57));
    }
    public void unTarget(Component c){
        c.setBackground(new Color(239,235,232));
        c.setForeground(new Color(104,159,57));
    }
}
