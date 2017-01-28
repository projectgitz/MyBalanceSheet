package com.chetan.balancesheet.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String decimalFormatter(Double value) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(value);
    }
}
