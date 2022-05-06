package Models;

import javax.swing.*;
import java.awt.*;

public class TriangleLabel extends JLabel {
    private Shape triangle;

    public TriangleLabel(Point a, Point b, Point c){
        triangle = createTriangle(a, b, c);

    }

    public void paintBorder( Graphics g ) {

        ((Graphics2D)g).draw(triangle);
    }
    public void paintComponent( Graphics g ) {
        ((Graphics2D) g).setPaint(Color.blue);
          ((Graphics2D) g).fill(triangle);
    }

    @Override
    public Dimension getMaximumSize() {
        return super.getMaximumSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return super.getMinimumSize();
    }

    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
    public boolean contains(int x, int y) {
        return triangle.contains(x, y);
    }

    private Shape createTriangle(Point a, Point b, Point c) {
        //System.out.println("haha");
        int[] x = {a.x, b.x, c.x};
        int[] y = {a.y, b.y, c.y};
        return new Polygon(x, y, x.length);
    }
}
