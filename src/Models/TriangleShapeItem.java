package Models;


import java.awt.*;

public class TriangleShapeItem {
    private final Shape triangleShape;
    private final GridPoint a;
    private final GridPoint b;
    private final GridPoint c;

    public boolean isMouseMoved = false;

    public TriangleShapeItem(GridPoint a, GridPoint b, GridPoint c) {
        this.a = a;
        this.b = b;
        this.c = c;
        triangleShape = createTriangleShape();


    }

    public Shape getTriangleShape() {
        return triangleShape;
    }

    public GridPoint getA() {
        return a;
    }

    public GridPoint getB() {
        return b;
    }

    public GridPoint getC() {
        return c;
    }

    private Shape createTriangleShape() {
        int[] x = {a.x, b.x, c.x};
        int[] y = {a.y, b.y, c.y};
        return new Polygon(x, y, x.length);
    }

    void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }
}
