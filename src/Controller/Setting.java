package Controller;

import Interfaces.IPanel;
import Interfaces.ISettingPanelListener;
import Views.SettingPanel;

import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

import static Views.custom.Theme.ARCH_FORM;
import static Views.custom.Theme.ARCW_FORM;

public class Setting extends JFrame implements IPanel, ISettingPanelListener {
    public static int WIDTH = 350;
    public static int HEIGHT = 260;
    public Home home;
    SettingPanel settingPanel;

    Setting(){
        init();
        addEvent();
    }
    @Override
    public void init() {
        setSize(WIDTH, HEIGHT);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(),  ARCW_FORM, ARCH_FORM));
        setLayout(null);
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
        home.setVisible(true);
        this.setVisible(false);
        //home.reDrawHome();
        home.unTargetSettingLb();
        home.setDim();
        home.setEnabled(true);
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
