package Views;

import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Interfaces.IStartGameListener;
import Models.GameDifficulty;
import Views.custom.RoundedBorder;
import Views.custom.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class NewGamePanel extends JPanel implements IPanel {

    JLabel beginnerLb;
    JLabel intermediateLb;
    JLabel expertLb;
    JLabel customLb;
    JLabel triangleLb;
    JLabel beginnerInfoLb;
    JLabel intermediateInfoLb;
    JLabel expertInfoLb;
    JLabel customInfoLb;
    JLabel triangleInfoLb;
    IStartGameListener listener;
    ISoundEventButton eventButton;
    JPanel contentPn;

    public NewGamePanel(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
//        setLayout(new GridLayout(4,2));
//        setBorder(new RoundedBorder(10));

        setLayout(null);



        beginnerLb = new JLabel();
        intermediateLb = new JLabel();
        expertLb = new JLabel();
        customLb = new JLabel();
        triangleLb = new JLabel();
        beginnerInfoLb = new JLabel();
        intermediateInfoLb = new JLabel();
        expertInfoLb = new JLabel();
        customInfoLb = new JLabel();
        triangleInfoLb = new JLabel();
        contentPn = new JPanel();

        contentPn.setLayout(new GridLayout(5,2));
        contentPn.setBorder(new RoundedBorder(10));

    }

    @Override
    public void addView() {
        Font font = new Font("Arial", Font.PLAIN, 20);

        beginnerLb.setText("Beginner");
        beginnerLb.setFont(font);
        beginnerLb.setHorizontalAlignment(JLabel.LEFT);
        beginnerLb.setVerticalAlignment(JLabel.CENTER);
        beginnerLb.setOpaque(true);
        unTarget(beginnerLb);

        beginnerInfoLb.setText("9x9 - 10");
        beginnerInfoLb.setFont(font);
        beginnerInfoLb.setHorizontalAlignment(JLabel.RIGHT);
        beginnerInfoLb.setVerticalAlignment(JLabel.CENTER);
        beginnerInfoLb.setOpaque(true);
        unTarget(beginnerInfoLb);

        intermediateLb.setText("Intermediate");
        intermediateLb.setFont(font);
        intermediateLb.setHorizontalAlignment(JLabel.LEFT);
        intermediateLb.setVerticalAlignment(JLabel.CENTER);
        intermediateLb.setOpaque(true);
        unTarget(intermediateLb);

        intermediateInfoLb.setText("16x16 - 40");
        intermediateInfoLb.setFont(font);
        intermediateInfoLb.setHorizontalAlignment(JLabel.RIGHT);
        intermediateInfoLb.setVerticalAlignment(JLabel.CENTER);
        intermediateInfoLb.setOpaque(true);
        unTarget(intermediateInfoLb);

        expertLb.setText("Expert");
        expertLb.setFont(font);
        expertLb.setHorizontalAlignment(JLabel.LEFT);
        expertLb.setVerticalAlignment(JLabel.CENTER);
        expertLb.setOpaque(true);
        unTarget(expertLb);

        expertInfoLb.setText("16x30 - 99");
        expertInfoLb.setFont(font);
        expertInfoLb.setHorizontalAlignment(JLabel.RIGHT);
        expertInfoLb.setVerticalAlignment(JLabel.CENTER);
        expertInfoLb.setOpaque(true);
        unTarget(expertInfoLb);

        customLb.setText("Custom");
        customLb.setFont(font);
        customLb.setHorizontalAlignment(JLabel.LEFT);
        customLb.setVerticalAlignment(JLabel.CENTER);
        customLb.setOpaque(true);
        unTarget(customLb);

        customInfoLb.setOpaque(true);
        unTarget(customInfoLb);

        triangleLb.setText("Triangle");
        triangleLb.setFont(font);
        triangleLb.setHorizontalAlignment(JLabel.LEFT);
        triangleLb.setVerticalAlignment(JLabel.CENTER);
        triangleLb.setOpaque(true);
        unTarget(triangleLb);

        triangleInfoLb.setOpaque(true);
        unTarget(triangleInfoLb);

        contentPn.add(beginnerLb);
        contentPn.add(beginnerInfoLb);
        contentPn.add(intermediateLb);
        contentPn.add(intermediateInfoLb);
        contentPn.add(expertLb);
        contentPn.add(expertInfoLb);
        contentPn.add(customLb);
        contentPn.add(customInfoLb);
        contentPn.add(triangleLb);
        contentPn.add(triangleInfoLb);
        contentPn.setBounds(0,0, 300, 150);
        contentPn.setBackground(Theme.FOREGROUND);
        contentPn.setForeground(Theme.BACKGROUND);
        add(contentPn);

    }
    public JPanel getContentPn(){
        return contentPn;
    }
    @Override
    public void addEvent() {
        
        beginnerLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(beginnerLb);
                target(beginnerInfoLb);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(beginnerLb);
                unTarget(beginnerInfoLb);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.startGame(GameDifficulty.BEGINNER);
                //listener.closeHomePanel();

            }
        });
        beginnerInfoLb.addMouseListener(beginnerLb.getMouseListeners()[0]);

        intermediateLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(intermediateLb);
                target(intermediateInfoLb);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(intermediateLb);
                unTarget(intermediateInfoLb);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.startGame(GameDifficulty.INTERMEDIATE);
            }
        });
        intermediateInfoLb.addMouseListener(intermediateLb.getMouseListeners()[0]);

        expertLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(expertLb);
                target(expertInfoLb);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(expertLb);
                unTarget(expertInfoLb);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.startGame(GameDifficulty.EXPERT);
            }
        });
        expertInfoLb.addMouseListener(expertLb.getMouseListeners()[0]);

        customLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(customLb);
                target(customInfoLb);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(customLb);
                unTarget(customInfoLb);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.startGame(GameDifficulty.CUSTOM);
            }
        });
        customInfoLb.addMouseListener(customLb.getMouseListeners()[0]);
        triangleLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(triangleLb);
                target(triangleInfoLb);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(triangleLb);
                unTarget(triangleInfoLb);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.startTriangleGame();
            }
        });
        triangleInfoLb.addMouseListener(triangleLb.getMouseListeners()[0]);


    }
    public void addListener(IStartGameListener event){
        listener = event;
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
