package Models;

import java.awt.geom.Point2D;

public class GridPoint {
    public int x;
    public int y;

    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridPoint(GridPoint gridPoint) {
        if (gridPoint != null) {
            this.x = gridPoint.x;
            this.y = gridPoint.y;
        }
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public static GridPoint pointCenterOfTriangle(GridPoint a, GridPoint b, GridPoint c) {
        return new GridPoint((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
    }

    public static boolean pointInTriangle(Point2D p, GridPoint p1, GridPoint p2, GridPoint p3) {
        var alpha = ((p2.y - p3.y) * (p.getX() - p3.x) + (p3.x - p2.x) * (p.getY() - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        var beta = ((p3.y - p1.y) * (p.getX() - p3.x) + (p1.x - p3.x) * (p.getY() - p3.y)) / ((p2.y - p3.y) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.y - p3.y));
        var gamma = 1.0 - alpha - beta;

        return (alpha > 0 && beta > 0 && gamma > 0);
    }
    @Override
    public GridPoint clone() {
        return new GridPoint(this);
    }

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof GridPoint p)) return false;
        return p.x == x && p.y == y;
    }
}
