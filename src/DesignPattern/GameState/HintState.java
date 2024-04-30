package DesignPattern.GameState;

import Controller.MineSweeperTemplate;
import Models.History;

public class HintState extends State {
    public HintState(MineSweeperTemplate game) {
        super(game);
    }

    @Override
    public void flag(int x, int y) {

    }

    @Override
    public void reveal(int x, int y) {
        boolean success = game.mineGrid.revealHint(x, y);
        if (success) {
            game.statusPanel.setNumFlag(game.mineGrid.getNumFlag());
            game.playHistory.add(new History(x, y, 3));
            game.mineGridPanelTemplate.updateGrid();
            if (game.isVictory()) {
                game.updateState(new FinishState(game));
            }
        }
    }

    @Override
    public void reviewNext() {

    }

    @Override
    public void reviewPrevious() {

    }
}
