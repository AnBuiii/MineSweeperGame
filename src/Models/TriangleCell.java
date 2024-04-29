package Models;

public class TriangleCell extends Cell {
    private boolean isUp;

    TriangleCell(boolean isUp) {
        super();
        this.isUp = isUp;
    }

    public boolean getIsUp() {
        return this.isUp;
    }
}
