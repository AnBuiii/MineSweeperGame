package Views.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RotateLabel extends JLabel {
    public RotateLabel(String text) {
        super(text);
        Font font = new Font("VNI",Font.BOLD,70);
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(text, null);
        setBounds(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight());
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D) g;
        gx.rotate(0.4, getX() + getWidth()/2, getY() + getHeight()/2);
        super.paintComponent(g);
    }
}