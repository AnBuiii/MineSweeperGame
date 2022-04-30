package Interfaces;

public interface IHomeListener {
    void continueGame();
    void reDrawHome();
    boolean isGameFinish();
    void openGameMenu(int x, int y);
    void closeGameMenu();
    boolean isGameMenuOpen();
}
