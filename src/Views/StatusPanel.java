package Views;


import Controller.MineSweeperGame;
import Controller.MineSweeperTemplate;
import DesignPattern.GameState.FinishState;
import DesignPattern.GameState.HintState;
import DesignPattern.GameState.State;
import Interfaces.IPanel;
import Interfaces.ISoundEventButton;
import Interfaces.IStatusPanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.lang.Thread;

import static Views.custom.Theme.*;

public class StatusPanel extends JPanel implements IPanel {
    private JLabel backBtn;
    private JLabel clockLb;
    private JLabel timeLb;
    private JLabel flagLb;
    private JLabel numFlagLb;
    private JLabel hintBtn;

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
    public int numFlag;
    public Clock clock;
    private final MineSweeperTemplate game;

    public StatusPanel(int numFlag, MineSweeperTemplate game) {
        this.numFlag = numFlag;
        init();
        addView();
        addEvent();
        stopClock = false;
        sec = 0;
        min = 0;
        hour = 0;
        this.game = game;
    }

    public int getTime() {
        return (int) (sec + min * 60 + hour * 3600);
    }

    public void startClock() {
        stopClock = false;
    }

    public void killClock() {
        clock.stop();
    }

    public boolean isClockExist() {
        return clock.isAlive();
    }

    public void setNumFlag(int numFlag) {
        numFlagLb.setText(String.valueOf(numFlag));
    }

    public void finishGame() {
        hintBtn.setText(BACK_ARROW);
        hintBtn.setFont(new Font("VNI", Font.PLAIN, 30));
        clock.stop();
    }

    public class Clock extends Thread implements Serializable {
        public Clock() {
        }

        public void run() {
            do {
                if (!stopClock) {
                    sec++;
                    if (sec == 60) {
                        min += 1;
                        sec = 1;
                    }
                    if (min == 60) {
                        hour += 1;
                        min = 1;
                    }
                    String S = hour + ":" + min + ":" + sec;
                    timeLb.setText(S);
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } while (!game.isFinished());
        }

    }

    @Override
    public void init() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND);
        backBtn = new JLabel(BACK);
        flagLb = new JLabel(FLAG);
        numFlagLb = new JLabel(String.valueOf(numFlag));
        clockLb = new JLabel(CLOCK);
        timeLb = new JLabel();
        clock = new Clock();
        clock.start();
        hintBtn = new JLabel(HINT);
        gbc = new GridBagConstraints();
    }

    @Override
    public void addView() {
        //backBtn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        backBtn.setFont(new Font("VNI", Font.BOLD, 30));

        //flagLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        flagLb.setFont(new Font("VNI", Font.PLAIN, 25));

        flagLb.setForeground(Color.RED);

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
        add(backBtn, gbc);

        gbc.gridx = 1;
        add(new JLabel(), gbc);

        gbc.gridx = 2;
        add(flagLb, gbc);

        gbc.gridx = 3;
        add(numFlagLb, gbc);

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
                parentFrame.setLocation(parentFrame.getLocation().x + xDrag - xPress, parentFrame.getLocation().y + yDrag - yPress);
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
                State state = listener.getGameState();
                if (state instanceof FinishState) {
                    listener.reGame();
                    return;
                }

                listener.setHint();

                if (listener.getGameState() instanceof HintState) {
                    hintBtn.setForeground(new Color(255, 255, 0));
                } else {
                    hintBtn.setForeground(Color.BLACK);
                }
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
                if (listener.getGameState() instanceof HintState) {
                    return;
                }
                unTarget(hintBtn);
            }
        });

    }

    public void addListener(IStatusPanelListener event) {
        listener = event;
    }

    public void addEventButtonListener(ISoundEventButton eventButton) {
        this.eventButton = eventButton;
    }

    public void reviewMode() {
        clockLb.setText(RIGHT_ARROW);
        timeLb.setVisible(false);
        flagLb.setVisible(false);
        numFlagLb.setText(LEFT_ARROW);
        clockLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventButton.playSoundClickButton();
                listener.getGameState().reviewNext();
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
                listener.getGameState().reviewPrevious();
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

    private void target(Component c) {
        c.setForeground(Color.RED);
    }

    private void unTarget(Component c) {
        c.setForeground(Color.BLACK);
    }

    private void targetHint(Component c) {
        c.setForeground(new Color(255, 255, 0));
    }

    public void setParentFrame(JFrame jFrame) {
        this.parentFrame = jFrame;
    }

}