package Views;

import Interfaces.IPanel;
import Interfaces.IQuitGamePanelListener;
import Interfaces.ISoundEventButton;
import Interfaces.IToolBarListener;
import Views.custom.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import static Views.custom.Theme.*;

public class QuitGamePanel extends JDialog implements IPanel {

    JTextArea noticeTA;
    RoundedButton closeBtn;
    RoundedButton yesBtn;
    RoundedButton noBtn;
    ISoundEventButton eventButton;
    IQuitGamePanelListener listener;
    public QuitGamePanel(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        getContentPane().setBackground(BACKGROUND);
        setSize(380, 200);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLocation(getX(), getY()+10);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(),  ARCW_FORM, ARCH_FORM));
        setLayout(null);

        closeBtn = new RoundedButton(10);
        yesBtn = new RoundedButton(10);
        noBtn = new RoundedButton(10);
        noticeTA = new JTextArea();

    }

    @Override
    public void addView() {
        Font font = new Font("VNI", Font.PLAIN, 20);
        closeBtn.setBounds(325,5,50,35);
        closeBtn.setFocusPainted(false);
        closeBtn.setFont(font);
        closeBtn.setText(CLOSE);
        closeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        closeBtn.setBackground(FOREGROUND);
        closeBtn.setForeground(BACKGROUND);
        add(closeBtn);


        noticeTA.setFont(new Font("VNI", Font.BOLD, 20));
        noticeTA.setBounds(70,50,200,30);
        noticeTA.setText("DO YOU WANT TO EXIT?");
        noticeTA.setSize(245,noticeTA.getHeight());
        noticeTA.setBackground(BACKGROUND);
        noticeTA.setEditable(false);
        add(noticeTA);

        yesBtn.setFont(new Font("VNI", Font.PLAIN, 20));
        yesBtn.setBounds(20,100,160,40);
        yesBtn.setFocusPainted(false);
        yesBtn.setText("YES");
        yesBtn.setHorizontalAlignment(SwingConstants.CENTER);
        yesBtn.setBackground(FOREGROUND);
        yesBtn.setForeground(BACKGROUND);
        add(yesBtn);

        noBtn.setFont(new Font("VNI", Font.PLAIN, 20));
        noBtn.setBounds(205,100,160,40);
        noBtn.setFocusPainted(false);
         noBtn.setText("NO");
        noBtn.setHorizontalAlignment(SwingConstants.CENTER);
        noBtn.setBackground(FOREGROUND);
        noBtn.setForeground(BACKGROUND);
        add(noBtn);

    }

    @Override
    public void addEvent() {
        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(closeBtn);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.closeBtnClicked();
                setVisible(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(closeBtn);
            }
        });
        yesBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(yesBtn);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.yesBtnClicked();
                setVisible(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(yesBtn);
            }
        });

        noBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(noBtn);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.noBtnClicked();
                setVisible(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(noBtn);
            }
        });
    }

    public void addListener(IQuitGamePanelListener listener){
        this.listener = listener;
    }
    public void addEventButtonListener(ISoundEventButton eventButton){
        this.eventButton = eventButton;
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
