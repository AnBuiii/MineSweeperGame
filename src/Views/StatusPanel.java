package Views;

import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Models.MineGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel implements IPanel {
    private JLabel lbNumCellUnrevealed;
    private JLabel lbNotify;
    private JButton btnRestart;
    private IGamePlayListener listener;

    public StatusPanel() {
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setLayout(null);
    }

    @Override
    public void addView() {
        Font font = new Font("VNI", Font.PLAIN, 20);

        lbNumCellUnrevealed = new JLabel();
        lbNumCellUnrevealed.setFont(font);
        lbNumCellUnrevealed.setText("Số ô chưa mở: " + MineGrid.NUM_ROWS * MineGrid.NUM_COLUMNS);
        lbNumCellUnrevealed.setBounds(10, 10, 250, 40);
        add(lbNumCellUnrevealed);

        lbNotify = new JLabel();
        lbNotify.setFont(font);
        lbNotify.setBounds(270, 10, 200, 40);
        add(lbNotify);

        btnRestart = new JButton();
        btnRestart.setFont(font);
        btnRestart.setText("Chơi lại");
        btnRestart.setBounds(490, 10, 200, 40);
        add(btnRestart);
    }

    @Override
    public void addEvent() {
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.restart();
                lbNumCellUnrevealed.setText("Số ô chưa mở: " + MineGrid.NUM_ROWS * MineGrid.NUM_COLUMNS);
                lbNotify.setText("");
            }
        });
    }

    public void addListener(IGamePlayListener event) {
        listener = event;
    }

    public void updateStatus(int numSquareUnrevealed) {
        lbNumCellUnrevealed.setText("Số ô chưa mở: " + numSquareUnrevealed);
        if (numSquareUnrevealed == MineGrid.NUM_MINES) {
            lbNotify.setText("THẮNG");
            lbNotify.setForeground(Color.blue);
        } else if (numSquareUnrevealed == 0) {
            lbNotify.setText("THUA");
            lbNotify.setForeground(Color.red);
        }
    }
}