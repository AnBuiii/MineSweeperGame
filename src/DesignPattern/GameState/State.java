package DesignPattern.GameState;

import Controller.MineSweeperGame;

public abstract class State {
    MineSweeperGame game;

    State(MineSweeperGame game) {
        this.game = game;
    }

    public abstract void flag(int x, int y);

    public abstract void reveal(int x, int y);

    public abstract void reviewNext();

    public abstract void reviewPrevious();
}

