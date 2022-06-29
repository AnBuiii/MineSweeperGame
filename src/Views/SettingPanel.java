package Views;

import Controller.Setting;
import Interfaces.IPanel;
import Interfaces.ISettingPanelListener;
import Interfaces.ISoundEventButton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;

public class SettingPanel extends JPanel implements IPanel {

    ISoundEventButton eventButton;
    ISettingPanelListener listener;

    JLabel backBtn;
    JPanel displayPn;

    JLabel soundMusicLb;
    JLabel soundEffectLb;

    JSlider soundMusicSLD;
    JSlider soundEffectSLD;
    JLabel nameSoundMusicLb;
    JLabel nameSoundEffectLb;
    JLabel windowName;

    private boolean isMuteMusic;
    private boolean isMuteSoundEffect;

    private final float currVolumeMusic;
    private final float currVolumeEffect;

    public SettingPanel(boolean isMuteSoundMusic, boolean isMuteSoundEffect, float volumeMusic, float volumeEffect){
        this.isMuteMusic = isMuteSoundMusic;
        this.isMuteSoundEffect = isMuteSoundEffect;
        this.currVolumeMusic = volumeMusic;
        this.currVolumeEffect = volumeEffect;
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        setLayout(null);
        setBackground(FOREGROUND);
        backBtn = new JLabel();
        windowName = new JLabel();

        soundMusicLb = setSoundLb(isMuteMusic);
        soundEffectLb = setSoundLb(isMuteSoundEffect);

        soundMusicSLD = new JSlider(-30, 6);
        soundMusicSLD.setValue((int) currVolumeMusic);

        soundEffectSLD = new JSlider(-30, 6);
        soundEffectSLD.setValue((int)currVolumeEffect);

        nameSoundMusicLb = new JLabel();
        nameSoundEffectLb = new JLabel();
        displayPn = new JPanel();
        displayPn.setLayout(new GridBagLayout());

    }

    @Override
    public void addView() {

        backBtn.setText(BACK);
        backBtn.setFont(new Font("VNI", Font.PLAIN, 30));
        backBtn.setForeground(Color.BLACK);

        windowName.setText("SETTINGS");
        windowName.setFont(new Font("VNI", Font.BOLD, 25));
        windowName.setForeground(BACKGROUND);

        nameSoundMusicLb.setText("Musics");
        nameSoundMusicLb.setFont(FONT);
        nameSoundEffectLb.setText("Effects");
        nameSoundEffectLb.setFont(FONT);


        soundMusicLb.setFont(FONT);
        soundMusicSLD.setBackground(BACKGROUND);

        soundEffectLb.setFont(FONT);
        soundEffectSLD.setBackground(BACKGROUND);

        displayPn.setBackground(FOREGROUND);

        backBtn.setBounds(10,10,30,30);
        windowName.setBounds(110,10,200,30);
        displayPn.setBounds(40,0, Setting.WIDTH -80,Setting.HEIGHT);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;      //make this component tall
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        displayPn.add(nameSoundMusicLb,c);

        c.fill = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        displayPn.add(soundMusicLb,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        displayPn.add(soundMusicSLD, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        displayPn.add(nameSoundEffectLb,c);

        c.fill = GridBagConstraints.LINE_END;
        c.gridx = 0;
        c.gridy = 3;
        displayPn.add(soundEffectLb,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        displayPn.add(soundEffectSLD, c);

        add(backBtn);
        add(windowName);
        add(displayPn);

    }

    private JLabel setSoundLb (boolean check){
        JLabel jLabel = new JLabel();
        if(!check){
           jLabel.setText(LOUD_SOUND);
        }else {
            jLabel.setText(MUTE_SOUND);
        }
        return jLabel;
    }

    @Override
    public void addEvent() {
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(backBtn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(backBtn);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.back();

            }
        });

        soundMusicLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                target(soundMusicLb);
                eventButton.playSoundHoverButton();
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                if(isMuteMusic){
                    isMuteMusic = false;
                    soundMusicSLD.setEnabled(true);
                    soundMusicLb.setText(LOUD_SOUND);

                }else {
                    isMuteMusic = true;
                    soundMusicSLD.setEnabled(false);
                    soundMusicLb.setText(MUTE_SOUND);
                }
                listener.muteMusic(isMuteMusic);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(soundMusicLb);
            }
        });

        soundEffectLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                target(soundEffectLb);
                eventButton.playSoundHoverButton();
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(isMuteSoundEffect){
                    isMuteSoundEffect = false;
                    soundEffectSLD.setEnabled(true);
                    soundEffectLb.setText(LOUD_SOUND);
                    eventButton.playSoundClickButton();
                }else {
                    isMuteSoundEffect = true;
                    soundEffectSLD.setEnabled(false);
                    soundEffectLb.setText(MUTE_SOUND);
                }
                listener.muteSoundEffect(isMuteSoundEffect); // thuc hien giam tieng hieu ung am thanh
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(soundEffectLb);
            }
        });

        soundMusicSLD.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                eventButton.playSoundClickButton();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
            }
        });
        soundMusicSLD.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                listener.changeVolumeMusic(soundMusicSLD.getValue());
            }
        });

        soundEffectSLD.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                eventButton.playSoundClickButton();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
            }
        });
        soundEffectSLD.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                listener.changeVolumeEffect(soundEffectSLD.getValue());
            }
        });

    }

    public void target(Component c){
        c.setForeground(Color.RED);
    }
    public void unTarget(Component c){
        c.setForeground(Color.BLACK);
    }

    public void addListener(ISettingPanelListener listener){
        this.listener = listener;
    }
    public void addEventButtonListener(ISoundEventButton eventButton){
        this.eventButton = eventButton;
    }
}
