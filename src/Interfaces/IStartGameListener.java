package Interfaces;

import Models.GameDifficulty;

public interface IStartGameListener {
    void startGame(GameDifficulty gameDifficulty);
    void startTriangleGame();
    void closeHomePanel();
}
