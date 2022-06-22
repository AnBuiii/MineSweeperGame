package Models;

import java.io.Serializable;
import java.util.Random;

public class MineGrid implements Serializable {
    private int num_rows ;
    private int num_columns;
    public static int num_mines ;
    public int numCellPlayed;
    private final Cell[][] cells;

    public MineGrid(int rows, int columns, int bombs) {
        num_rows = rows;
        num_columns = columns;
        num_mines = bombs;
        numCellPlayed = 0;

        cells = new Cell[num_rows][num_columns];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < num_mines; i++) {
            int x = genRan(num_rows);
            int y = genRan(num_columns);
            while (cells[x][y].isMine()) {
                x = genRan(num_rows);
                y = genRan(num_columns);
            }
            cells[x][y].setMine(true);
        }

        setCellNumber();
    }
    public MineGrid(MineGrid old){
        num_rows = old.num_rows;
        num_columns = old.num_columns;
        num_mines = old.num_mines;
        cells = old.cells;
    }
    public void setCellNumber(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                int count = 0;
                for (int m = -1; m <= 1; m++) {
                    if (i + m < 0) { m++; }
                    if (i + m > num_rows - 1) { break; }
                    for (int n = -1; n <= 1; n++) {
                        if (j + n < 0) { n++; }
                        if (j + n > num_columns - 1) { break; }
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
        if(cells[x][y].isMine()) {
            cells[x][y].setMine(false);
            int xx = genRan(num_rows);
            int yy = genRan(num_columns);
            while (cells[xx][yy].isMine()) {
                xx = genRan(num_rows);
                yy = genRan(num_columns);
            }
            cells[xx][yy].setMine(true);
            setCellNumber();
        }
    }

    private int genRan(int range) {
        Random rd = new Random();
        return rd.nextInt(range);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean reveal(int x, int y) {
        if (!cells[x][y].isRevealed() && !cells[x][y].isFlagged()) {
            cells[x][y].setRevealed(true);
            if (cells[x][y].isMine()) {
                return false;
            }
            numCellPlayed++;
            if (cells[x][y].getNumMineAround() == 0) {
                for (int m = -1; m <= 1; m++) {
                    if (x + m < 0) { m++; }
                    if (x + m > num_rows - 1) { break; }
                    for (int n = -1; n <= 1; n++) {
                        if (y + n < 0) { n++; }
                        if (y + n > num_columns - 1) { break; }
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

    public boolean isVictory(){
        if(numCellPlayed == num_columns*num_rows - num_mines) return true;
        return false;
    }

    public boolean isCellRevealed(int x, int y){
        return cells[x][y].isRevealed();
    }
    public boolean isCellFlagged(int x, int y){
        return cells[x][y].isFlagged();
    }

    public int getNum_mines(){
        return num_mines;
    }

    public int getNum_columns() {
        return num_columns;
    }

    public int getNum_rows() {
        return num_rows;
    }


    public void unRevealAllCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].setRevealed(false);
            }
        }
    }
}