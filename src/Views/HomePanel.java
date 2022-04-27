package Views;

import Interfaces.IHomeListener;
import Interfaces.IPanel;
import Views.custom.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;

public class HomePanel extends JPanel implements IPanel {
    public static final String TITLE = "\uD83D\uDCA3 MINESWEEPER";
    public static int WINDOW_WIDTH = 400;
    public static int WINDOW_HEIGHT = 600;
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
        static int width = 300;
        static int height = 50;
    }
    public static class newGameMenu_bound{
        static int x = 50;
        static int y = 250;
        static int width = 300;
        static int height = 300;
    }

    private IHomeListener listener;
    private JLabel titleLb;
    private RoundedButton newGameBtn;
    private RoundedButton continueBtn;

    public HomePanel(){
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        titleLb = new JLabel(TITLE);
        continueBtn = new RoundedButton(10);
        newGameBtn = new RoundedButton(10);

    }

    @Override
    public void addView() {
        Font font = new Font("Arial", Font.PLAIN, 20);

        titleLb.setFont(new Font("VNI",Font.BOLD,25));
        titleLb.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        titleLb.setForeground(Color.BLACK);
        titleLb.setBackground(new Color(239,235,232));

        continueBtn.setFont(FONT);
        continueBtn.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        continueBtn.setFocusPainted(false);
        continueBtn.setText("Continue");
        continueBtn.setHorizontalAlignment(SwingConstants.LEFT);
        continueBtn.setForeground(BACKGROUND);
        continueBtn.setBackground(FOREGROUND);
        continueBtn.setVisible(false);

        newGameBtn.setFont(font);
        newGameBtn.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        newGameBtn.setFocusPainted(false);
        newGameBtn.setText("New game");
        newGameBtn.setHorizontalAlignment(SwingConstants.LEFT);
        newGameBtn.setBackground(FOREGROUND);
        newGameBtn.setForeground(BACKGROUND);


        if(listener != null){
            if(listener.isGameFinish()) {
                continueBtn.setVisible(false);
            }
            else {
                newGameBtn_bound.y = 200;
                continueBtn.setVisible(true);
            }
        }

        titleLb.setBounds(title_bound.x,title_bound.y,title_bound.width,title_bound.height);
        continueBtn.setBounds(continueBtn_bound.x,continueBtn_bound.y,continueBtn_bound.width,continueBtn_bound.height);
        newGameBtn.setBounds(newGameBtn_bound.x, newGameBtn_bound.y, newGameBtn_bound.width, newGameBtn_bound.height);

        add(titleLb);
        add(continueBtn);
        add(newGameBtn);

    }
    @Override
    public void addEvent() {

        continueBtn.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                target(continueBtn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                unTarget(continueBtn);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        newGameBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                target(newGameBtn);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!listener.isGameMenuOpen()){
                    unTarget(newGameBtn);
                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(!listener.isGameMenuOpen()){
                    target(newGameBtn);
                    listener.openGameMenu(50,260);
                } else {
                    unTarget(newGameBtn);
                    listener.closeGameMenu();
                }
            }
        });

    }
    public void target(Component c){
        c.setBackground(BACKGROUND);
        c.setForeground(FOREGROUND);
    }
    public void unTarget(Component c){
        c.setBackground(FOREGROUND);
        c.setForeground(BACKGROUND);
    }
    public void addListener(IHomeListener event){
        listener = event;
    }
}
