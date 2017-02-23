package com.chethan.balancesheet.model;

/**
 * Created by Santhosh on 2/23/2017.
 */

public class OtherDeductionDetails {

    private int _cost;
    private String date;
    private String description;

    //Default Constructor
    public OtherDeductionDetails() {
    }

    public OtherDeductionDetails(String date, int _cost, String description) {
        this.date = date;
        this._cost = _cost;
        this.description=description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
