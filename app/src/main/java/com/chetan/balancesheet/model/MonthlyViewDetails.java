package com.chetan.balancesheet.model;

/**
 * Created by ckumo on 12/8/2016.
 */

public class MonthlyViewDetails {

    private String month;
    private String year;
    private double openingBalance; // first pay check opening balance will be this one from balance sheet table
    private double endingBalance; // second pay check ending balance will be this one from balanace sheet table
    private int hours; // hours will add from balance sheet table first pay hours + second pay hours

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(double endingBalance) {
        this.endingBalance = endingBalance;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }



}
