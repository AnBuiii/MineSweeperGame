package DesignPattern.GameState;

import Controller.MineSweeperGame;
import Controller.MineSweeperTemplate;

public class FinishState extends State {
    FinishState(MineSweeperTemplate game) {
        super(game);
        game.finishingGame();
    }

    @Override
    public void flag(int x, int y) {
        game.openFinishGamePanel();
    }

    @Override
    public void reveal(int x, int y) {
        game.openFinishGamePanel();
    }

    @Override
    public void reviewNext() {

    }

    @Override
    public void reviewPrevious() {

    }
}
