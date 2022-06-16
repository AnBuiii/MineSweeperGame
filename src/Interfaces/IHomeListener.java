package Interfaces;

import Views.NewGamePanel;

public interface IHomeListener {
    void continueGame();
    void reDrawHome();
    boolean isGameFinish();

    void startTriangleGame();
    NewGamePanel getNewGameMenu();
}
