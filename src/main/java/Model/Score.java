package Model;

import java.util.ArrayList;

public class Score {
    private static ArrayList<Score> allScores;
    private Account account;
    private double score;
    private Product product;

    public Score(Account account, double score, Product product) {
        this.account = account;
        this.score = score;
        this.product = product;
        allScores.add(this);
    }

}
