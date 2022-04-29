package com.app.model;

import com.app.util.FaceOrderUtil;

public class Card {

    private String suit;

    private String face;

    // Int value for the card (i.e J is 11 , Q is 12 ... )
    private int order;

    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
        this.order = FaceOrderUtil.getOrder(face);
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
