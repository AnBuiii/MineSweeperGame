package DesignPattern.GameTemplate;

import Models.Cell;

public class MineRectangleGrid extends MineGrid{
    public MineRectangleGrid(int numRows, int numColumns, int numMines) {
        super(numRows, numColumns, numMines);
    }

    @Override
    protected void initGrid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    @Override
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

    @Override
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
}
