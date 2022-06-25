package Views;

import Interfaces.IPanel;
import Interfaces.IQuitGamePanelListener;
import Interfaces.ISoundEventButton;
import Interfaces.IToolBarListener;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static Views.custom.Theme.*;

public class ToolBarPanel extends JPanel implements IPanel, IQuitGamePanelListener {
    private static JLabel minimizeLb;
    private JLabel closeLb;
    private GridBagConstraints gbc;

    private int xDrag;
    private int yDrag;

    private int xPress;
    private int yPress;

    private JFrame parentFrame;
    private ISoundEventButton eventButton;
    private IToolBarListener listener;
    public ToolBarPanel() {
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setLayout(null);
        setBackground(BACKGROUND);
        minimizeLb = new JLabel(MINIMIZE);
        closeLb = new JLabel(CLOSE);

    }
    @Override
    public void addView() {

        minimizeLb.setFont(new Font("VNI", Font.PLAIN, 25));

        closeLb.setFont(new Font("VNI", Font.PLAIN, 25));

        minimizeLb.setOpaque(true);
        minimizeLb.setBackground(BACKGROUND);
        minimizeLb.setBounds(315, -5, 40, 45);
        minimizeLb.setVerticalAlignment(SwingConstants.TOP);
        minimizeLb.setHorizontalAlignment(SwingConstants.CENTER);

        closeLb.setOpaque(true);
        closeLb.setBackground(BACKGROUND);
        closeLb.setBounds(355, 0, 45,40);
        closeLb.setVerticalAlignment(SwingConstants.CENTER);
        closeLb.setHorizontalAlignment(SwingConstants.CENTER);


        add(closeLb);
        add(minimizeLb);


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

        minimizeLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventButton.playSoundClickButton();
                listener.minimizeWindow();
                super.mouseClicked(e);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                eventButton.playSoundHoverButton();
                target(minimizeLb);
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                unTarget(minimizeLb);
                super.mouseExited(e);
            }
        });

        closeLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventButton.playSoundClickButton();
                openQuitGamePanel();
                super.mouseClicked(e);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                eventButton.playSoundHoverButton();
                target(closeLb);
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                unTarget(closeLb);
                super.mouseExited(e);
            }
        });


    }

    private void openQuitGamePanel(){
        this.setForeground(new Color(1.0f,1.0f,1.0f,0));
        QuitGamePanel quitGamePanel = new QuitGamePanel();
        quitGamePanel.setLocationRelativeTo(parentFrame);
        quitGamePanel.setVisible(true);
        quitGamePanel.addListener(this);
        quitGamePanel.addEventButtonListener(eventButton);
        parentFrame.setEnabled(false);
    }

    public void addListener(IToolBarListener event) {
        listener = event;

    }
    public void addEventButtonListener(ISoundEventButton eventButton){
        this.eventButton = eventButton;
    }

    public void target(Component c){

        c.setBackground(new Color(104,190,58));
        c.setForeground( new Color(192,188,185));

    }
    public void unTarget(Component c){
        c.setBackground(BACKGROUND);
        c.setForeground(Color.DARK_GRAY);
    }


    public void setParentFrame(JFrame jFrame){
        this.parentFrame = jFrame;
    }

    @Override
    public void closeBtnClicked() {
        parentFrame.setEnabled(true);
    }

    @Override
    public void noBtnClicked() {
        parentFrame.setEnabled(true);
    }

    @Override
    public void yesBtnClicked() {
        listener.quitGame();
    }
}