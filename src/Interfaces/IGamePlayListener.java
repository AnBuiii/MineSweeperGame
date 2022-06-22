package Interfaces;

import Models.Cell;
public interface IGamePlayListener {
    Cell[][] getListCell();

    void reveal(int x, int y);
    void flag(int x, int y);

    boolean isReviewMode();
}