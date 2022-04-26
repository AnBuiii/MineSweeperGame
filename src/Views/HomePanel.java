package Views;

import Controller.Home;
import Interfaces.IHomeListener;
import Interfaces.IPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements IPanel {
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 600;
    private IHomeListener listener;
    private JButton newGamelb ;
    private Home home;

    public HomePanel(){
        init();
        addView();
        addEvent();
    }


    @Override
    public void init() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        newGamelb = new JButton();

    }

    @Override
    public void addView() {
        Font font = new Font("Arial", Font.PLAIN, 20);

        newGamelb.setBounds(50,100,300,50);
        newGamelb.setFont(font);
        newGamelb.setBorder(new RoundedBorder(10));
        newGamelb.setFocusPainted(false);
        newGamelb.setText("New game");
        newGamelb.setHorizontalAlignment(SwingConstants.LEFT);
        newGamelb.setBackground(new Color(239,235,232));
        newGamelb.setForeground(new Color(104,159,57));

        add(newGamelb);

    }
    @Override
    public void addEvent() {

        newGamelb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                target(newGamelb);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(newGamelb);
            }
        });

        newGamelb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!listener.isGameMenuOpen()){
                    target(newGamelb);
                    listener.openGameMenu();

                } else {
                    unTarget(newGamelb);
                    listener.closeGameMenu();

                }

            }
        });
    }
    public void target(Component c){
        c.setBackground(new Color(104,159,57));
        c.setForeground(new Color(239,235,232));
    }
    public void unTarget(Component c){
        c.setBackground(new Color(239,235,232));
        c.setForeground(new Color(104,159,57));
    }
    public void addListener(IHomeListener event){
        listener = event;
    }
}
