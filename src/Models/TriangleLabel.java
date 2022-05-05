package Models;

import javax.swing.*;
import java.awt.*;

public class TriangleLabel extends JLabel {
    private Shape triangle = createTriangle();

    public void paintBorder( Graphics g ) {

        ((Graphics2D)g).draw(triangle);
    }
    public void paintComponent( Graphics g ) {

          ((Graphics2D) g).fill(triangle);
    }

    public Dimension getPreferredSize() {

        return new Dimension(300,  600);
    }
    public boolean contains(int x, int y) {
        return triangle.contains(x, y);
    }

    private Shape createTriangle() {
        Polygon p = new Polygon();
        p.addPoint( 0   , 100 );
        p.addPoint( 100 , 0   );
        p.addPoint( 200 ,100  );
        return p;
    }
}
