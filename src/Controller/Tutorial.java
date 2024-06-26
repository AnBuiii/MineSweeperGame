package Controller;

import Interfaces.IPanel;
import Interfaces.ITutorialPanelListener;
import Views.TutorialPanel;

import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class Tutorial extends JFrame implements IPanel, ITutorialPanelListener {
    public Home home;

    private TutorialPanel tutorialPanel;

    Tutorial() {
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setSize(WIDTH, HEIGHT);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        setLayout(null);

    }

    @Override
    public void addView() {
        tutorialPanel = new TutorialPanel();
        tutorialPanel.setBounds(0, 0, WIDTH, HEIGHT);
        tutorialPanel.addListener(this);
        add(tutorialPanel);

    }

    @Override
    public void addEvent() {

    }

    @Override
    public void back() {
        this.setVisible(false);
        home.reDrawHome();
        home.setVisible(true);

    }

    public void setHome(Home home) {
        this.home = home;
        tutorialPanel.addEventButtonListener(home.getEventSoundButton());
    }

}
