package DesignPattern.GameTemplate;

import Models.Cell;
import Models.Reveal;

import java.util.Random;

public class MineGrid {
    protected final int num_rows;
    protected final int num_columns;
    protected final int num_mines;
    protected final Cell[][] cells;
    private final Random rand;
    private int numCellPlayed;
    protected int numFlag;

    public MineGrid(int numRows, int numColumns, int numMines) {
        this.num_rows = numRows;
        this.num_columns = numColumns;
        this.num_mines = numMines;
        this.cells = new Cell[numRows][numColumns];
        this.numCellPlayed = 0;
        this.numFlag = 0;
        this.rand = new Random();

        initGrid();
        landMine();
        setNumber();
    }

    protected void initGrid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void landMine() {
        for (int i = 0; i < num_mines; i++) {
            int x = genRan(num_rows);
            int y = genRan(num_columns);
            while (cells[x][y].isMine()) {
                x = genRan(num_rows);
                y = genRan(num_columns);
            }
            cells[x][y].setMine(true);
        }
    }

    void setNumber() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                int count = 0;
                for (int m = -1; m <= 1; m++) {
                    if (i + m < 0) {
                        m++;
                    }
                    if (i + m > num_rows - 1) {
                        break;
                    }
                    for (int n = -1; n <= 1; n++) {
                        if (j + n < 0) {
                            n++;
                        }
                        if (j + n > num_columns - 1) {
                            break;
                        }
                        if (!(m == 0 && n == 0) && cells[i + m][j + n].isMine()) {
                            count++;
                        }
                    }
                }
                cells[i][j].setNumMineAround(count);
            }
        }

    }

    public int getNumFlag() {
        return numFlag;
    }

    public boolean flag(int x, int y) {
        Cell cell = cells[x][y];
        if (cell.isRevealed()) return false;
        if (cells[x][y].isFlagged()) {
            cells[x][y].setFlagged(false);
            numFlag--;
        } else {
            if (numFlag == num_mines) return false;
            cells[x][y].setFlagged(true);
            numFlag++;
        }
        return true;
    }

    public void revealAllCell() {
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                cell.setRevealed(true);
            }
        }
    }

    public void revealAround(int x, int y) {
        for (int m = -1; m <= 1; m++) {
            if (x + m < 0) {
                m++;
            }
            if (x + m > num_rows - 1) {
                break;
            }
            for (int n = -1; n <= 1; n++) {
                if (y + n < 0) {
                    n++;
                }
                if (y + n > num_columns - 1) {
                    break;
                }
                reveal(x + m, y + n);
            }
        }
    }

    private void firstMove(int x, int y) {
        cells[x][y].setMine(false);
        int xx = genRan(num_rows);
        int yy = genRan(num_columns);
        while (cells[xx][yy].isMine()) {
            xx = genRan(num_rows);
            yy = genRan(num_columns);
        }
        cells[xx][yy].setMine(true);
        setNumber();
    }

    // isBomb -> false
    public Reveal reveal(int x, int y) {
        Cell cell = cells[x][y];
        if (numCellPlayed == 0 && cell.isMine()) {
            firstMove(x, y);
            return Reveal.SUCCESS;
        }

        if (cell.isRevealed() || cell.isFlagged()) {
            return Reveal.NOT_SUCCESS;
        }
        cell.setRevealed(true);
        if (cell.isMine()) {
            return Reveal.BOMB;
        }
        numCellPlayed++;
        if (cells[x][y].getNumMineAround() == 0) {
            revealAround(x, y);
        }
        return Reveal.SUCCESS;
    }

    public boolean isVictory() {
        return numCellPlayed == num_columns * num_rows - num_mines;
    }

    public int genRan(int range) {
        return rand.nextInt(range);
    }


    public void unRevealAllCells() {
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                cell[j].setRevealed(false);
                cell[j].setFlagged(false);
            }
        }
        numFlag = 0;
    }

    public boolean revealHint(int x, int y) {
        Cell cell = cells[x][y];
        if (cell.isRevealed()) {
            return false;
        }
        cell.setRevealed(true);
        if (!cells[x][y].isMine()) numCellPlayed++;
        return true;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean isCellRevealed(int x, int y) {
        return cells[x][y].isRevealed();
    }

    public boolean isCellFlagged(int x, int y) {
        return cells[x][y].isFlagged();
    }
}

