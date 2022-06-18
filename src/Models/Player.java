package Models;

import java.io.Serializable;

public class Player implements Serializable {

    private static final int  CUSTOM = 0;
    private static final int  BEGINNER = 1;
    private static final int  INTERMEDIATE = 2;
    private static final int  EXPERT = 3;
    private static final int  TRIANGLE = 4;

    public int[] totalGames ;
    public int[] totalBomb ;
    public int[] totalTime ;
    public int[]shortestFinishTime ;
    public float[] performance;
    public int[] totalVictoryGame;

    public Player(){
        totalGames = new int[5];
        totalBomb = new int[5];
        totalTime = new int[5];
        shortestFinishTime = new int[5];
        performance = new float[5];
        totalVictoryGame = new int[5];
    }

    public Player(Player obj) {
        this.totalGames = obj.totalGames;
        this.totalBomb = obj.totalBomb;
        this.totalTime = obj.totalTime;
        this.shortestFinishTime = obj.shortestFinishTime;
        this.performance = obj.performance;
        this.totalVictoryGame = obj.totalVictoryGame;
    }
}
