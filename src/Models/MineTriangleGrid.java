package Models;

import java.util.Random;

public class MineTriangleGrid {
    private int num_rows ;
    private int num_columns;
    private int num_mines ;
    public boolean isPlayed;
    private Cell[][] cells;

    public MineTriangleGrid(int rows, int columns, int bombs) {
        num_rows = rows;
        num_columns = columns;
        num_mines = bombs;
        isPlayed = false;

        landMines();
    }
//    public MineGrid(MineGrid old){
//        num_rows = old.num_rows;
//        num_columns = old.num_columns;
//        num_mines = old.num_mines;
//        cells = old.cells;
//    }

    public void landMines(){

        cells = new Cell[num_rows][num_columns];

        // xác định ô tam giác úp hay đứng
        for(int i = 0; i < num_rows; i ++){
            for(int j = 0; j < num_columns; j ++){
                cells[i][j] = new Cell();
                cells[i][j].setIsUp(( i + j) % 2 != 0);
            }
        }


        int count = 0;
        int ranMine;

        while(count < this.num_mines){
            ranMine = genRan(num_columns * num_rows);
            int i = (int)(ranMine / num_columns);
            int j = ranMine % num_columns;
            if(ranMine < (this.num_rows * this.num_columns) && !cells[i][j].isMine()){
                cells[i][j].setMine(true);

                if(j - 1 >= 0){ // left
                    if(i - 1 >= 0){ // left top
                        cells[i - 1][j - 1].addAMineAround();
                        if(!cells[i][j].getIsUp() && j - 2 >= 0) { //far left top
                            cells[i - 1][j - 2].addAMineAround();
                        }
                    }

                if(i + 1 < this.num_rows){ // left bottom
                    cells[i + 1][j - 1].addAMineAround();
                    if(cells[i][j].getIsUp() && j - 2 >= 0){ // far left bottom
                        cells[i + 1][j - 2].addAMineAround();
                    }
                }

                cells[i][j - 1].addAMineAround(); // left middle

                    if(j - 2 >=0 ){
                        cells[i][j - 2].addAMineAround(); // far left middle
                    }
                }

                if(i - 1 >= 0){
                    cells[i - 1][j].addAMineAround(); // top middle
                }
                if(i + 1 < this.num_rows){
                    cells[i + 1][j].addAMineAround(); // bottom middle
                }

                if(j + 1 < this.num_columns){
                    if(i - 1 >= 0){
                        cells[i - 1][j + 1].addAMineAround(); // top right
                    if(!cells[i][j].getIsUp() && j + 2 < this.num_rows){ // far top right
                        cells[i - 1][j + 2].addAMineAround();
                    }
                    }
                    if(i + 1 < this.num_rows){
                        cells[i + 1][j + 1].addAMineAround(); // bottom right
                        if(cells[i][j].getIsUp() && j + 2 < this.num_columns){ // far bottom right
                            cells[i + 1][j + 2].addAMineAround();
                        }
                    }
                    cells[i][j + 1].addAMineAround(); // middle right
                    if(j + 2 < this.num_columns){
                        cells[i][j + 2].addAMineAround(); // far middle right
                    }
                }
                count++;
            }
        }
    }

    public void firstMove(int x, int y){
        if(cells[x][y].isMine()) cells[x][y].setMine(false);
        landMines();
        isPlayed = true;
    }

    private int genRan(int range) {
        Random rd = new Random();
        return rd.nextInt(range);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean reveal(int i, int j) {
        if (!cells[i][j].isRevealed() && !cells[i][j].isFlagged()) {
            if (cells[i][j].isMine()) {
                return false;
            }
            cells[i][j].setRevealed(true);
            if (cells[i][j].getNumMineAround() == 0) {
                if (j - 1 >= 0) { // left
                    if (i - 1 >= 0) { // left top
                        this.reveal(i - 1, j - 1);
                        if (!this.cells[i][j].getIsUp() && j - 2 >= 0) {
                            this.reveal(i - 1, j - 2);
                        }
                    }
                    if (i + 1 < this.num_rows) { // left bottom
                        this.reveal(i + 1, j - 1);
                        if (this.cells[i][j].getIsUp() && j - 2 >= 0) { // far left bottom
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
                        if (!this.cells[i][j].getIsUp() && j + 2 < this.num_columns) { // far top  right
                            this.reveal(i - 1, j + 2);
                        }
                    }
                    if (i + 1 < this.num_rows) {
                        this.reveal(i + 1, j + 1); // bottom right
                        if (this.cells[i][j].getIsUp() && j + 2 < this.num_columns) { // far bottom right
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
    public int getNum_mines(){
        return num_mines;
    }

    public int getNum_columns() {
        return num_columns;
    }

    public int getNum_rows() {
        return num_rows;
    }
}
