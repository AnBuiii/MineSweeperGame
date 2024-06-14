package DesignPattern.GameBuilder;

public interface GameBuilder {
    void setColumn(int column);

    void setRow(int row);

    void setMines(int mines);

    void setGameMode(int gameMode);
}
