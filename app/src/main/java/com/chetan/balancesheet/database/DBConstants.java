package com.chetan.balancesheet.database;

/**
 * Created by 3164 on 12-12-2016.
 */

public class DBConstants {

    public static final String DATABASE_NAME = "balanaceSheet.db";
    public static final int DATABASE_VERSION = 1;

    //Tables
    public final static String TABLE_NAME_BALANCESHEET = "balance_sheet_table";
    public final static String TABLE_NAME_SUPPORT = "support_table";
    public final static String TABLE_NAME_MONTHLYVIEW = "monthly_view";

    //For BalanceSheet
    public final static String COLUMN_ID = "id";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public final static String COLUMN_PAYCHECKDATE = "payCheckDate";
    public final static String COLUMN_OPENING_BALANCE = "openingBalance";
    public final static String COLUMN_RATE = "rate";
    public final static String COLUMN_HOURS = "hours";
    public final static String COLUMN_CREDIT = "credit";
    public final static String COLUMN_DEBIT = "debit";
    public final static String COLUMN_ENDING_BALANCE = "endingBalance";

    //For Support
    public final static String COLUMN_SUPPORT_ID = "id";
    public final static String COLUMN_SUPPORT_MONTH = "month";
    public final static String COLUMN_SUPPORT_YEAR = "year";
    public final static String COLUMN_SUPPORT_AMOUNT = "amount";

    //Other Deduction
    public final static String COLUMN_OD_DESCRIPTION = "description";
    public final static String COLUMN_OD_AMOUNT = "amount";

    //Monthlyview
    public final static String COLUMN_MONTHVIEW_ID = "id";
    public final static String COLUMN_MONTHVIEW_MONTH = "month";
    public final static String COLUMN_MONTHVIEW_YEAR = "year";
    public final static String COLUMN_MONTHVIEW_OPENING_BALANCE = "openingBalance";
    public final static String COLUMN_MONTHVIEW_ENDING_BALANCE = "endingBalance";
    public final static String COLUMN_MONTHVIEW_HOURS = "hours";

}
