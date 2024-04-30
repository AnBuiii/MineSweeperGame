package Interfaces;

import DesignPattern.GameState.State;

public interface IStatusPanelListener {
    void back();

    // return isHint
    void setHint();

    void reGame();

    State getGameState();
}
