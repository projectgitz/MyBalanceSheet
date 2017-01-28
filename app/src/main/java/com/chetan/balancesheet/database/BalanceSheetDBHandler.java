package com.chetan.balancesheet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chetan.balancesheet.base.BalanceSheetApplication;
import com.chetan.balancesheet.model.BalanceSheetDetails;
import com.chetan.balancesheet.model.SupportTableDetails;

import java.text.SimpleDateFormat;

/**
 * Created by ckumo on 10/26/2016.
 */
public class BalanceSheetDBHandler extends SQLiteOpenHelper {

    private static BalanceSheetDBHandler instance;
    private static String DATE_FORMAT = "yyyy-MM-dd";

    private String TABLE_NAME[] = {
            DBConstants.TABLE_NAME_BALANCESHEET,
            DBConstants.TABLE_NAME_SUPPORT,
            DBConstants.TABLE_NAME_MONTHLYVIEW
    };
    private SimpleDateFormat dateFormat;

    private BalanceSheetDBHandler(Context context) {
        super(context, DBConstants.DATABASE_NAME, null,
                DBConstants.DATABASE_VERSION);
    }

    public static BalanceSheetDBHandler getInstance() {
        if (instance == null) {
            instance = new BalanceSheetDBHandler(BalanceSheetApplication.getInstance());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Log.d("DATABASE", "BalanceSheetDBHandler onCreate() is Called....");
        createBalanceSheetTable(db);
        createSupportTable(db);
//        createMonthlyViewTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String tables : TABLE_NAME) {
            //Drop Older table if existed.
            db.execSQL("DROP TABLE IF EXISTS" + tables);
        }
        //Create tables again
        onCreate(db);
    }

    //Creating BalanceSheet Table
    private void createBalanceSheetTable(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + DBConstants.TABLE_NAME_BALANCESHEET + "(" +
                DBConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBConstants.COLUMN_START_DATE + " DATE," +
                DBConstants.COLUMN_END_DATE + " DATE," +
                DBConstants.COLUMN_PAYCHECKDATE + " DATE," +
                DBConstants.COLUMN_OPENING_BALANCE + " REAL," +
                DBConstants.COLUMN_RATE + " REAL," +
                DBConstants.COLUMN_HOURS + " REAL," +
                DBConstants.COLUMN_CREDIT + " REAL," +
                DBConstants.COLUMN_DEBIT + " REAL," +
                DBConstants.COLUMN_ENDING_BALANCE + " REAL" + ")";
        db.execSQL(query);
        Log.d("DATABASE", "CreateBalanceSheetTable is Called....");
    }

    //Creating Support Table
    private void createSupportTable(SQLiteDatabase db) {
        String query = " CREATE TABLE IF NOT EXISTS " + DBConstants.TABLE_NAME_SUPPORT + "(" +
                DBConstants.COLUMN_SUPPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBConstants.COLUMN_SUPPORT_MONTH + " INTEGER," +
                DBConstants.COLUMN_SUPPORT_YEAR + " INTEGER," +
                DBConstants.COLUMN_SUPPORT_AMOUNT + " INTEGER" + ")";
        db.execSQL(query);
        Log.d("DATABASE", "CreateSupportTable is Called....");
    }

    //Creating MonthlyView Table
//    private void createMonthlyViewTable(SQLiteDatabase db) {
//        String query = "CREATE TABLE IF NOT EXISTS " + DBConstants.TABLE_NAME_MONTHLYVIEW + "(" +
//                DBConstants.COLUMN_MONTHVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                DBConstants.COLUMN_MONTHVIEW_MONTH + " TEXT," +
//                DBConstants.COLUMN_MONTHVIEW_YEAR + " INTEGER," +
//                DBConstants.COLUMN_MONTHVIEW_OPENING_BALANCE + " REAL," +
//                DBConstants.COLUMN_MONTHVIEW_ENDING_BALANCE + " REAL," +
//                DBConstants.COLUMN_MONTHVIEW_HOURS + " INTEGER" + ")";
//        db.execSQL(query);
//    }

    public void insertPayCheck(BalanceSheetDetails balanceSheetDetails) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(DBConstants.COLUMN_START_DATE, dateFormat.format(balanceSheetDetails.getStartDate()));
//        values.put(DBConstants.COLUMN_END_DATE, dateFormat.format(balanceSheetDetails.getEndDate()));

        values.put(DBConstants.COLUMN_START_DATE, balanceSheetDetails.getStartDate());
        values.put(DBConstants.COLUMN_END_DATE, balanceSheetDetails.getEndDate());
        values.put(DBConstants.COLUMN_PAYCHECKDATE, balanceSheetDetails.getPayCheckDate());
        values.put(DBConstants.COLUMN_OPENING_BALANCE, balanceSheetDetails.getOpeningBalance());
        values.put(DBConstants.COLUMN_RATE, balanceSheetDetails.getRate());
        values.put(DBConstants.COLUMN_HOURS, balanceSheetDetails.getHours());
        values.put(DBConstants.COLUMN_CREDIT, balanceSheetDetails.getCredit());
        values.put(DBConstants.COLUMN_DEBIT, balanceSheetDetails.getDebit());
        values.put(DBConstants.COLUMN_ENDING_BALANCE, balanceSheetDetails.getEndingBalance());
        database.insert(DBConstants.TABLE_NAME_BALANCESHEET, null, values);
        database.close();
    }

    public double getOpeningBalance() {
        double openingBalance = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(DBConstants.TABLE_NAME_BALANCESHEET, null, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            openingBalance = cursor.getDouble(cursor.getColumnIndex(DBConstants.COLUMN_ENDING_BALANCE));
        }
        cursor.close();
        return openingBalance;
    }

    public void insertSupportAmount(SupportTableDetails supportTableDetails) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.COLUMN_SUPPORT_MONTH, supportTableDetails.getMonth());
        values.put(DBConstants.COLUMN_SUPPORT_YEAR, supportTableDetails.getYear());
        values.put(DBConstants.COLUMN_SUPPORT_AMOUNT, supportTableDetails.getCost());
        database.insert(DBConstants.TABLE_NAME_SUPPORT, null, values);

        Log.d("Database", "after inserting cost in support..." + supportTableDetails.getCost());
        database.close();
    }

}
