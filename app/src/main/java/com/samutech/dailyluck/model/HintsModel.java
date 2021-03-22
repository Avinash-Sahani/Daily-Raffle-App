package com.samutech.dailyluck.model;

public class HintsModel {


    private String draw_name,hint;

    public HintsModel(){}


    public HintsModel(String draw_name, String hint) {
        this.draw_name = draw_name;
        this.hint = hint;
    }


    public String getDraw_name() {
        return draw_name;
    }

    public void setDraw_name(String draw_name) {
        this.draw_name = draw_name;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
