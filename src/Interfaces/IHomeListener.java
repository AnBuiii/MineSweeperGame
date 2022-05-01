package Interfaces;

import Views.NewGamePanel;

public interface IHomeListener {
    void continueGame();
    void reDrawHome();
    boolean isGameFinish();
    NewGamePanel getNewGameMenu();
}
