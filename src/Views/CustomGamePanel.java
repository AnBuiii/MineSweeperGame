package Views;

import Interfaces.ICustomGameListener;
import Interfaces.IPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.BACKGROUND;
import static Views.custom.Theme.FOREGROUND;

public class CustomGamePanel extends JDialog implements IPanel {
    ICustomGameListener listener;
    JLabel closeBtn;
    JLabel newGameLb;
    JLabel widthLb;
    JLabel heightLb;
    JLabel mineLb;
    JTextField widthTf;
    JTextField heightTf;
    JTextField mineTf;

    public CustomGamePanel(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        getContentPane().setBackground(BACKGROUND);
        setSize(400, 300);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeBtn = new JLabel("x");
        newGameLb = new JLabel("New Game");
        widthLb = new JLabel("Width");
        heightLb = new JLabel("Height");
        mineLb = new JLabel("Mines");
        widthTf = new JTextField("9");
        heightTf = new JTextField("9");
        mineTf = new JTextField("9");
    }

    @Override
    public void addView() {
        closeBtn.setBounds(370,0,30,30);
        closeBtn.setFont(new Font("VNI", Font.PLAIN, 20));
        add(closeBtn);

        newGameLb.setBounds(10,10,100,30);
        newGameLb.setFont(new Font("VNI", Font.PLAIN, 20));
        add(newGameLb);

        widthLb.setBounds(20,50,100,30);
        widthLb.setFont(new Font("VNI", Font.PLAIN, 20));
        add(widthLb);

        heightLb.setBounds(80,50,100,30);
        heightLb.setFont(new Font("VNI", Font.PLAIN, 20));
        add(heightLb);

        mineLb.setBounds(50,100,100,30);
        mineLb.setFont(new Font("VNI", Font.PLAIN, 20));
        add(mineLb);

//        newGameLb.setBounds(10,10,100,30);
//        newGameLb.setFont(new Font("VNI", Font.PLAIN, 20));
    }

    @Override
    public void addEvent() {
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
//                eventButton.playSoundHoverButton();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                eventButton.playSoundClickButton();
                dispose();
                listener.closeCustomPanel();
            }
        });
    }
    public void addListener(ICustomGameListener listener){
        this.listener = listener;
    }
}
