package Views;

import Interfaces.IHomeListener;
import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Views.custom.RotateLabel;
import Views.custom.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;

public class HomePanel extends JPanel implements IPanel {
    public static final String TITLE = BOMB + "MINESWEEPER";

    public static class setting_bound{
        static int x = 330;
        static int y = 55;
        static int width = 40;
        static int height = 40;
    }
    public static class title_bound{
         static int x = 50;
         static int y = 40;
         static int width = 300;
         static int height = 70;
    }
    public static class continueBtn_bound{
        static int x = 50;
        static int y = 140;
        static int width = 300;
        static int height = 50;
    }
    public static class newGameBtn_bound {
        static int x = 50;
        static int y = 140;
        static final int y_default = y;
        static int width = 300;
        static int height = 50;
    }
    public static class newGameMenu_bound{
        static int x = 50;
        static int y = 200;
        static final int y_default = y;
        static int width = 300;
        static int height = 150;
    }
    public static class statisticsGame_bound {
        static int x = 50;
        static int y = 200;
        static final int y_default = y;
        static int width = 300;
        static int height = 50;
    }
    public static class tutorialGame_bound {
        static int x = 50;
        static int y = 260;
        static final int y_default = y;
        static int width = 300;
        static int height = 50;
    }


    private IHomeListener listener;
    private ISoundEventButton eventButton;
    private JLabel titleLb;
    private RoundedButton newGameBtn;
    private RoundedButton continueBtn;
    private RoundedButton statisticbtn;
    private RoundedButton tutorialbtn;
    private RotateLabel bombArt;
    private JLabel settingLb;
    private boolean newGameBtnIsClick;
    private boolean isSettingClicked;

    public HomePanel(){
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        //setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        titleLb = new JLabel(TITLE);
        continueBtn = new RoundedButton(10);
        newGameBtn = new RoundedButton(10);
        statisticbtn = new RoundedButton(10);
        tutorialbtn = new RoundedButton(10);
        bombArt = new RotateLabel(BOMB);
        newGameBtnIsClick = false;
        settingLb = new JLabel(SETTING);
        isSettingClicked = false;

    }

    @Override
    public void addView() {

        Font font = new Font("Arial", Font.PLAIN, 20);

        settingLb.setFont(new Font("VNI",Font.BOLD,40));
        settingLb.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        settingLb.setForeground(BACKGROUND);
        settingLb.setBackground(new Color(239,235,232));

        titleLb.setFont(new Font("VNI",Font.BOLD,25));
        titleLb.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        titleLb.setForeground(Color.BLACK);
        titleLb.setBackground(new Color(239,235,232));

        continueBtn.setFont(font);
        continueBtn.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        continueBtn.setFocusPainted(false);
        continueBtn.setText("Continue");
        continueBtn.setHorizontalAlignment(SwingConstants.LEFT);
        unTarget(continueBtn);
        continueBtn.setVisible(false);

        newGameBtn.setFont(font);
        newGameBtn.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        newGameBtn.setFocusPainted(false);
        newGameBtn.setText("New game");
        newGameBtn.setHorizontalAlignment(SwingConstants.LEFT);
        unTarget(newGameBtn);

        statisticbtn.setFont(font);
        statisticbtn.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        statisticbtn.setFocusPainted(false);
        statisticbtn.setText("Statistics");
        statisticbtn.setHorizontalAlignment(SwingConstants.LEFT);
        unTarget(statisticbtn);

        tutorialbtn.setFont(font);
        tutorialbtn.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        tutorialbtn.setFocusPainted(false);
        tutorialbtn.setText("Tutorial");
        tutorialbtn.setHorizontalAlignment(SwingConstants.LEFT);
        unTarget(tutorialbtn);

        bombArt.setBounds(-100,200,1000,1000);
        bombArt.setFont(new Font("VNI",Font.BOLD,400));



        // check continue btn
        if(listener != null){
            newGameBtn_bound.y = newGameBtn_bound.y_default;
            newGameMenu_bound.y = newGameMenu_bound.y_default;
            if(listener.isGameFinish()) {
                newGameBtn_bound.y = 140;
                statisticsGame_bound.y = 210;
                tutorialGame_bound.y = 280;
                continueBtn.setVisible(false);
            }
            else {
                newGameBtn_bound.y = 210;
                statisticsGame_bound.y = 280;
                tutorialGame_bound.y = 350;
                continueBtn.setVisible(true);
            }
        }
        settingLb.setBounds(setting_bound.x,setting_bound.y,setting_bound.width,setting_bound.height);
        titleLb.setBounds(title_bound.x,title_bound.y,title_bound.width,title_bound.height);
        continueBtn.setBounds(continueBtn_bound.x,continueBtn_bound.y,continueBtn_bound.width,continueBtn_bound.height);
        newGameBtn.setBounds(newGameBtn_bound.x, newGameBtn_bound.y, newGameBtn_bound.width, newGameBtn_bound.height);
        statisticbtn.setBounds(statisticsGame_bound.x, statisticsGame_bound.y, statisticsGame_bound.width, statisticsGame_bound.height);
        tutorialbtn.setBounds(tutorialGame_bound.x, tutorialGame_bound.y, tutorialGame_bound.width, tutorialGame_bound.height);

        add(settingLb);
        add(titleLb);
        add(continueBtn);
        add(newGameBtn);
        add(statisticbtn);
        add(tutorialbtn);
        add(bombArt,BorderLayout.CENTER);
    }


