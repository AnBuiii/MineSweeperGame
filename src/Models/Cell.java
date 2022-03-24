package Models;

public class Cell {
    private boolean isBomb;
    private boolean isFlagged;
    private boolean isRevealed;

    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }
}
