package Interfaces;

import DesignPattern.GameState.State;
import Models.Cell;
public interface IGamePlayListener {
    Cell[][] getListCell();

    void reveal(int x, int y);
    void flag(int x, int y);

    boolean isReviewMode();

    boolean isHintMode();

    void revealHint(int x, int y);

    State getGameState();
}