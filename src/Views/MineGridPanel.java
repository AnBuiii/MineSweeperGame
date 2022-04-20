package Views;

import Controller.IListener;
import Models.Cell;
import Models.MineGrid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineGridPanel extends JPanel implements IPanel {
    private Label[][] lbCell;
    private IListener listener;
    private int numCellUnRevealed;

    public MineGridPanel() {
        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {
        setLayout(new GridLayout(MineGrid.NUM_ROWS, MineGrid.NUM_COLUMNS));
    }

    @Override
    public void addView() {
        Border border = BorderFactory.createLineBorder(Color.gray, 1);
        lbCell = new Label[MineGrid.NUM_ROWS][MineGrid.NUM_COLUMNS];
        for (int i = 0; i < lbCell.length; i++) {
            for (int j = 0; j < lbCell[0].length; j++) {
                lbCell[i][j] = new Label();
                lbCell[i][j].setOpaque(true);
                lbCell[i][j].setBackground(new Color(242, 242, 242));
                lbCell[i][j].setBorder(border);
                lbCell[i][j].setHorizontalAlignment(JLabel.CENTER);
                lbCell[i][j].setVerticalAlignment(JLabel.CENTER);
                add(lbCell[i][j]);
            }
        }
    }

    @Override
    public void addEvent() {
        for (int i = 0; i < lbCell.length; i++) {
            for (int j = 0; j < lbCell[0].length; j++) {
                lbCell[i][j].x = i;
                lbCell[i][j].y = j;
                lbCell[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        Label label = (Label) e.getComponent();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            listener.reveal(label.x, label.y);
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            listener.flag(label.x, label.y);
                        }
                    }
                });
            }
        }
    }

    public void addListener(IListener event) {
        listener = event;
    }

    public void updateGrid() {
        Font font = new Font("VNI", Font.PLAIN, 20);
        numCellUnRevealed = 0;
        Cell[][] listCell = listener.getListSquare();
        for (int i = 0; i < listCell.length; i++) {
            for (int j = 0; j < listCell[0].length; j++) {
                lbCell[i][j].setFont(font);
                if (!listCell[i][j].isRevealed()) {
                    lbCell[i][j].setBackground(new Color(242, 242, 242));
                    lbCell[i][j].setForeground(Color.black);
                    numCellUnRevealed++;
                    if (!listCell[i][j].isFlagged()) {
                        lbCell[i][j].setText("");
                    } else {
                        lbCell[i][j].setText("\uD83D\uDEA9"); // 'flag'
                    }
                } else {
                    if (listCell[i][j].isMine()) {
                        lbCell[i][j].setText("\uD83D\uDCA3"); // 'bomb'
                    } else {
                        int numMineAround = listCell[i][j].getNumMineAround();
                        if (numMineAround == 0) {
                            lbCell[i][j].setText("");
                        } else {
                            lbCell[i][j].setText(numMineAround + "");
                            switch (numMineAround) {
                                case 1 -> lbCell[i][j].setForeground(new Color(128, 128, 128));
                                case 2 -> lbCell[i][j].setForeground(new Color(255, 0, 0));
                                case 3 -> lbCell[i][j].setForeground(new Color(0, 204, 0));
                                case 4 -> lbCell[i][j].setForeground(new Color(102, 0, 255));
                                case 5 -> lbCell[i][j].setForeground(new Color(128, 128, 128));
                                case 6 -> lbCell[i][j].setForeground(new Color(255, 0, 0));
                                case 7 -> lbCell[i][j].setForeground(new Color(0, 204, 0));
                                case 8 -> lbCell[i][j].setForeground(new Color(102, 0, 255));
                            }
                        }
                    }
                    lbCell[i][j].setBackground(Color.white);
                }
            }
        }
    }

    private class Label extends JLabel {
        private int x;
        private int y;
    }

    public int getNumCellUnRevealed() {
        return numCellUnRevealed;
    }
}