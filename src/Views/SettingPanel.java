package Views;

import Interfaces.IPanel;
import Interfaces.ISettingPanelListener;
import Interfaces.ISoundEventButton;
import Interfaces.IStatisticPanelListener;

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
        displayPn = new JPanel();
        displayPn.setLayout(new GridLayout(3,2));

    }

    @Override
    public void addView() {

        backBtn.setText(BACK);
        backBtn.setFont(new Font("VNI", Font.PLAIN, 30));
        backBtn.setForeground(Color.BLACK);

        windowName.setText("SETTINGS");
        windowName.setFont(new Font("VNI", Font.BOLD, 30));
        windowName.setForeground(BACKGROUND);

        backBtn.setBounds(10,10,30,30);
        windowName.setBounds(130,10,200,30);

        add(backBtn);
        add(windowName);
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
