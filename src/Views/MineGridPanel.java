package Views;

import Controller.IListener;
import Models.Cell;
import Models.MineGrid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

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
        Border border = BorderFactory.createLineBorder(Color.gray, 0);
        lbCell = new Label[MineGrid.NUM_ROWS][MineGrid.NUM_COLUMNS];
        for (int i = 0; i < lbCell.length; i++) {
            for (int j = 0; j < lbCell[0].length; j++) {
                lbCell[i][j] = new Label();
                lbCell[i][j].setOpaque(true);
                if((i + j) % 2 == 0){
                    lbCell[i][j].setBackground(new Color(169,207,81));
                } else {
                    lbCell[i][j].setBackground(new Color(176,213,88));
                }

                //lbCell[i][j].setBorder(border);
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
                            //Border border = BorderFactory.createLineBorder(new Color(141,173,65), 3);
                            //label.setBorder(border);
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
        Cell[][] listCell = listener.getListCell();
        for (int i = 0; i < listCell.length; i++) {
            for (int j = 0; j < listCell[0].length; j++) {
                lbCell[i][j].setFont(font);
                if (!listCell[i][j].isRevealed()) {
                    if((i + j) % 2 == 0){
                        lbCell[i][j].setBackground(new Color(169,207,81));
                    } else {
                        lbCell[i][j].setBackground(new Color(176,213,88));
                    }
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
                                case 1 -> lbCell[i][j].setForeground(new Color(0, 0, 255));
                                case 2 -> lbCell[i][j].setForeground(new Color(0, 132, 0));
                                case 3 -> lbCell[i][j].setForeground(new Color(255, 0, 0));
                                case 4 -> lbCell[i][j].setForeground(new Color(0, 0, 132));
                                case 5 -> lbCell[i][j].setForeground(new Color(132, 0, 0));
                                case 6 -> lbCell[i][j].setForeground(new Color(0, 132, 132));
                                case 7 -> lbCell[i][j].setForeground(new Color(132, 0, 132));
                                case 8 -> lbCell[i][j].setForeground(new Color(132, 132, 132));
                            }
                        }
                    }
                    int a= 0, b= 0, c= 0, d = 0;
                    if(i>0) if(!listCell[i-1][j].isRevealed()) a = 3;
                    if(j>0) if(!listCell[i][j-1].isRevealed()) b = 3;
                    if(i<MineGrid.NUM_ROWS-1) if(!listCell[i+1][j].isRevealed()) c = 3;
                    if(j<MineGrid.NUM_COLUMNS-1) if(!listCell[i][j+1].isRevealed()) d = 3;
                    lbCell[i][j].setBorder(BorderFactory.createMatteBorder(a,b,c,d,new Color(141,173,65) ));

                    if((i + j) % 2 == 0){
                        lbCell[i][j].setBackground(new Color(210,184,154));

                    } else {
                        lbCell[i][j].setBackground(new Color(223,194,161));
                    }
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