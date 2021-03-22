package com.samutech.dailyluck.model;

public class PriceModel {

String price,price_1,price_2;

public PriceModel()
{

    price=null;
    price_1=null;
    price_2=null;
}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_1() {
        return price_1;
    }

    public void setPrice_1(String price_1) {
        this.price_1 = price_1;
    }

    public String getPrice_2() {
        return price_2;
    }

    public void setPrice_2(String price_2) {
        this.price_2 = price_2;
    }
}
