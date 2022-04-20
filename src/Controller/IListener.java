package Controller;

import Models.Cell;

public interface IListener {
    Cell[][] getListSquare();

    void reveal(int x, int y);
    void flag(int x, int y);
    void restart();
}