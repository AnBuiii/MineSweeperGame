package DesignPattern.GameState;

import Controller.MineSweeperGame;
import Controller.MineSweeperTemplate;

public abstract class State {
    MineSweeperTemplate game;

    State(MineSweeperTemplate game) {
        this.game = game;
    }

    public abstract void flag(int x, int y);

    public abstract void reveal(int x, int y);

    public abstract void reviewNext();

    public abstract void reviewPrevious();
}

