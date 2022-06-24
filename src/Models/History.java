package Models;

import java.io.Serializable;

public class History implements Serializable {
    public int x;
    public int y;
    public int move; // 1: play, 2: hint, 3: flag
    public History(int x, int y, int move){
        this.x = x;
        this.y = y;
        this.move = move;
    }
}