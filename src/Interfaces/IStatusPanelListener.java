package Interfaces;

import DesignPattern.GameState.State;

public interface IStatusPanelListener {
    void back();
    void hint();
    void restart();

    void reviewNext();

    void reviewPrevious();

    void reGame();

    State getGameState();
}
