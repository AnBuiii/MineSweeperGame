package Models;

import java.awt.*;

public class TriangleShapeItem {
    private Shape triangleShape;
    private Point a;
    private Point b;
    private Point c;
    private boolean isChecked;

    public TriangleShapeItem(Point a, Point b, Point c){
        this.a = a;
        this.b = b;
        this.c = c;
        triangleShape = createTriangleShape();
        isChecked = false;
    }
    public void setChecked(boolean checked){
        this.isChecked = checked;
    }
    public boolean isChecked(){
        return this.isChecked;
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
