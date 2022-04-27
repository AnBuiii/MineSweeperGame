package Interfaces;

public interface IHomeListener {

    void restart();
    boolean isGameFinish();
    void openGameMenu(int x, int y);
    void closeGameMenu();
    boolean isGameMenuOpen();
}
