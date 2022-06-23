package Interfaces;

import Views.NewGamePanel;

public interface IHomeListener {

    void continueGame();
    void reDrawHome();
    boolean isGameFinish();
    void openSetting();
    void openStatistic();
    void openTutorial();

    NewGamePanel getNewGameMenu();
}
