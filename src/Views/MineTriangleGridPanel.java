package Views;

import Interfaces.IGamePlayListener;
import Interfaces.IPanel;
import Models.Cell;
import Models.TriangleLabel;
import Models.TriangleShapeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
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
    private Cell[][] cells;

    public MineTriangleGridPanel(int gridHeight, int gridWidth){
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;

        init();
        addView();
        addEvent();
    }

    @Override
    public void init() {

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
        Font font = FONT;
        g2d.setFont(font);
        // vẽ
        for(int i = 0; i < this.gridHeight; i ++){
            for (int j = 0; j < this.gridWidth; j ++){
                if(cells == null){
                    //khởi tạo
                   if(triangleShape[i][j].isMouseMoved){
                       // g2d.setColor(new Color(176,213,88, 50));
                       g2d.setColor(Color.GREEN);
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                    }
                    else {
                        if((i + j) % 2 == 0){
                            g2d.setColor(new Color(169,207,81));
                        }
                        else {
                            g2d.setColor(new Color(176,213,88));
                        }
                       // g2d.setColor(Color.gray);
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                      //  g2d.setColor(Color.black);
                       // g2d.draw(triangleShape[i][j].getTriangleShape());

                    }
                    //System.out.println("lô");
                }
                else{
                    Point center = pointCenterOfTriangle(triangleShape[i][j].getA(),triangleShape[i][j].getB(),triangleShape[i][j].getC());
                    center.x -= 6;
                    center.y += 6;
                    if(cells[i][j].isRevealed()){
                        if((i + j) % 2 == 0){
                            g2d.setColor(new Color(210,184,154));
                        }else {
                            g2d.setColor(new Color(223,194,161));
                        }
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                        if(cells[i][j].isMine()){
                            g2d.setColor(Color.black);
                            ;
                            g2d.drawString(BOMB, center.x, center.y); // bomb
                        }
                        else{
                            int x = cells[i][j].getNumMineAround();
                            if(x != 0){
                                String s = String.valueOf(x);
                                //g2d.setColor(Color.blue);
                                switch (x) {
                                    case 1 -> g2d.setColor(new Color(0, 0, 255));
                                    case 2 -> g2d.setColor(new Color(0, 132, 0));
                                    case 3 -> g2d.setColor(new Color(255, 0, 0));
                                    case 4 -> g2d.setColor(new Color(0, 0, 132));
                                    case 5 -> g2d.setColor(new Color(132, 0, 0));
                                    case 6 -> g2d.setColor(new Color(0, 132, 132));
                                    case 7 -> g2d.setColor(new Color(132, 0, 132));
                                    case 8 -> g2d.setColor(new Color(132, 132, 132));
                                    case 9 -> g2d.setColor(new Color(132, 132, 1));
                                    case 10 -> g2d.setColor(new Color(132, 132, 180));
                                    case 11 -> g2d.setColor(new Color(132, 0, 180));
                                    case 12 -> g2d.setColor(new Color(132, 230, 230));

                                }
                                g2d.drawString(s, center.x, center.y);
                            }
                        }
                       // g2d.setColor(Color.black);
                      //  g2d.draw(triangleShape[i][j].getTriangleShape());
                    }
                    else {
                        if((i + j) % 2 == 0){
                            g2d.setColor(new Color(169,207,81));
                        }
                        else {
                            g2d.setColor(new Color(176,213,88));
                        }
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                        if(cells[i][j].isFlagged()){
                            //System.out.println("(" + i + "," + j + ")");
                          //  g2d.setColor(Color.gray);
                           // g2d.fill(triangleShape[i][j].getTriangleShape());
                            //System.out.println("ủa alo");
                            g2d.setColor(Color.red);
                            g2d.drawString(FLAG, center.x, center.y);   //flag
                            //g2d.setColor(Color.black);
                           // g2d.draw(triangleShape[i][j].getTriangleShape());
                        }
                        else if(triangleShape[i][j].isMouseMoved){
                            g2d.setColor(Color.GREEN);
                            g2d.fill(triangleShape[i][j].getTriangleShape());
                           // g2d.setColor(Color.black);
                            //g2d.draw(triangleShape[i][j].getTriangleShape());
                        }
                    }
                }
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
                findTriangleCellClicked(pointMouse, e);
            }

        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                double x = e.getPoint().getLocation().getX();
                double y = e.getPoint().getLocation().getY();
                Point2D pointMouse = new Point2D.Double(x, y);
                findTriangleCellClicked(pointMouse, e);
            }
        });


    }

    public boolean pointInTriangle(Point2D p, Point p1, Point p2, Point p3) {
        var alpha = ((p2.y - p3.y) * (p.getX() - p3.x) + (p3.x - p2.x) * (p.getY() - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        var beta = ((p3.y - p1.y) * (p.getX() - p3.x) + (p1.x - p3.x) * (p.getY() - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        var gamma = 1.0 - alpha - beta;

        return (alpha > 0 && beta > 0 && gamma > 0);
    }

    public void updateTriangleGridPanel(){
        this.cells = listener.getListCell();
        repaint();
    }

    public void findTriangleCellClicked(Point2D mouse, MouseEvent event){
        for(int i = 0; i < triangleShape.length; i ++){
            for(int j = 0; j < triangleShape[0].length; j ++){
                if(pointInTriangle(mouse, triangleShape[i][j].getA(), triangleShape[i][j].getB(), triangleShape[i][j].getC())){
                    //System.out.println(i + " " + j);
                    if(event.getButton() == MouseEvent.BUTTON1){
                        listener.reveal(i, j);
                        System.out.println("left clicked!");
                    }
                    else if(event.getButton() == MouseEvent.BUTTON3){
                        listener.flag(i, j);
                    }
                    else {
                        triangleShape[i][j].isMouseMoved = true;
                        repaint();
                    }
                }
                else {
                    triangleShape[i][j].isMouseMoved = false;
                    repaint();
                }
            }
        }
    }

    public void addListener(IGamePlayListener event) {
        listener = event;
    }
    private Point pointCenterOfTriangle(Point a, Point b, Point c){
        return new Point((a.x + b.x + c.x) / 3, (a. y + b.y + c.y) / 3);
    }

}
