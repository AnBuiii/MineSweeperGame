package DesignPattern.GameProxy;

import java.awt.*;

public class TriangleType {

    private String name;
    private Color color;
    private boolean isMouseIn;

    public TriangleType(String name, Color color, boolean isMouseIn) {
        this.name = name;
        this.color = color;
        this.isMouseIn = isMouseIn;
    }

    public void draw(Graphics g, int[] x, int[] y, int n ) {
        g.setColor(color);
        g.fillPolygon(x, y, n);
//        g.set
    }
}
