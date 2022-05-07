package Views;

import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Models.TriangleLabel;
import Models.TriangleShapeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static Views.custom.Theme.*;


public class MineTriangleGridPanel extends JPanel implements IPanel {
    private TriangleLabel[][] lbCell;
    private IGamePlayListener listener;
    private int numCellUnRevealed;
    private TriangleShapeItem[][] triangleShape;
    private Graphics2D grp;
    private int gridWidth;
    private int gridHeight;
    private BufferedImage paintImage;
    private  int gridSize;

    public MineTriangleGridPanel(int gridHeight, int gridWidth){
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;

        try {
            // replace this image with your image
            paintImage = new BufferedImage(640, 640, BufferedImage.TYPE_INT_ARGB);

        } catch (final Exception e) {
            e.printStackTrace();
        }
        init();
        //grp = paintImage.createGraphics();
        addView();
        addEvent();
       // doDrawing(grp);
    }
    @Override
    public void init() {

        gridSize = 640;
        this.gridWidth = 2 * (gridSize / CELL_SIZE) - 1;
        this.gridHeight = (gridSize / CELL_SIZE);
        triangleShape = new TriangleShapeItem[gridHeight][gridWidth];

        // set các tọa độ để vẽ tam giác
        Point a = new Point(0, 0);
        Point b = new Point(CELL_SIZE / 2, CELL_SIZE);
        Point c = new Point(CELL_SIZE, 0);

        for(int i = 0; i < this.gridHeight; i ++){
            for (int j = 0; j < this.gridWidth; j ++){

                triangleShape[i][j] = new TriangleShapeItem(a, b, c);
                a = (Point) b.clone();
                b = (Point) c.clone();
                c = (Point) a.clone();

                c.x += CELL_SIZE;
            }

            a.setLocation(0, a.y +  CELL_SIZE);

            if(i % 2 == 0){
                c.setLocation(a.x +  CELL_SIZE, a.y);
                b.setLocation(a.x +  CELL_SIZE / 2, a.y -  CELL_SIZE);
            }else {
                c.setLocation( CELL_SIZE, a.y);
                b.setLocation( CELL_SIZE / 2, b.y + CELL_SIZE);
            }
        }
    }

    @Override
    public void addView() {

    }

    private void doDrawing(Graphics2D g){
        Graphics2D g2d = (Graphics2D) g.create();
        // vẽ
        for(int i = 0; i < this.gridHeight; i ++){
            for (int j = 0; j < this.gridWidth; j ++){
                if(!triangleShape[i][j].isChecked()){
                    g2d.setColor(Color.gray);
                    g2d.fill(triangleShape[i][j].getTriangleShape());
                }
                else{
                    g2d.setColor(Color.red);
                    g2d.fill(triangleShape[i][j].getTriangleShape());
                }
                g2d.setColor(Color.black);
                g2d.draw(triangleShape[i][j].getTriangleShape());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       grp = (Graphics2D) g.create();
           doDrawing(grp);

    }

    @Override
    public void addEvent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double x = e.getPoint().getLocation().getX();
                double y = e.getPoint().getLocation().getY();
                Point2D pointMouse = new Point2D.Double(x, y);
                findTriangleCellClicked(pointMouse);
            }
        });

    }

    public boolean pointInTriangle(Point2D p, Point p1, Point p2, Point p3) {
        var alpha = ((p2.y - p3.y) * (p.getX() - p3.x) + (p3.x - p2.x) * (p.getY() - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        var beta = ((p3.y - p1.y) * (p.getX() - p3.x) + (p1.x - p3.x) * (p.getY() - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        var gamma = 1.0 - alpha - beta;

        return (alpha > 0 && beta > 0 && gamma > 0);
    }

    public void findTriangleCellClicked(Point2D mouse){
        for(int i = 0; i < triangleShape.length; i ++){
            for(int j = 0; j < triangleShape[0].length; j ++){
                if(pointInTriangle(mouse, triangleShape[i][j].getA(), triangleShape[i][j].getB(), triangleShape[i][j].getC())){
                    System.out.println(i + " " + j);
                    triangleShape[i][j].setChecked(true);
//                    grp.setColor(Color.black);
//                    grp.draw(triangleShape[i][j].getTriangleShape());
//                    grp.setColor(Color.red);
//                    grp.fill(triangleShape[i][j].getTriangleShape());
                    repaint();
                }
            }
        }
    }


}
