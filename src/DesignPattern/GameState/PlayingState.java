package DesignPattern.GameState;

import Controller.MineSweeperGame;
import Models.History;
import Models.Reveal;

public class PlayingState extends State {
    public PlayingState(MineSweeperGame game) {
        super(game);
    }

    @Override
    public void flag(int x, int y) {
        boolean success = game.mineGrid.flag(x, y);
        if (success) {
            game.statusPanel.setNumFlag(game.mineGrid.getNumFlag());
            game.playHistory.add(new History(x, y, 3));
            game.mineGridPanel.updateGrid();
        }
    }

    @Override
    public void reveal(int x, int y) {
        Reveal check = game.mineGrid.reveal(x, y);
        switch (check) {
            case SUCCESS:
                game.playHistory.add(new History(x, y, 1));
                game.mineGridPanel.updateGrid();
                if (game.isVictory()) {
                    game.updateState(new FinishState(game));
                }
                break;
            case BOMB:
                game.mineGrid.revealAllCell();
                game.updateState(new FinishState(game));
                game.playHistory.add(new History(x, y, 1));
                break;
        }
    }

    @Override
    public void reviewNext() {

    }

    @Override
    public void reviewPrevious() {

    }
}
