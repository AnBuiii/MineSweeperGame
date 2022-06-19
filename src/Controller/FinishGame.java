package Controller;

import Interfaces.IFinishGameListener;
import Interfaces.IPanel;
import Views.custom.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;

public class FinishGame extends JDialog implements IPanel {
    JLabel closeBtn;
    IFinishGameListener listener;
    FinishGame(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        setBackground(FOREGROUND);
        setSize(200, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLocation(getX(), getY()+10);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeBtn = new JLabel("x");
    }

    @Override
    public void addView() {
        Font font = new Font("VNI", Font.PLAIN, 20);
        closeBtn.setBounds(160,0,30,30);
        closeBtn.setFont(font);
        add(closeBtn);
    }

    @Override
    public void addEvent() {
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                listener.closeFinishGame();
            }
        });
    }

    public void addListener(IFinishGameListener listener){
        this.listener = listener;
    }
}
