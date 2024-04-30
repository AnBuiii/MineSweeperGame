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
    JLabel flag_tutorial;
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
        flag_tutorial = new JLabel(FLAG);
        backBtn = new JLabel();
        displayPn = new JPanel();
        displayPn.setLayout(new GridLayout(1,5));
        windowName = new JLabel();
    }


    private void setLayout(Object o) {
    }

    public void addView(){
        String textTutorial ="Some terms:" +
                "\nFlag: Put a flag in a zone when you have confirmed that there is a mine. " +
                "\nHint: When you use hint, if you open a square which is mine, you will not lose. But you just can use the 3 times." +
                "\nSafe squares have numbers telling you how many mines touch the square. " +
                "\nYou can use the number clues to solve the game by opening all of the safe squares. If you click on a mine you lose the game! " +
                "\nYou open squares with the left mouse button and put flags on mines with the right mouse button. " +
                "\nWhen you open a square that does not touch any mines." +
                "\nHowever, if you place the correct number of flags on the wrong squares, chording will explode the mines. " +
                "\nThe three difficulty levels are Beginner (8x8 or 9x9 with 10 mines), Intermediate (16x16 with 40 mines) and Expert (30x16 with 99 mines). " +
                "\nThe game ends when all safe squares have been opened. A counter shows the number of mines without flags, and a clock shows your time. ";

        createTextAreaView(tutorialTa, textTutorial);

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

        displayPn.setBounds(20,50, Tutorial.WIDTH - 2*20 ,Tutorial.HEIGHT - 60 - 20);
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

    private void createTextAreaView(JTextArea jTextArea, String text){
        Font font = new Font("VNI", Font.PLAIN, 16);
        jTextArea.setText(text);
        jTextArea.setFont(font);
        jTextArea.setMinimumSize(new Dimension(500, 500));
        //jTextArea.setHorizontalAlignment(alignment);
        //jTextArea.setVerticalAlignment(JLabel.CENTER);
        jTextArea.setOpaque(true);
        jTextArea.setBackground(FOREGROUND);
        jTextArea.setForeground(Color.BLACK);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setLineWrap(true);
        jTextArea.setSize(new Dimension(500, 500));
        jTextArea.setPreferredSize(new Dimension(500, 500));
        add(new JScrollPane(new JTextArea(20, 20)), BorderLayout.PAGE_START);
        jTextArea.setEditable(false);
        add(flag_tutorial,gbc);
        //JScrollPane scrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
    /*private void createImageView(JPEGImageReadParam jImage) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);
    }*/

    public void target(Component c){
        c.setForeground(Color.RED);
    }
    public void unTarget(Component c){
        c.setForeground(Color.BLACK);
    }

}

