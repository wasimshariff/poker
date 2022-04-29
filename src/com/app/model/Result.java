package com.app.model;

public class Result {

    private Ranking rank;
    // TODO need to implement final Score incase of Tie between players
    private int finalScore;
    // TODO need to implement this to specify face card names ( i.e. Sam Two Pair Ace King )
    private String[] cardDesc;

    public Result(Ranking rank, String[] cardDesc) {
        this.rank = rank;
        this.cardDesc = cardDesc;
    }

    public Result(Ranking rank, String[] cardDesc, int finalScore) {
        this.rank = rank;
        this.cardDesc = cardDesc;
        this.finalScore = finalScore;
    }

    public Result(Ranking ranking) {
        this.rank = ranking;
    }

    public Ranking getRank() {
        return rank;
    }

    public void setRank(Ranking rank) {
        this.rank = rank;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public String[] getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String[] cardDesc) {
        this.cardDesc = cardDesc;
    }
}
