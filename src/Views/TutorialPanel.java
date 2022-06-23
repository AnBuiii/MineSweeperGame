package Views;

import Controller.Tutorial;
import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Interfaces.ITutorialPanelListener;
import javax.swing.JScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;

public class TutorialPanel extends JPanel implements IPanel {
    ITutorialPanelListener listener;
    ISoundEventButton eventButton;
    //JLabel tutorialLb;
    JTextArea tutorialTa;
    JPanel displayPn;
    JLabel backBtn;
    JLabel windowName;
    private GridBagConstraints gbc;
    public TutorialPanel(){
        init();
        addView();
        addEvent();
    }
    public void init(){
        setLayout(null);
        setBackground(FOREGROUND);

        //tutorialLb = new JLabel();
        tutorialTa = new JTextArea();
        backBtn = new JLabel();
        displayPn = new JPanel();
        displayPn.setLayout(new GridLayout(1,5));
        windowName = new JLabel();
    }


    private void setLayout(Object o) {
    }

    public void addView(){
        /*createJLabelView(tutorialLb, "Minesweeper rules are very simple. " +
                "\nThe board is divided into cells, with mines randomly distributed. " +
                "\nTo win, you need to open all the cells. The number on a cell shows the number of mines adjacent to it. " +
                "\nUsing this information, you can determine cells that are safe, and cells that contain mines. " +
                "\nCells suspected of being mines can be marked with a flag using the right mouse button.",JLabel.CENTER);*/
        String textTutorial = "Minesweeper rules are very simple." +
                "\nThe board is divided into cells, with mines randomly distributed." +
                "\nTo win, you need to open all the cells. The number on a cell shows the number of mines adjacent to it." +
                "\nUsing this information, you can determine cells that are safe, and cells that contain mines." +
                "\nCells suspected of being mines can be marked with a flag using the right mouse button.";
        /*createTextAreaView(tutorialTa, "Minesweeper rules are very simple. " +
                "The board is divided into cells, with mines randomly distributed. " +
                "To win, you need to open all the cells. The number on a cell shows the number of mines adjacent to it. " +
                "Using this information, you can determine cells that are safe, and cells that contain mines. " +
                "Cells suspected of being mines can be marked with a flag using the right mouse button.",JTextArea.CENTER_ALIGNMENT);*/
        createTextAreaView(tutorialTa, textTutorial, JTextArea.CENTER_ALIGNMENT);

        windowName.setText("TUTORIAL");
        windowName.setFont(new Font("VNI", Font.PLAIN, 30));
        windowName.setForeground(BACKGROUND);

        backBtn.setText(BACK);
        backBtn.setFont(new Font("VNI", Font.PLAIN, 30));
        backBtn.setForeground(Color.BLACK);

        backBtn.setBounds(10,10,30,30);
        windowName.setBounds(70,10,200,30);
        displayPn.add(tutorialTa);

        add(backBtn);
        add(windowName);
        //displayPn.setBounds(20,80, Tutorial.WIDTH - 2*20,Tutorial.HEIGHT - 80 - 20);
        displayPn.setBounds(20,80, Tutorial.WIDTH - 2*20 ,Tutorial.HEIGHT - 80 - 20);
        displayPn.setBackground(FOREGROUND);
        displayPn.setForeground(BACKGROUND);
        add(displayPn);
    }
    public void addEvent(){
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
    public void addListener(ITutorialPanelListener listener){
        this.listener = listener;
    }
    public void addEventButtonListener(ISoundEventButton eventButton){
        this.eventButton = eventButton;
    }
    private void createJLabelView(JLabel jLabel, String text, int alignment){
        Font font = new Font("VNI", Font.PLAIN, 20);
        jLabel.setText(text);
        jLabel.setFont(font);
        jLabel.setHorizontalAlignment(alignment);
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setOpaque(true);
        jLabel.setBackground(FOREGROUND);
        jLabel.setForeground(BACKGROUND);
    }
    private void createTextAreaView(JTextArea jTextArea, String text, float alignment){
        Font font = new Font("VNI", Font.PLAIN, 18);
        jTextArea.setText(text);
        jTextArea.setFont(font);
        jTextArea.setMinimumSize(new Dimension(500, 500));
        //jTextArea.setHorizontalAlignment(alignment);
        //jTextField.setVerticalAlignment(JLabel.CENTER);
        jTextArea.setOpaque(true);
        jTextArea.setBackground(FOREGROUND);
        jTextArea.setForeground(Color.BLACK);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setLineWrap(true);
        jTextArea.setSize(new Dimension(500, 500));
        jTextArea.setPreferredSize(new Dimension(500, 500));
        add(new JScrollPane(new JTextArea(50, 50)), BorderLayout.PAGE_START);
        jTextArea.setEditable(false);
        //JScrollPane scrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
    public void target(Component c){
        c.setForeground(Color.RED);
    }
    public void unTarget(Component c){
        c.setForeground(Color.BLACK);
    }

}

