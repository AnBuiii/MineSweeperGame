package Views.custom;

import java.awt.*;

public class Theme {
    public final static String TITLE = "MINESWEEPER";
    public final static String BOMB = "\uD83D\uDCA3";
    public final static String FLAG = "\uD83D\uDEA9";
    public final static String BIN = "\uD83D\uDDD1";
    public final static String BACK = "\u2190";
    public final static String CLOCK = "\u23F1";
    public final static String HINT = "\uD83D\uDCA1";
    public final static Color FOREGROUND = new Color(239,235,232);
    public final static Color BACKGROUND = new Color(104,159,57);

  //  public final static Font FONT = new Font("Arial", Font.PLAIN, 20); // cái font Arial vẽ cờ bị lỗi

    public final static Font FONT = new Font("VNI", Font.PLAIN, 20);
    public final static int CELL_SIZE = 40;
    public final static int STATUS_PANEL_HEIGHT = 60;
}
