package Views;

import Interfaces.IGamePlayListener;
import Controller.MineSweeperGame;
import Interfaces.IPanel;
import Models.Cell;
import Models.MineGrid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.custom.Theme.*;

public class MineGridPanel extends JPanel implements IPanel {
    private int num_rows;
    private int num_columns;
    private Label[][] lbCell;
    private IGamePlayListener listener;
    private int numCellUnRevealed;

    public MineGridPanel(int rows, int columns) {
        this.num_rows = rows;
        this.num_columns = columns;
        init();
        addView();
        addEvent();
    }
    public MineGridPanel(MineGridPanel old){

    }

    @Override
    public void init() {
        setLayout(new GridLayout(num_rows, num_columns));
    }

    @Override
    public void addView() {
        lbCell = new Label[num_rows][num_columns];
        for (int    i = 0; i < lbCell.length; i++) {
            for (int j = 0; j < lbCell[0].length; j++) {
                lbCell[i][j] = new Label();
                lbCell[i][j].setOpaque(true);
                if((i + j) % 2 == 0){
                    lbCell[i][j].setBackground(new Color(169,207,81));
                } else {
                    lbCell[i][j].setBackground(new Color(176,213,88));
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
                        Label label = (Label) e.getComponent();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            listener.reveal(label.x, label.y);
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            listener.flag(label.x, label.y);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        Label label = (Label) e.getComponent();
                        if(!listener.getListCell()[label.x][label.y].isRevealed()){
                            label.setBackground(new Color(195,223,129));
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        Label label = (Label) e.getComponent();
                        if(!listener.getListCell()[label.x][label.y].isRevealed()){
                           // label.setBackground(new Color(195,223,129));
                            if((label.x + label.y) % 2 == 0){
                                label.setBackground(new Color(169,207,81));
                            } else {
                                label.setBackground(new Color(176,213,88));
                            }
                        }
                    }
                });
            }
        }
    }

    public void addListener(IGamePlayListener event) {
        listener = event;
    }

    public void updateGrid() {
        Font font = new Font("VNI", Font.PLAIN, 20);
        numCellUnRevealed = 0;
        Cell[][] listCell = listener.getListCell();
        for (int i = 0; i < listCell.length; i++) {
            for (int j = 0; j < listCell[0].length; j++) {
                lbCell[i][j].setFont(font);
                lbCell[i][j].setBorder(null);
                lbCell[i][j].removeAll();
                lbCell[i][j].setForeground(Color.BLACK);
                if (!listCell[i][j].isRevealed()) {
                    if((i + j) % 2 == 0){
                        lbCell[i][j].setBackground(new Color(169,207,81));
                    } else {
                        lbCell[i][j].setBackground(new Color(176,213,88));
                    }
                    numCellUnRevealed++;
                    if (!listCell[i][j].isFlagged()) {
                        lbCell[i][j].setText("");
                    } else {
                        lbCell[i][j].setText(FLAG); // 'flag'
                        lbCell[i][j].setForeground(Color.red);
                    }
                }
                else {
                    if (listCell[i][j].isMine()) {
                        lbCell[i][j].setText(BOMB); // 'bomb'
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
                    if(i<num_rows-1) if(!listCell[i+1][j].isRevealed()) c = 3;
                    if(j<num_columns-1) if(!listCell[i][j+1].isRevealed()) d = 3;
                    lbCell[i][j].setBorder(BorderFactory.createMatteBorder(a,b,c,d,new Color(141,173,65) ));

                    if(i>0 && j > 0) if(a == 0 && b == 0 && !listCell[i-1][j-1].isRevealed()){
                        JPanel corner = new JPanel();
                        corner.setBackground(new Color(141,173,65));
                        corner.setBounds(0,0,3,3);
                        lbCell[i][j].add(corner);
                    }
                    if(i < num_rows -1 && j > 0) if( b == 0 && c == 0 && !listCell[i+1][j-1].isRevealed()){
                        JPanel corner = new JPanel();
                        corner.setBackground(new Color(141,173,65));
                        corner.setBounds(0, CELL_SIZE - 3,3,3 );
                        lbCell[i][j].add(corner);
                    }
                    if(i > 0 && j < num_columns -1 ) if( a == 0 && d == 0 && !listCell[i-1][j+1].isRevealed()){
                        JPanel corner = new JPanel();
                        corner.setBackground(new Color(141,173,65));
                        corner.setBounds(CELL_SIZE-3, 0,3,3 );
                        lbCell[i][j].add(corner);
                    }
                    if(i< num_rows -1  && j < num_columns -1 ) if(c == 0 && d == 0 && !listCell[i+1][j+1].isRevealed()){
                        JPanel corner = new JPanel();
                        corner.setBackground(new Color(141,173,65));
                        corner.setBounds(CELL_SIZE -3,CELL_SIZE - 3,3,3);
                        lbCell[i][j].add(corner);
                    }
                    if((i + j) % 2 == 0) lbCell[i][j].setBackground(new Color(210,184,154));
                    else lbCell[i][j].setBackground(new Color(223,194,161));


                }
            }
        }
    }
    public void target(Component c){
//        c.setBackground(BACKGROUND);
        c.setForeground(FOREGROUND);
        c.setBackground(new Color(195,223,129));
    }
    public void unTarget(Component c){
//        c.setBackground(FOREGROUND);
        c.setForeground(BACKGROUND);
    }

    private class Label extends JLabel {
        private int x;
        private int y;
    }

    public int getNumCellUnRevealed() {
        return numCellUnRevealed;
    }
}