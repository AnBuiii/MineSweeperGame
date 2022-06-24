package Models;

import java.io.Serializable;

public class Cell implements Serializable {
    private boolean isRevealed;
    private boolean isMine;
    private int numMineAround;
    public boolean isFlagged;
    private boolean isUp; // xác định ô tam giác là đứng hay úp


    public Cell() {
        isRevealed = false;
        isMine = false;
        isFlagged = false;
        this.numMineAround = 0;
    }

    public void setIsUp(boolean isUp){
        this.isUp = isUp;
    }
    public boolean getIsUp(){
        return this.isUp;
    }
    public void addAMineAround() { this.numMineAround ++;}
    public boolean isRevealed() {
        return isRevealed;
    }
    public void setRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed;
    }
    public boolean isMine() {
        return isMine;
    }
    public void setMine(boolean mine) {
        this.isMine = mine;
    }
    public int getNumMineAround() {
        return numMineAround;
    }
    public void setNumMineAround(int numMineAround) {
        this.numMineAround = numMineAround;
    }
    public boolean isFlagged() {
        return isFlagged;
    }
    public void setFlagged(boolean isTarget) {
        this.isFlagged = isTarget;
    }


}