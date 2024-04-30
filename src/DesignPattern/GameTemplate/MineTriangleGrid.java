package DesignPattern.GameTemplate;

import Models.TriangleCell;

public class MineTriangleGrid extends MineGrid {

    public MineTriangleGrid(int numRows, int numColumns, int numMines) {
        super(numRows, numColumns, numMines);
    }


    @Override
    public void initGrid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new TriangleCell((i + j) % 2 != 0);
            }
        }
    }

    @Override
    void setNumber() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                TriangleCell cell = (TriangleCell) cells[i][j];
                if (!cell.isMine()) continue;
                boolean isCellUp = cell.getIsUp();
                cells[i][j].setMine(true);

                if (j - 1 >= 0) { // left
                    if (i - 1 >= 0) { // left top
                        cells[i - 1][j - 1].addAMineAround();
                        if (!isCellUp && j - 2 >= 0) { //far left top
                            cells[i - 1][j - 2].addAMineAround();
                        }
                    }

                    if (i + 1 < this.num_rows) { // left bottom
                        cells[i + 1][j - 1].addAMineAround();
                        if (isCellUp && j - 2 >= 0) { // far left bottom
                            cells[i + 1][j - 2].addAMineAround();
                        }
                    }

                    cells[i][j - 1].addAMineAround(); // left middle

                    if (j - 2 >= 0) {
                        cells[i][j - 2].addAMineAround(); // far left middle
                    }
                }

                if (i - 1 >= 0) {
                    cells[i - 1][j].addAMineAround(); // top middle
                }
                if (i + 1 < this.num_rows) {
                    cells[i + 1][j].addAMineAround(); // bottom middle
                }

                if (j + 1 < this.num_columns) {
                    if (i - 1 >= 0) {
                        cells[i - 1][j + 1].addAMineAround(); // top right
                        if (!isCellUp && j + 2 < this.num_columns) { // far top right
                            cells[i - 1][j + 2].addAMineAround();
                        }
                    }
                    if (i + 1 < this.num_rows) {
                        cells[i + 1][j + 1].addAMineAround(); // bottom right
                        if (isCellUp && j + 2 < this.num_columns) { // far bottom right
                            cells[i + 1][j + 2].addAMineAround();
                        }
                    }
                    cells[i][j + 1].addAMineAround(); // middle right
                    if (j + 2 < this.num_columns) {
                        cells[i][j + 2].addAMineAround(); // far middle right
                    }
                }
            }
        }
    }

    @Override
    public void revealAround(int i, int j) {
        TriangleCell cell = (TriangleCell) cells[i][j];
        if (j - 1 >= 0) { // left
            if (i - 1 >= 0) { // left top
                this.reveal(i - 1, j - 1);
                if (!cell.getIsUp() && j - 2 >= 0) {
                    this.reveal(i - 1, j - 2);
                }
            }
            if (i + 1 < this.num_rows) { // left bottom
                this.reveal(i + 1, j - 1);
                if (cell.getIsUp() && j - 2 >= 0) { // far left bottom
                    this.reveal(i + 1, j - 2);
                }
            }
            this.reveal(i, j - 1); // left middle
            if (j - 2 >= 0) {
                this.reveal(i, j - 2); // far left middle
            }
        }

        if (i - 1 >= 0) {
            this.reveal(i - 1, j); // top middle
        }
        if (i + 1 < this.num_rows) {
            this.reveal(i + 1, j); // bottom middle
        }

        if (j + 1 < this.num_columns) {
            if (i - 1 >= 0) {
                this.reveal(i - 1, j + 1); // top right
                if (!cell.getIsUp() && j + 2 < this.num_columns) { // far top  right
                    this.reveal(i - 1, j + 2);
                }
            }
            if (i + 1 < this.num_rows) {
                this.reveal(i + 1, j + 1); // bottom right
                if (cell.getIsUp() && j + 2 < this.num_columns) { // far bottom right
                    this.reveal(i + 1, j + 2);
                }
            }
            this.reveal(i, j + 1); // middle right
            if (j + 2 < this.num_columns) {
                this.reveal(i, j + 2); // far middle right
            }
        }
    }
}
