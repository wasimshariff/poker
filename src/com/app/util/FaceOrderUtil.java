package com.app.util;

public class FaceOrderUtil {

    public static int getOrder(String face) {
        int order = 0;
        switch (face) {
            case "2":
                order = 2;
                break;
            case "3":
                order = 3;
                break;
            case "4":
                order = 4;
                break;
            case "5":
                order = 5;
                break;
            case "6":
                order = 6;
                break;
            case "7":
                order = 7;
                break;
            case "8":
                order = 8;
                break;
            case "9":
                order = 9;
                break;
            case "T":
                order = 10;
                break;
            case "J":
                order = 11;
                break;
            case "Q":
                order = 12;
                break;
            case "K":
                order = 13;
                break;
            case "A":
                order = 14;
                break;
        }
        return order;
    }
}
