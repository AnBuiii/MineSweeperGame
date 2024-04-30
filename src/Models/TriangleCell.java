package Models;

public class TriangleCell extends Cell {
    private final boolean isUp;

    public TriangleCell(boolean isUp) {
        super();
        this.isUp = isUp;
    }

    public boolean getIsUp() {
        return this.isUp;
    }
}
