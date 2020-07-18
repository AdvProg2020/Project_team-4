package Model;

import java.util.ArrayList;

public class Score {
    private static ArrayList<Score> allScores;
    private String account;
    private double score;
    private String product;

    public Score(String account, double score, String product) {
        this.account = account;
        this.score = score;
        this.product = product;
        allScores.add(this);
    }

}
