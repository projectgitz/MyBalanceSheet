package com.chethan.balancesheet.model;

import java.util.Date;

/**
 * Created by ckumo on 10/25/2016.
 */

public class SupportTableDetails {

    private String date;
    private int _cost;

    //Default Constructor
    public SupportTableDetails() {

    }

    public SupportTableDetails(String date, int _cost) {
        this.date = date;
        this._cost = _cost;
    }

   public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCost() {
        return _cost;
    }

    public void setCost(int _cost) {
        this._cost = _cost;
    }
}