    @Override
    public void addEvent() {

        settingLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                settingLb.setForeground(Color.RED);
               // target(settingLb);

            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(!isSettingClicked){
                   unTarget(settingLb);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isSettingClicked = true;
                eventButton.playSoundClickButton();
                listener.openSetting();
            }
        });
        //StatusPanel.Clock clockCTN = new StatusPanel.Clock();
        continueBtn.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                eventButton.playSoundHoverButton();
                target(continueBtn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                unTarget(continueBtn);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                eventButton.playSoundClickButton();

                listener.continueGame();
            }
        });

        newGameBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(newGameBtn);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!listener.getNewGameMenu().isVisible()){
                    unTarget(newGameBtn);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                eventButton.playSoundClickButton(); // phát âm ở button

                if(!newGameBtnIsClick){
                    newGameBtnIsClick = true;
                    super.mouseClicked(e);
                    if(!listener.getNewGameMenu().isVisible()){
                        target(newGameBtn);
                        if(continueBtn.isVisible()) newGameMenu_bound.y += 60;
                        listener.getNewGameMenu().setVisible(true);
                        listener.getNewGameMenu().setLocation(newGameMenu_bound.x, newGameMenu_bound.y);
                        listener.getNewGameMenu().getContentPn().setLocation(0, - newGameMenu_bound.height);
                        new Timer(1, new ActionListener(){
                            int count = 0;
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                listener.getNewGameMenu().getContentPn().setLocation(listener.getNewGameMenu().getContentPn().getX(),listener.getNewGameMenu().getContentPn().getY() + 10);
                                statisticbtn.setLocation(statisticbtn.getX(), statisticbtn.getY() + 10);
                                tutorialbtn.setLocation(tutorialbtn.getX(), tutorialbtn.getY() + 10);
                                count +=10;

                                if (count == 150){
                                    ((Timer) e.getSource()).stop();
                                    newGameBtnIsClick = false;
                                }
                            }
                        }).start();

                    } else {
                        if(continueBtn.isVisible()) newGameMenu_bound.y -= 60;
                        unTarget(newGameBtn);
                        listener.getNewGameMenu().getContentPn().setEnabled(false);
                        new Timer(1, new ActionListener(){
                            int count = 0;
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                listener.getNewGameMenu().getContentPn().setLocation(listener.getNewGameMenu().getContentPn().getX(),listener.getNewGameMenu().getContentPn().getY() - 10);
                                statisticbtn.setLocation(statisticbtn.getX(), statisticbtn.getY() - 10);
                                tutorialbtn.setLocation(tutorialbtn.getX(), tutorialbtn.getY() - 10);
                                count += 10;

                                if (count == 150){
                                    ((Timer) e.getSource()).stop();
                                    listener.getNewGameMenu().setVisible(false);
                                    newGameBtnIsClick = false;
                                }
                            }
                        }).start();
                    }

                }

            }
        });
        statisticbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(statisticbtn);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {

                unTarget(statisticbtn);

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                eventButton.playSoundClickButton();
                listener.openStatistic();
                super.mouseClicked(e);
            }
        });

        tutorialbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(tutorialbtn);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(tutorialbtn);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                eventButton.playSoundClickButton();
                listener.openTutorial();
                super.mouseClicked(e);
            }
        });

    }
    public void target(Component c){
        c.setBackground(BACKGROUND);
        c.setForeground(FOREGROUND);
    }
    public void unTarget(Component c){
        c.setBackground(new Color(0,0,0,0));
        c.setForeground(BACKGROUND);
    }
    public void addListener(IHomeListener event){
        listener = event;
    }
    public void addEventButtonListener(ISoundEventButton eventButton){
        this.eventButton = eventButton;
    }
    public void reTargetSettingLb(){
        isSettingClicked = false;
        unTarget(settingLb);
    }
}
