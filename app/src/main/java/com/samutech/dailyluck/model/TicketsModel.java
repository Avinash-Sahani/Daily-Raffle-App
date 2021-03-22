package com.samutech.dailyluck.model;

public class TicketsModel {


    private String luckynumber;

    public TicketsModel(){}


    public TicketsModel(String luckynumber) {
        this.luckynumber = luckynumber;
    }

    public String getLuckynumber() {
        return luckynumber;
    }

    public void setLuckynumber(String luckynumber) {
        this.luckynumber = luckynumber;
    }
}
