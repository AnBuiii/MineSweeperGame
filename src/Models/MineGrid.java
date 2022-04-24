package Models;

import java.util.Random;

public class MineGrid {
    public static final int NUM_ROWS = 8;
    public static final int NUM_COLUMNS = 10;
    public static final int NUM_MINES = 10;

    private Cell[][] cell;

    public MineGrid() {
        cell = new Cell[NUM_ROWS][NUM_COLUMNS];
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j] = new Cell();
            }
        }
        for (int i = 0; i < NUM_MINES; i++) {
            int x = genRan(NUM_ROWS);
            int y = genRan(NUM_COLUMNS);
            while (cell[x][y].isMine()) {
                x = genRan(NUM_ROWS);
                y = genRan(NUM_COLUMNS);
            }
            cell[x][y].setMine(true);
        }
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                int count = 0;
                for (int m = -1; m <= 1; m++) {
                    if (i + m < 0) { m++; }
                    if (i + m > NUM_ROWS - 1) { break; }
                    for (int n = -1; n <= 1; n++) {
                        if (j + n < 0) { n++; }
                        if (j + n > NUM_COLUMNS - 1) { break; }
                        if (!(m == 0 && n == 0) && cell[i + m][j + n].isMine()) {
                            count++;
                        }
                    }
                }
                cell[i][j].setNumMineAround(count);
            }
        }
    }

    private int genRan(int range) {
        Random rd = new Random();
        return rd.nextInt(range);
    }

    public Cell[][] getListCell() {
        return cell;
    }

    public boolean reveal(int x, int y) {
        if (!cell[x][y].isRevealed()) {
            cell[x][y].setRevealed(true);
            if (cell[x][y].isMine()) {
                return false;
            }
            if (cell[x][y].getNumMineAround() == 0) {
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
        if (!cell[x][y].isRevealed()) {
            if (!cell[x][y].isFlagged()) {
                cell[x][y].setFlagged(true);
            } else {
                cell[x][y].setFlagged(false);
            }
        }
    }

    public void revealAllCell() {
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cell[i][j].setRevealed(true);
            }
        }
    }
}