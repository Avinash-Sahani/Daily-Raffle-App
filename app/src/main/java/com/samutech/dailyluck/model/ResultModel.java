package com.samutech.dailyluck.model;

public class ResultModel {


    private String hash,key,lucky_number,name,price,status;

    public ResultModel(){}


    public ResultModel(String hash, String key, String lucky_number, String name, String price, String status) {
        this.hash = hash;
        this.key = key;
        this.lucky_number = lucky_number;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLucky_number() {
        return lucky_number;
    }

    public void setLucky_number(String lucky_number) {
        this.lucky_number = lucky_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
