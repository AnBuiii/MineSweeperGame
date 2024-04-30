package Controller;

import Views.MineGridPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static Views.custom.Theme.STATUS_PANEL_HEIGHT;

public class MineSweeperGame extends MineSweeperTemplate {
    public MineSweeperGame(int num_rows, int num_columns, int num_bombs, int gameMode) {
        super(num_rows, num_columns, num_bombs, gameMode);
    }

    public MineSweeperGame(MineSweeperTemplate old) {
        super(old);
    }

    @Override
    public void addMineGrid() {
        mineGridPanelTemplate = new MineGridPanel(num_rows, num_columns);
        mineGridPanelTemplate.setBounds(0, STATUS_PANEL_HEIGHT, getWidth(), getHeight() - STATUS_PANEL_HEIGHT);
        mineGridPanelTemplate.addListener(this);
        add(mineGridPanelTemplate);
    }
}
