package Interfaces;

import DesignPattern.GameState.State;
import Models.Cell;
public interface IGamePlayListener {
    Cell[][] getListCell();

    boolean isReviewMode();

    State getGameState();
}