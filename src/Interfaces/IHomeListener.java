package Interfaces;

import Views.NewGamePanel;

public interface IHomeListener {
    void continueGame();
    void reDrawHome();
    boolean isGameFinish();
    void openStatistic();
    void openTutorial();

    NewGamePanel getNewGameMenu();
}
