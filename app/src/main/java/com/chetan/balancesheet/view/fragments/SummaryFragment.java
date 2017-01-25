package com.chetan.balancesheet.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chetan.balancesheet.R;
import com.chetan.balancesheet.base.BalanceSheetApplication;

/**
 * Created by 3164 on 24-01-2017.
 */

public class SummaryFragment extends BaseBalanceSheetFragment {

    private TextView finalBalanceTV;

    //Shared Preferences
    private SharedPreferences mPref;
    private String savedPrefValue;
    private String SAVED_OPENING_BALANACE_KEY = "OPENING_BALANCE_KEY";
    private String PREF_NAME = "BALANCESHEET_PREF";

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_summary,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        finalBalanceTV = (TextView) view.findViewById(R.id.balance_sheet_balance_amount_tv);
        mPref = BalanceSheetApplication.getInstance().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        savedPrefValue = mPref.getString(SAVED_OPENING_BALANACE_KEY, "");
        if(savedPrefValue.length() == 0) {
            finalBalanceTV.setText("Balance: $" + "00.00");
        } else {
            finalBalanceTV.setText("Balance: $" + savedPrefValue);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
