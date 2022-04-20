package Views;

import Controller.IListener;
import Models.MineGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends JPanel implements IPanel {
    private JLabel lbNumCellClosed;
    private JLabel lbNotify;
    private JButton btnRestart;
    private IListener listener;

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

        lbNumCellClosed = new JLabel();
        lbNumCellClosed.setFont(font);
        lbNumCellClosed.setText("Số ô chưa mở: " + MineGrid.NUM_ROWS * MineGrid.NUM_COLUMNS);
        lbNumCellClosed.setBounds(10, 10, 250, 40);
        add(lbNumCellClosed);

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
                lbNumCellClosed.setText("Số ô chưa mở: " + MineGrid.NUM_ROWS * MineGrid.NUM_COLUMNS);
                lbNotify.setText("");
            }
        });
    }

    public void addListener(IListener event) {
        listener = event;
    }

    public void updateStatus(int numSquareClosed) {
        lbNumCellClosed.setText("Số ô chưa mở: " + numSquareClosed);
        if (numSquareClosed == MineGrid.NUM_MINES) {
            lbNotify.setText("THẮNG");
            lbNotify.setForeground(Color.blue);
        } else if (numSquareClosed == 0) {
            lbNotify.setText("THUA");
            lbNotify.setForeground(Color.red);
        }
    }
}