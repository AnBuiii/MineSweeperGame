package DesignPattern.GameState;

import Controller.MineSweeperGame;
import Models.History;

public class ReviewState extends State {
    private int reviewStep;

    public ReviewState(MineSweeperGame game) {
        super(game);
        game.statusPanel.reviewMode();
        game.mineGrid.unRevealAllCells();
        game.mineGridPanel.updateGrid();
        game.mineGridPanel.mark(game.playHistory.get(reviewStep).x, game.playHistory.get(reviewStep).y);
    }

    @Override
    public void flag(int x, int y) {

    }

    @Override
    public void reveal(int x, int y) {
    }

    @Override
    public void reviewNext() {
        if (reviewStep == game.playHistory.size()) return;
        History history = game.playHistory.get(reviewStep);
        switch (history.move) {
            case 1:
                revealMove(history.x, history.y);
                break;
            case 2:
                break;
            case 3:
                flagMove(history.x, history.y);
                break;
        }
        reviewStep++;
        if (reviewStep != game.playHistory.size())
            game.mineGridPanel.mark(game.playHistory.get(reviewStep).x, game.playHistory.get(reviewStep).y);
    }

    @Override
    public void reviewPrevious() {
        if (reviewStep == 0) return;
        game.mineGrid.unRevealAllCells();
        game.mineGridPanel.updateGrid();
        reviewStep--;

        for (int i = 0; i < reviewStep; i++) {
            History history = game.playHistory.get(i);
            switch (history.move) {
                case 1:
                    revealMove(history.x, history.y);
                    break;
                case 2:
                    break;
                case 3:
                    flagMove(history.x, history.y);
                    break;
            }

        }
//        game.mineGridPanel.mark(game.playHistory.get(reviewStep).x, game.playHistory.get(reviewStep).y);
    }

    private void revealMove(int x, int y) {
        game.mineGrid.reveal(x, y);
        System.out.println(x + " " + y);
        game.mineGridPanel.updateGrid();
    }

    private void flagMove(int x, int y) {
        game.mineGrid.flag(x, y);
        game.mineGridPanel.updateGrid();
    }
}
