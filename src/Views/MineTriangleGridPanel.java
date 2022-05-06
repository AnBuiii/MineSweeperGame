package Views;

import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Models.TriangleLabel;
import javax.swing.*;
import java.awt.*;
import static Views.custom.Theme.*;


public class MineTriangleGridPanel extends JPanel implements IPanel {
    private TriangleLabel[][] lbCell;
    private IGamePlayListener listener;
    private int numCellUnRevealed;
    private Shape[][] triangleShape;
    private Graphics grp;
    private int gridWidth;
    private int gridHeight;

    public MineTriangleGridPanel(int gridHeight, int gridWidth){
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        init();
       // addView();
        addEvent();
    }
    @Override
    public void init() {

    }

    @Override
    public void addView() {

    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        int gridSize = 640;
        int cellSize = CELL_SIZE;
        int gridWidth = 2 * (gridSize / cellSize) - 1;
        int gridHeight = (gridSize / cellSize);

        triangleShape = new Shape[gridHeight][gridWidth];

        Point a = new Point(0, 0);
        Point b = new Point(cellSize / 2, cellSize);
        Point c = new Point(cellSize, 0);

        for(int i = 0; i < gridHeight; i ++){
            for (int j = 0; j < gridWidth; j ++){

                triangleShape[i][j] = CreateTriangleShape(a, b, c);
                g2d.setColor(Color.black);
                g2d.draw(triangleShape[i][j]);
                g2d.setColor(Color.gray);
                g2d.fill(triangleShape[i][j]);
                a = (Point) b.clone();
                b = (Point) c.clone();
                c = (Point) a.clone();

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

    private Shape CreateTriangleShape(Point a, Point b, Point c) {
        int[] x = {a.x, b.x, c.x};
        int[] y = {a.y, b.y, c.y};
        return new Polygon(x, y, x.length);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        grp = g.create();
        doDrawing(grp);
    }

    @Override
    public void addEvent() {

    }
}
