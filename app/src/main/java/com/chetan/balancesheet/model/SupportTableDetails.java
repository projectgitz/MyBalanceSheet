package com.chetan.balancesheet.model;

import java.util.Date;

/**
 * Created by ckumo on 10/25/2016.
 */

public class SupportTableDetails {

    private Date date;
    private int _cost;
    private String _month;
    private String _year;

    public String getMonth() {
        return _month;
    }

    public void setMonth(String _month) {
        this._month = _month;
    }

    public String getYear() {
        return _year;
    }

    public void setYear(String _year) {
        this._year = _year;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCost() {
        return _cost;
    }

    public void setCost(int _cost) {
        this._cost = _cost;
    }
}
