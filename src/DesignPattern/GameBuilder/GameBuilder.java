package DesignPattern.GameBuilder;

import Views.MineGridPanel;
import Views.MineGridPanelTemplate;

import java.util.Locale;

public interface GameBuilder {
    void setColumn(int column);

    void setRow(int row);

    void setMines(int mines);

    void setGameMode(int gameMode);
}
