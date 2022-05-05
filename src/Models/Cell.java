package Models;

import java.io.Serializable;

public class Cell implements Serializable {
    private boolean isRevealed;
    private boolean isMine;
    private int numMineAround;
    private boolean isFlagged;


    public Cell() {
        isRevealed = false;
        isMine = false;
        isFlagged = false;
    }

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