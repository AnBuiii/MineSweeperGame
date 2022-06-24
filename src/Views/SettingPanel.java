package Views;

import Controller.Setting;
import Interfaces.IPanel;
import Interfaces.ISettingPanelListener;
import Interfaces.ISoundEventButton;

import javax.swing.*;

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


    public SettingPanel(){
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
        soundMusicLb = new JLabel(LOUD_SOUND);
        soundEffectLb = new JLabel(LOUD_SOUND);
        soundEffectSLD = new JSlider(0, 100);
        soundMusicSLD = new JSlider(0, 100);
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
        windowName.setFont(new Font("VNI", Font.BOLD, 30));
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
        windowName.setBounds(130,10,200,30);
        displayPn.setBounds(40,80, Setting.WIDTH -80,Setting.HEIGHT - 300);

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

    private void createJLabelView(JLabel jLabel, String text, int alignment){
        Font font = new Font("VNI", Font.PLAIN, 20);
        jLabel.setText(text);
        jLabel.setFont(font);
        jLabel.setHorizontalAlignment(alignment);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setOpaque(true);
        jLabel.setBackground(FOREGROUND);
        if(alignment == JLabel.LEFT) jLabel.setForeground(Color.BLACK);
        else jLabel.setForeground(BACKGROUND);
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
