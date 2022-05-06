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
        setCellNumber();
    }
//    public MineGrid(MineGrid old){
//        num_rows = old.num_rows;
//        num_columns = old.num_columns;
//        num_mines = old.num_mines;
//        cells = old.cells;
//    }

    public void landMines(){

        cells = new Cell[num_rows][num_columns];
        int count = 0;
        int ranMine;

        while(count < this.num_mines){
            ranMine = genRan(num_columns * num_rows);
            int i = (int)(ranMine / num_columns);
            int j = ranMine % num_columns;
            if(ranMine < (this.num_rows * this.num_columns) && !cells[i][j].isMine()){
                cells[i][j].setMine(true);
                if(j - 1 >= 0){
                    if(i - 1 >= 0){
                        cells[i][j].addAMineAround();
                    }
                }
            }
        }
    }

    public void setCellNumber(){

    }
    public void firstMove(int x, int y){

    }

    private int genRan(int range) {
        Random rd = new Random();
        return rd.nextInt(range);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean reveal(int x, int y) {
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
