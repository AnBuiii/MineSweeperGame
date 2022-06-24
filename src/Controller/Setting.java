package Controller;

import Interfaces.IPanel;
import Interfaces.ISettingPanelListener;
import Views.SettingPanel;

import javax.swing.*;
import java.awt.*;

public class Setting extends JFrame implements IPanel, ISettingPanelListener {
    public static int WIDTH = 400;
    public static int HEIGHT = 600;
    public Home home;
    SettingPanel settingPanel;

    Setting(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        //setSize(WIDTH, HEIGHT);
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
        settingPanel = new SettingPanel();
        settingPanel.setBounds(0, 0, WIDTH, HEIGHT);
        settingPanel.addListener(this);
        add(settingPanel);
    }

    @Override
    public void addEvent() {

    }
    @Override
    public void back() {
        this.setVisible(false);
        //home.reDrawHome();
        home.setVisible(true);
    }

    public void setHome(Home home){
        this.home = home;
        settingPanel.addEventButtonListener(home.getEventSoundButton());
    }


}
