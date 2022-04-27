package Views.custom;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private int radius;
    public RoundedButton(int radius) {
        super();
        this.radius = radius;
        setFocusable(false);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }


    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,radius,radius);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0,0,0, 0));
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,radius,radius);
    }
}