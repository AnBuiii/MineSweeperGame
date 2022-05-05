package Views;

import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Models.MineGrid;
import Models.TriangleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MineTriangleGridPanel extends JPanel implements IPanel {
    private TriangleLabel[][] lbCell;
    private IGamePlayListener listener;
    private int numCellUnRevealed;

    public MineTriangleGridPanel(){
        init();
        addView();
        addEvent();
    }
    @Override
    public void init() {
        setSize(640, 640);
        //setLayout(new GridLayout(640, 640));
    }

    @Override
    public void addView() {
        Label x = new Label();
        int gridSize = 640;
        int cellSize = 40;
        int gridWidth = 2 * (gridSize / cellSize) - 1;
        int gridHeight = (gridSize / cellSize);
        lbCell = new TriangleLabel[gridHeight][gridWidth];
        Point a = new Point(0, 0);
        Point b = new Point(cellSize / 2, cellSize);
        Point c = new Point(cellSize, 0);

        for(int i = 0; i < gridHeight; i ++){
            for (int j = 0; j < gridWidth; j ++){
               // lbCell[i][j] = new TriangleLabel(a, b, c);
                lbCell[i][j] = new TriangleLabel();

                if((i + j) % 2 == 0){
                    lbCell[i][j].setBackground(new Color(169,207,81));
                } else {
                    lbCell[i][j].setBackground(new Color(176,213,88));
                }
                add(lbCell[i][j]);
                c.x += cellSize;
            }

            a.setLocation(0, a.y + cellSize);

            if(i % 2 == 0){
                c.setLocation(a.x + cellSize, a.y);
                b.setLocation(a.x + cellSize / 2, a.y - cellSize);
            }else {
                c.setLocation(cellSize, a.y);
                b.setLocation(cellSize / 2, b.y + cellSize);
            }
        }
    }


    @Override
    public void addEvent() {

    }
}
