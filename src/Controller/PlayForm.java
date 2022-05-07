package Controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayForm extends JFrame {

    public PlayForm(){
        JButton jButton = new JButton("Open TriangleMineSweeperGame");
        setSize(1000, 1000);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TriangleForm triangleForm = new TriangleForm();
                add(triangleForm);
            }
        });
    }
}
