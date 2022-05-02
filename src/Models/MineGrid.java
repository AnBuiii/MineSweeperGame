package Models;

import java.util.Random;

public class MineGrid {
    public static int NUM_ROWS = 8;
    public static int NUM_COLUMNS = 10;
    public static int NUM_MINES = 20;
    public boolean isPlayed;
    private final Cell[][] cells;

    public MineGrid(int rows, int columns, int bombs) {
        NUM_ROWS = rows;
        NUM_COLUMNS = columns;
        NUM_MINES = bombs;
        isPlayed = false;

        cells = new Cell[NUM_ROWS][NUM_COLUMNS];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < NUM_MINES; i++) {
            int x = genRan(NUM_ROWS);
            int y = genRan(NUM_COLUMNS);
            while (cells[x][y].isMine()) {
                x = genRan(NUM_ROWS);
                y = genRan(NUM_COLUMNS);
            }
            cells[x][y].setMine(true);
        }
        setCellNumber();

    }
    public void setCellNumber(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                int count = 0;
                for (int m = -1; m <= 1; m++) {
                    if (i + m < 0) { m++; }
                    if (i + m > NUM_ROWS - 1) { break; }
                    for (int n = -1; n <= 1; n++) {
                        if (j + n < 0) { n++; }
                        if (j + n > NUM_COLUMNS - 1) { break; }
                        if (!(m == 0 && n == 0) && cells[i + m][j + n].isMine()) {
                            count++;
                        }
                    }
                }
                cells[i][j].setNumMineAround(count);
            }
        }
    }
    public void firstMove(int x, int y){
        if(cells[x][y].isMine()) cells[x][y].setMine(false);
        int xx = genRan(NUM_ROWS);
        int yy = genRan(NUM_COLUMNS);
        while (cells[xx][yy].isMine()) {
            xx = genRan(NUM_ROWS);
            yy = genRan(NUM_COLUMNS);
        }
        cells[xx][yy].setMine(true);
        setCellNumber();
        isPlayed = true;
    }

    private int genRan(int range) {
        Random rd = new Random();
        return rd.nextInt(range);
    }

    public Cell[][] getListCell() {
        return cells;
    }

    public boolean reveal(int x, int y) {
        if (!cells[x][y].isRevealed() && !cells[x][y].isFlagged()) {
            cells[x][y].setRevealed(true);
            if (cells[x][y].isMine()) {
                return false;
            }
            if (cells[x][y].getNumMineAround() == 0) {
                for (int m = -1; m <= 1; m++) {
                    if (x + m < 0) { m++; }
                    if (x + m > NUM_ROWS - 1) { break; }
                    for (int n = -1; n <= 1; n++) {
                        if (y + n < 0) { n++; }
                        if (y + n > NUM_COLUMNS - 1) { break; }
                        reveal(x + m, y + n);
                    }
                }
            }
        }
        return true;
    }

    public void flag(int x, int y) {
        if (!cells[x][y].isRevealed()) {
            cells[x][y].setFlagged(!cells[x][y].isFlagged());
        }
    }

    public void revealAllCell() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].setRevealed(true);
            }
        }
    }
}