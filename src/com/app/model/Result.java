package com.app.model;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private Ranking rank;

    private List<String> cardDesc;


    public Result(Ranking rank, List<String> cardDesc) {
        this.rank = rank;
        this.cardDesc = cardDesc;
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

    public List<String> getCardDesc() {
        if (cardDesc == null) {
            this.cardDesc = new ArrayList<>();
        }
        return  this.cardDesc;
    }

    public void setCardDesc(List<String> cardDesc) {
        this.cardDesc = cardDesc;
    }
}
