package com.chetan.balancesheet.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chetan.balancesheet.R;
import com.chetan.balancesheet.database.BalanceSheetDBHandler;
import com.chetan.balancesheet.utils.Utils;

/**
 * Created by 3164 on 24-01-2017.
 */

public class SummaryFragment extends BaseBalanceSheetFragment {

    private TextView finalBalanceTV;

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

        double balanceAmount = BalanceSheetDBHandler.getInstance().getOpeningBalance();
        String decimalFormtedBalanceAmt = Utils.decimalFormatter(balanceAmount);
        finalBalanceTV.setText("Balance Amount: $" + decimalFormtedBalanceAmt);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
