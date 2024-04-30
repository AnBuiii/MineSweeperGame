package Views;

import Interfaces.IGamePlayListener;
import Models.Cell;
import Models.MineLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;
import static Views.custom.Theme.CELL_SIZE;

public class MineGridPanel extends MineGridPanelTemplate {
    public MineGridPanel(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public void addListener(IGamePlayListener event) {
        listener = event;
    }


    @Override
    public void init() {
        setLayout(new GridLayout(num_rows, num_columns));
    }

    @Override
    public void addView() {
        lbCell = new MineLabel[num_rows][num_columns];
        for (int i = 0; i < lbCell.length; i++) {
            for (int j = 0; j < lbCell[0].length; j++) {
                lbCell[i][j] = new MineLabel();
                lbCell[i][j].setOpaque(true);
                if ((i + j) % 2 == 0) {
                    lbCell[i][j].setBackground(new Color(169, 207, 81));
                } else {
                    lbCell[i][j].setBackground(new Color(176, 213, 88));
                }
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
                        MineLabel label = (MineLabel) e.getComponent();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            listener.getGameState().reveal(label.x, label.y);
                            System.out.println(listener.getGameState().toString());
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            listener.getGameState().flag(label.x, label.y);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        if (listener.isReviewMode()) return;
                        MineLabel label = (MineLabel) e.getComponent();
                        if (!listener.getListCell()[label.x][label.y].isRevealed()) {
                            label.setBackground(new Color(195, 223, 129));
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        if (listener.isReviewMode()) return;
                        MineLabel label = (MineLabel) e.getComponent();
                        if (!listener.getListCell()[label.x][label.y].isRevealed()) {
                            if ((label.x + label.y) % 2 == 0) {
                                label.setBackground(new Color(169, 207, 81));
                            } else {
                                label.setBackground(new Color(176, 213, 88));
                            }
                        }
                    }
                });
            }
        }
    }

    public void updateGrid() {
        Font font = new Font("VNI", Font.PLAIN, 20);
        Cell[][] listCell = listener.getListCell();
        for (int i = 0; i < listCell.length; i++) {
            for (int j = 0; j < listCell[0].length; j++) {
                MineLabel cell = lbCell[i][j];
                cell.setFont(font);
                cell.setBorder(null);
                cell.removeAll();
                cell.setForeground(Color.BLACK);
                if (!listCell[i][j].isRevealed()) {
                    if ((i + j) % 2 == 0) {
                        cell.setBackground(new Color(169, 207, 81));
                    } else {
                        cell.setBackground(new Color(176, 213, 88));
                    }
                    if (!listCell[i][j].isFlagged()) {
                        cell.setText("");
                    } else {
                        cell.setText(FLAG); // 'flag'
                        cell.setForeground(Color.red);
                    }
                } else {
                    if (listCell[i][j].isMine()) {
                        cell.setText(BOMB); // 'bomb'
                    } else {
                        int numMineAround = listCell[i][j].getNumMineAround();
                        if (numMineAround == 0) {
                            cell.setText("");
                        } else {
                            cell.setText(numMineAround + "");
                            switch (numMineAround) {
                                case 1 -> cell.setForeground(new Color(0, 0, 255));
                                case 2 -> cell.setForeground(new Color(0, 132, 0));
                                case 3 -> cell.setForeground(new Color(255, 0, 0));
                                case 4 -> cell.setForeground(new Color(0, 0, 132));
                                case 5 -> cell.setForeground(new Color(132, 0, 0));
                                case 6 -> cell.setForeground(new Color(0, 132, 132));
                                case 7 -> cell.setForeground(new Color(132, 0, 132));
                                case 8 -> cell.setForeground(new Color(132, 132, 132));
                            }
                        }
                    }

                    int a = 0, b = 0, c = 0, d = 0;
                    if (i > 0) if (!listCell[i - 1][j].isRevealed()) a = 3;
                    if (j > 0) if (!listCell[i][j - 1].isRevealed()) b = 3;
                    if (i < num_rows - 1) if (!listCell[i + 1][j].isRevealed()) c = 3;
                    if (j < num_columns - 1) if (!listCell[i][j + 1].isRevealed()) d = 3;
                    cell.setBorder(BorderFactory.createMatteBorder(a, b, c, d, new Color(141, 173, 65)));

                    if (i > 0 && j > 0) if (a == 0 && b == 0 && !listCell[i - 1][j - 1].isRevealed()) {
                        JPanel corner = new JPanel();
                        corner.setBackground(new Color(141, 173, 65));
                        corner.setBounds(0, 0, 3, 3);
                        cell.add(corner);
                    }
                    if (i < num_rows - 1 && j > 0) if (b == 0 && c == 0 && !listCell[i + 1][j - 1].isRevealed()) {
                        JPanel corner = new JPanel();
                        corner.setBackground(new Color(141, 173, 65));
                        corner.setBounds(0, CELL_SIZE - 3, 3, 3);
                        cell.add(corner);
                    }
                    if (i > 0 && j < num_columns - 1) if (a == 0 && d == 0 && !listCell[i - 1][j + 1].isRevealed()) {
                        JPanel corner = new JPanel();
                        corner.setBackground(new Color(141, 173, 65));
                        corner.setBounds(CELL_SIZE - 3, 0, 3, 3);
                        cell.add(corner);
                    }
                    if (i < num_rows - 1 && j < num_columns - 1)
                        if (c == 0 && d == 0 && !listCell[i + 1][j + 1].isRevealed()) {
                            JPanel corner = new JPanel();
                            corner.setBackground(new Color(141, 173, 65));
                            corner.setBounds(CELL_SIZE - 3, CELL_SIZE - 3, 3, 3);
                            cell.add(corner);
                        }
                    if ((i + j) % 2 == 0) cell.setBackground(new Color(210, 184, 154));
                    else cell.setBackground(new Color(223, 194, 161));
                }
            }
        }
    }
}
