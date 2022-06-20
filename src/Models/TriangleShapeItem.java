package Models;

import java.awt.*;

public class TriangleShapeItem {
    private Shape triangleShape;
    private Point a;
    private Point b;
    private Point c;

    public boolean isMouseMoved = false;

    public TriangleShapeItem(Point a, Point b, Point c){
        this.a = a;
        this.b = b;
        this.c = c;
        triangleShape = createTriangleShape();

    }

    public Shape getTriangleShape(){
        return triangleShape;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    private Shape createTriangleShape() {
        int[] x = {a.x, b.x, c.x};
        int[] y = {a.y, b.y, c.y};
        return new Polygon(x, y, x.length);
    }

}
