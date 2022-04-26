package Views;

import Controller.MineSweeperGame;
import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Interfaces.IStartGameListener;
import Models.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class NewGamePanel extends JPanel implements IPanel {

    JLabel beginnerLb;
    JLabel intermediateLb;
    JLabel expertLb;
    JLabel customLb;
    JLabel beginnerInfoLb;
    JLabel intermediateInfoLb;
    JLabel expertInfoLb;
    JLabel customInfoLb;
    IStartGameListener listener;
    public NewGamePanel(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        setLayout(new GridLayout(4,2));
        setBorder(new RoundedBorder(10));

        beginnerLb = new JLabel();
        intermediateLb = new JLabel();
        expertLb = new JLabel();
        customLb = new JLabel();
        beginnerInfoLb = new JLabel();
        intermediateInfoLb = new JLabel();
        expertInfoLb = new JLabel();
        customInfoLb = new JLabel();
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

        expertInfoLb.setText("24x24 - 99");
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

        add(beginnerLb);
        add(beginnerInfoLb);
        add(intermediateLb);
        add(intermediateInfoLb);
        add(expertLb);
        add(expertInfoLb);
        add(customLb);
        add(customInfoLb);

    }

    @Override
    public void addEvent() {
        
        beginnerLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
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
                listener.startGame(GameMode.BEGINNER);
            }
        });
        beginnerInfoLb.addMouseListener(beginnerLb.getMouseListeners()[0]);

        intermediateLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
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
                listener.startGame(GameMode.INTERMEDIATE);
            }
        });
        intermediateInfoLb.addMouseListener(intermediateLb.getMouseListeners()[0]);

        expertLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
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
                listener.startGame(GameMode.EXPERT);
            }
        });
        expertInfoLb.addMouseListener(expertLb.getMouseListeners()[0]);

        customLb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                target(customLb);
                target(customInfoLb);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(customLb);
                unTarget(customInfoLb);
            }
        });
        customInfoLb.addMouseListener(customLb.getMouseListeners()[0]);


    }
    public void addListener(IStartGameListener event){
        listener = event;
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
