package DesignPattern.GameTemplate;

import Interfaces.IGamePlayListener;
import Models.Cell;
import Models.GridPoint;
import Models.TriangleShapeItem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;

import static Views.custom.Theme.*;


public class MineTriangleGridPanel extends MineGridPanelTemplate {
    private IGamePlayListener listener;
    private TriangleShapeItem[][] triangleShape;
    private final int gridWidth;
    private final int gridHeight;
    private Cell[][] cells;

    public MineTriangleGridPanel(int gridHeight, int gridWidth) {
        super(gridWidth, gridHeight);
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;

        init();
        addView();
        addEvent();
    }

    @Override
    public void updateGrid() {
        this.cells = listener.getListCell();
        for (TriangleShapeItem[] triangleShapeItems : this.triangleShape) {
            for (TriangleShapeItem triangleShapeItem : triangleShapeItems) {
                triangleShapeItem.isMouseMoved = false;
            }
        }
        repaint();
    }

    @Override
    public void init() {
        triangleShape = new TriangleShapeItem[gridHeight][gridWidth];

        // set các tọa độ để vẽ tam giác
        GridPoint a = new GridPoint(0, 0);
        GridPoint b = new GridPoint(CELL_SIZE / 2, CELL_SIZE);
        GridPoint c = new GridPoint(CELL_SIZE, 0);

        for (int i = 0; i < this.gridHeight; i++) {
            for (int j = 0; j < this.gridWidth; j++) {

                triangleShape[i][j] = new TriangleShapeItem(a, b, c);
                a = b.clone();
                b = c.clone();
                c = a.clone();

                c.x += CELL_SIZE;
            }

            a.move(0, a.y + CELL_SIZE);

            if (i % 2 == 0) {
                c.move(a.x + CELL_SIZE, a.y);
                b.move(a.x + CELL_SIZE / 2, a.y - CELL_SIZE);
            } else {
                c.move(CELL_SIZE, a.y);
                b.move(CELL_SIZE / 2, b.y + CELL_SIZE);
            }
        }
    }

    @Override
    public void addView() {

    }

    private void doDrawing(Graphics2D g2d) {
        g2d.setFont(FONT);

        // vẽ
        for (int i = 0; i < this.gridHeight; i++) {
            for (int j = 0; j < this.gridWidth; j++) {
                if (cells == null) {
                    //khởi tạo
                    if (triangleShape[i][j].isMouseMoved) {
                        g2d.setColor(new Color(195, 223, 129));
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                    } else {
                        if ((i + j) % 2 == 0) {
                            g2d.setColor(new Color(169, 207, 81));
                        } else {
                            g2d.setColor(new Color(176, 213, 88));
                        }
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                    }
                } else {
                    GridPoint center = GridPoint.pointCenterOfTriangle(triangleShape[i][j].getA(), triangleShape[i][j].getB(), triangleShape[i][j].getC());
                    center.x -= 6;
                    center.y += 6;
                    if (cells[i][j].isRevealed()) {
                        if ((i + j) % 2 == 0) {
                            g2d.setColor(new Color(210, 184, 154));
                        } else {
                            g2d.setColor(new Color(223, 194, 161));
                        }
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                        if (cells[i][j].isMine()) {
                            g2d.setColor(Color.black);
                            g2d.drawString(BOMB, center.x - 3, center.y + 3); // bomb
                        } else {
                            int x = cells[i][j].getNumMineAround();
                            if (x != 0) {
                                String s = String.valueOf(x);
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
                    } else {
                        if ((i + j) % 2 == 0) {
                            g2d.setColor(new Color(169, 207, 81));
                        } else {
                            g2d.setColor(new Color(176, 213, 88));
                        }
                        g2d.fill(triangleShape[i][j].getTriangleShape());
                        if (cells[i][j].isFlagged()) {
                            g2d.setColor(Color.red);
                            g2d.drawString(FLAG, center.x, center.y);   //flag
                        } else if (triangleShape[i][j].isMouseMoved) {
                            g2d.setColor(new Color(195, 223, 129));
                            g2d.fill(triangleShape[i][j].getTriangleShape());
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grp = (Graphics2D) g.create();
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

    public void findTriangleCellClicked(Point2D mouse, MouseEvent event) {
        if (listener.isReviewMode()) return;
        for (int i = 0; i < triangleShape.length; i++) {
            for (int j = 0; j < triangleShape[0].length; j++) {
                if (GridPoint.pointInTriangle(mouse, triangleShape[i][j].getA(), triangleShape[i][j].getB(), triangleShape[i][j].getC())) {
                    //System.out.println(i + " " + j);
                    if (event.getButton() == MouseEvent.BUTTON1) {
                        listener.getGameState().reveal(i, j);
                    } else if (event.getButton() == MouseEvent.BUTTON3) {
                        listener.getGameState().flag(i, j);
                    } else {
                        triangleShape[i][j].isMouseMoved = true;
                    }
                    repaint();
                } else {
                    triangleShape[i][j].isMouseMoved = false;
                    repaint();
                }
            }
        }
    }

    public void addListener(IGamePlayListener event) {
        listener = event;
    }


    public void mark(int x, int y) {
        triangleShape[x][y].isMouseMoved = true;
        repaint();
    }

}
