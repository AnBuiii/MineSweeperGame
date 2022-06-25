import Controller.Home;

import javax.swing.*;
import java.util.Objects;

public class Main  {
    ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Icons/bomb.png")));
    public static void main(String[] args) {

            Main main = new Main();
            Home homePanel = new Home();
            homePanel.setIconImage(main.imageIcon.getImage());
            homePanel.setTitle("Minesweeper");
            homePanel.setVisible(true);

    }
}

