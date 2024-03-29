package com.chethan.balancesheet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.chethan.balancesheet.base.BalanceSheetApplication;
import com.chethan.balancesheet.model.BalanceSheetDetails;
import com.chethan.balancesheet.model.OtherDeductionDetails;
import com.chethan.balancesheet.model.SupportTableDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckumo on 10/26/2016.
 */
public class BalanceSheetDBHandler extends SQLiteOpenHelper {

    private static BalanceSheetDBHandler instance;
    private static String DATE_FORMAT = "yyyy-MM-dd";

    private String TABLE_NAME[] = {
            DBConstants.TABLE_NAME_BALANCESHEET,
            DBConstants.TABLE_NAME_SUPPORT,
            DBConstants.TABLE_NAME_OTHER_DEDUCTION,
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
        createOtherDeductionTable(db);
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
                DBConstants.COLUMN_SUPPORT_MONTH_YEAR + " DATE UNIQUE," +
                DBConstants.COLUMN_SUPPORT_AMOUNT + " INTEGER" + ")";
        db.execSQL(query);
        Log.d("DATABASE", "CreateSupportTable is Called....");
    }

    //creating Other Deduction Table
    private void createOtherDeductionTable(SQLiteDatabase db) {
        String query = " CREATE TABLE IF NOT EXISTS " + DBConstants.TABLE_NAME_OTHER_DEDUCTION + "(" +
                DBConstants.COLUMN_OTHER_DEDUCTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBConstants.COLUMN_OTHER_DEDUCTION_MONTH_YEAR + " DATE UNIQUE," +
                DBConstants.COLUMN_OTHER_DEDUCTION_AMOUNT + " INTEGER," +
                DBConstants.COLUMN_OTHER_DEDUCTION_DESCRIPTION + " TEXT)";
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

    public boolean insertSupportAmount(SupportTableDetails supportTableDetails) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.COLUMN_SUPPORT_MONTH_YEAR, supportTableDetails.getDate());
        values.put(DBConstants.COLUMN_SUPPORT_AMOUNT, supportTableDetails.getCost());

        Log.d("Database", "after inserting date in support..." + supportTableDetails.getDate());
        Log.d("Database", "after inserting cost in support..." + supportTableDetails.getCost());
        return database.insert(DBConstants.TABLE_NAME_SUPPORT, null, values) != -1;
    }

    public boolean insertOtherDeductionAmount(OtherDeductionDetails otherDeductionDetails) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.COLUMN_OTHER_DEDUCTION_MONTH_YEAR, otherDeductionDetails.getDate());
        values.put(DBConstants.COLUMN_OTHER_DEDUCTION_AMOUNT, otherDeductionDetails.getCost());

        Log.d("Database", "after inserting date in support..." + otherDeductionDetails.getDate());
        Log.d("Database", "after inserting cost in support..." + otherDeductionDetails.getCost());
        return database.insert(DBConstants.TABLE_NAME_OTHER_DEDUCTION, null, values) != -1;
    }

    public double getTotalSupportAmt() {
        double supportAmt = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select sum(" + DBConstants.COLUMN_SUPPORT_AMOUNT + ") from "
                + DBConstants.TABLE_NAME_SUPPORT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            supportAmt = cursor.getDouble(0);
        }
        cursor.close();
        return supportAmt;
    }

    public double getTotalOtherDeductionAmt() {
        double otherDeductionAmt = 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select sum(" + DBConstants.COLUMN_OTHER_DEDUCTION_AMOUNT + ") from "
                + DBConstants.TABLE_NAME_OTHER_DEDUCTION, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            otherDeductionAmt = cursor.getDouble(0);
        }
        cursor.close();
        return otherDeductionAmt;
    }

    public List<OtherDeductionDetails> getOtherDeductionDetailItems() {
        List<OtherDeductionDetails> otherDeductionDetailsList = null;
        Cursor mCursor = getReadableDatabase().query(DBConstants.TABLE_NAME_OTHER_DEDUCTION, null, null, null,
                null, null, DBConstants.COLUMN_OTHER_DEDUCTION_MONTH_YEAR);
        if (mCursor.getCount() > 0) {
            otherDeductionDetailsList = new ArrayList<>();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                OtherDeductionDetails otherDeductionDetails = new OtherDeductionDetails(
                        mCursor.getString(mCursor.getColumnIndex(DBConstants.COLUMN_OTHER_DEDUCTION_MONTH_YEAR)),
                        mCursor.getInt(mCursor.getColumnIndex(DBConstants.COLUMN_OTHER_DEDUCTION_AMOUNT)),
                        mCursor.getString(mCursor.getColumnIndex(DBConstants.COLUMN_OTHER_DEDUCTION_DESCRIPTION)));
                otherDeductionDetailsList.add(otherDeductionDetails);
            }
        }
        return otherDeductionDetailsList;
    }

    public List<SupportTableDetails> getSupportItems() {
        List<SupportTableDetails> supportTableDetailsList = null;
        Cursor mCursor = getReadableDatabase().query(DBConstants.TABLE_NAME_SUPPORT, null, null, null,
                null, null, DBConstants.COLUMN_SUPPORT_MONTH_YEAR);
        if (mCursor.getCount() > 0) {
            supportTableDetailsList = new ArrayList<>();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                SupportTableDetails mSupportTableDetails = new SupportTableDetails(
                        mCursor.getString(mCursor.getColumnIndex(DBConstants.COLUMN_SUPPORT_MONTH_YEAR)),
                        mCursor.getInt(mCursor.getColumnIndex(DBConstants.COLUMN_SUPPORT_AMOUNT)));
                supportTableDetailsList.add(mSupportTableDetails);
            }
        }
        return supportTableDetailsList;
    }

}
