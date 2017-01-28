package com.chetan.balancesheet.model;

import android.util.Log;

import java.util.Date;

/**
 * Created by ckumo on 10/28/2016.
 */

public class BalanceSheetDetails {

    private int _id;
    private String _startDate;
    private String _endDate;
    private String _payCheckDate;
    private float _openingBalance;
    private float _rate;
    private int _hours;
    private float _credit;
    private float _debit;
    private float _endingBalance;

    public BalanceSheetDetails() {
    }

    //constructor without Id
    public BalanceSheetDetails(String startDate, String endDate, String payCheckDate,
                               float openingBalance, float rate,
                               int hours, float credit, float debit, float endingBalance) {
        this._startDate = startDate;
        this._endDate = endDate;
        this._payCheckDate = payCheckDate;
        this._openingBalance = openingBalance;
        this._rate = rate;
        this._hours = hours;
        this._credit = credit;
        this._debit = debit;
        this._endingBalance = endingBalance;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getStartDate() {
        return _startDate;
    }

    public void setStartDate(String _period) {
        this._startDate = _period;
    }

    public String getPayCheckDate() {
        return _payCheckDate;
    }

    public void setPayCheckDate(String _payCheckDate) {
        this._payCheckDate = _payCheckDate;
    }

    public float getOpeningBalance() {
        return _openingBalance;
    }

    public void setOpeningBalance(float _openingBalance) {
        this._openingBalance = _openingBalance;
    }

    public float getRate() {
        return _rate;
    }

    public void setRate(float _rate) {
        this._rate = _rate;
    }

    public int getHours() {
        return _hours;
    }

    public void setHours(int _hours) {
        this._hours = _hours;
    }

    public float getCredit() {
        return _credit;
    }

    public void setCredit(float _credit) {
        this._credit = _credit;
    }

    public float getDebit() {
        return _debit;
    }

    public void setDebit(float _debit) {
        this._debit = _debit;
    }

    public float getEndingBalance() {
        return _endingBalance;
    }

    public void setEndingBalance(float _endingBalance) {
        this._endingBalance = _endingBalance;
    }

    public String getEndDate() {
        return _endDate;
    }

    public void setEndDate(String _endDate) {
        this._endDate = _endDate;
    }
}
