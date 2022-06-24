package Views;


import Controller.MineSweeperGame;
import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Interfaces.IStatusPanelListener;
import Models.MineGrid;
import Models.Cell;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.Thread;

import static Models.MineGrid.num_mines;
import static Views.custom.Theme.*;

public class StatusPanel extends JPanel implements IPanel {
    private JLabel lbNumCellUnrevealed;
    private JLabel lbNotify;
    private JButton btnRestart;
    private JLabel backBtn;
    private JLabel clockLb;
    private static JLabel timeLb;
    private JLabel flagLb;
    private JLabel numFlagLb;
    private JLabel hintBtn;
    private boolean hintMode;

    private GridBagConstraints gbc;

    private int xDrag;
    private int yDrag;

    private int xPress;
    private int yPress;

    private JFrame parentFrame = new JFrame();
    private IStatusPanelListener listener;
    private ISoundEventButton eventButton;
    public int time;
    public static long sec;
    public static int hour;
    public static int min;
    public static boolean stopClock;
    Clock clock;
    public StatusPanel() {
        init();
        addView();
        addEvent();
        hintMode = false;
        stopClock = false;
    }

    public int getTime() {
        return (int) (sec + min*60 + hour*3600);
    }

    public void startClock() {
        stopClock = false;
        System.out.println("ua");
    }

    public static class Clock extends Thread{
        public Clock(){	}
        public void run() {
            do {
                if(!stopClock){
                    sec++;
                    if (sec==60)
                    {
                        min+=1;
                        sec=1;
                    }
                    if (min==60)
                    {
                        hour+=1;
                        min=1;
                    }
                    String S = String.valueOf(  + hour+ ":" + min +":"+ sec);
                    timeLb.setText(S);
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } while (!MineSweeperGame.isFinish);
        }

    }

    @Override
    public void init() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND);
        backBtn = new JLabel(BACK);
        flagLb = new JLabel(FLAG);
        //numFlagLb = new JLabel("00");
        String mine = String.valueOf(num_mines);
        numFlagLb = new JLabel(mine);
        clockLb = new JLabel(CLOCK);
        timeLb = new JLabel();
        clock = new Clock();
        clock.start();
        if (MineSweeperGame.isFinish)
        {
            clock.stop();
        }
        hintBtn = new JLabel(HINT);
        gbc = new GridBagConstraints();
    }
    @Override
    public void addView() {
        //backBtn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        backBtn.setFont(new Font("VNI", Font.BOLD, 30));

        //flagLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        flagLb.setFont(new Font("VNI", Font.PLAIN, 25));

        //numFlagLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        numFlagLb.setFont(new Font("VNI", Font.PLAIN, 23));

        //clockLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        clockLb.setFont(new Font("VNI", Font.PLAIN, 25));

        //timeLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        timeLb.setFont(new Font("VNI", Font.PLAIN, 25));

        //hintBtn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        hintBtn.setFont(new Font("VNI", Font.PLAIN, 25));


        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(backBtn,gbc);

        gbc.gridx = 1;
        add(new JLabel(), gbc);

        gbc.gridx = 2;
        add(flagLb,gbc);

        gbc.gridx = 3;
        add(numFlagLb,gbc);

        gbc.gridx = 4;
        add(new JLabel(), gbc);

        gbc.gridx = 5;
        add(clockLb, gbc);

        gbc.gridx = 6;
        add(timeLb, gbc);

        gbc.gridx = 7;
        add(new JLabel(), gbc);

        gbc.gridx = 8;
        add(hintBtn, gbc);


    }

    @Override
    public void addEvent() {
       this.addMouseMotionListener(new MouseMotionAdapter() {
           @Override
           public void mouseDragged(MouseEvent e) {
               super.mouseDragged(e);
               xDrag = e.getX();
               yDrag = e.getY();
               parentFrame.setLocation(parentFrame.getLocation().x+xDrag-xPress, parentFrame.getLocation().y+yDrag-yPress);

           }

           @Override
           public void mouseMoved(MouseEvent e) {
               super.mouseMoved(e);
               xPress = e.getX();
               yPress = e.getY();
           }
       });
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventButton.playSoundHoverButton();
                target(backBtn);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unTarget(backBtn);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
//                clock.stop();
                stopClock = true;
                listener.back();
            }

        });
        hintBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.hint();
                hintMode = !hintMode;
                if(hintMode) hintBtn.setForeground(new Color(255,255,0));
                else hintBtn.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                targetHint(hintBtn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(!hintMode) unTarget(hintBtn);
            }
        });

    }

    public void addListener(IStatusPanelListener event) {
        listener = event;

    }
    public void addEventButtonListener(ISoundEventButton eventButton){
        this.eventButton = eventButton;
    }

    public void updateStatus(int numSquareUnrevealed) {
        /*lbNumCellUnrevealed.setText("Số ô chưa mở: " + numSquareUnrevealed);
        if (numSquareUnrevealed == MineGrid.num_mines) {
            lbNotify.setText("THẮNG");
            lbNotify.setForeground(Color.blue);
       } else if (numSquareUnrevealed == 0) {
            lbNotify.setText("THUA");
            lbNotify.setForeground(Color.red);
       }*/
        /*if (num_mines< numSquareUnrevealed)
        {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Error");
        }*/

    }
    public void reviewMode(){
        clockLb.setText(RIGHT_ARROW);
        timeLb.setVisible(false);
        flagLb.setVisible(false);
        numFlagLb.setText(LEFT_ARROW);
        clockLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.reviewNext();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(clockLb);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(clockLb);
            }
        });
        numFlagLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.reviewPrevious();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eventButton.playSoundHoverButton();
                target(numFlagLb);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                unTarget(numFlagLb);
            }
        });

    }
    private void target(Component c){
        c.setForeground(Color.RED);
    }
    private void unTarget(Component c){
        c.setForeground(Color.BLACK);
    }
    private void targetHint(Component c){
        c.setForeground(new Color(255,255,0));
    }

    public void setParentFrame(JFrame jFrame){
        this.parentFrame = jFrame;
    }
    public void load() {
        addEvent();
    }

}