package com.samutech.dailyluck.model;

public class WinnersModel {


    private String uid, draw,price;


    public WinnersModel
            (){}

    public WinnersModel(String uid, String draw, String price) {
        this.uid = uid;
        this.draw = draw;
        this.price = price;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getPrize() {
        return price;
    }

    public void setPrize(String price) {
        this.price = price;
    }
}