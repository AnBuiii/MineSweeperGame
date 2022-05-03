package Views;

import Controller.MineSweeperGame;
import Interfaces.IPanel;
import Interfaces.IStatusPanelListener;
import Models.GameDifficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;

public class StatusPanel extends JPanel implements IPanel {
    private JLabel lbNumCellUnrevealed;
    private JLabel lbNotify;
    private JButton btnRestart;
    private JLabel backBtn;
    private JLabel clockLb;
    private JLabel timeLb;
    private JLabel flagLb;
    private JLabel hintBtn;
    private IStatusPanelListener listener;

    public StatusPanel() {
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setLayout(null);
        backBtn = new JLabel("\u2190");
    }

    @Override
    public void addView() {
        backBtn.setBounds(0,0,50, STATUS_PANEL_HEIGHT);
        backBtn.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        backBtn.setVerticalAlignment(JLabel.CENTER);
        backBtn.setOpaque(true);
        backBtn.setBackground(Color.green);
        backBtn.setFont(new Font("VNI", Font.BOLD, 30));
        add(backBtn);
    }

    @Override
    public void addEvent() {
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {

            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listener.back();

            }
        });

    }

    public void addListener(IStatusPanelListener event) {
        listener = event;
    }

    public void updateStatus(int numSquareUnrevealed) {
//        lbNumCellUnrevealed.setText("Số ô chưa mở: " + numSquareUnrevealed);
//        if (numSquareUnrevealed == MineGrid.NUM_MINES) {
//            lbNotify.setText("THẮNG");
//            lbNotify.setForeground(Color.blue);
//        } else if (numSquareUnrevealed == 0) {
//            lbNotify.setText("THUA");
//            lbNotify.setForeground(Color.red);
//        }
    }

    public void load() {
        addEvent();
    }
}