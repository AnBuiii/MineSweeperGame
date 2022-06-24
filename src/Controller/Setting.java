package Controller;

import Interfaces.IPanel;
import Interfaces.ISettingPanelListener;
import Views.SettingPanel;

import javax.swing.*;

public class Setting extends JFrame implements IPanel, ISettingPanelListener {
    public static int WIDTH = 400;
    public static int HEIGHT = 600;
    public Home home;
    SettingPanel settingPanel;

    Setting(){
        init();
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
        settingPanel = new SettingPanel(home.musicGame.isMuteMusic(), home.musicGame.isMuteSoundEffect(), home.musicGame.getCurMusicVolume(), home.musicGame.getCurSoundEffectVolume());
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

    @Override
    public void muteMusic(boolean check) {
        home.musicGame.pauseMusic(check);
    }

    @Override
    public void muteSoundEffect(boolean check) {
        home.musicGame.pauseSoundEffect(check);
    }

    @Override
    public void changeVolumeMusic(float volume) {
        home.musicGame.volumeMusicChanged(volume);
    }

    @Override
    public void changeVolumeEffect(float volume) {
        home.musicGame.volumeSoundEffectChanged(volume);
    }

    public void setHome(Home home){
        this.home = home;
        addView();
        settingPanel.addEventButtonListener(home.getEventSoundButton());
    }


}
