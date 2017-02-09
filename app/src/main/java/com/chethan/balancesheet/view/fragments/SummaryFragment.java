package com.chethan.balancesheet.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chethan.balancesheet.R;
import com.chethan.balancesheet.database.BalanceSheetDBHandler;
import com.chethan.balancesheet.utils.Utils;

/**
 * Created by 3164 on 24-01-2017.
 */

public class SummaryFragment extends BaseBalanceSheetFragment {

    private TextView finalBalanceTV;
    private TextView supportDeducTV;
    private Button finalBalBtn;

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
        supportDeducTV = (TextView) view.findViewById(R.id.support_deductions);
        finalBalBtn = (Button) view.findViewById(R.id.final_balance_after_deductions);

        totSummaryCalculations();
    }

    private void totSummaryCalculations() {
        double totalSupportAmt = BalanceSheetDBHandler.getInstance().getTotalSupportAmt();
        double balanceAmount = BalanceSheetDBHandler.getInstance().getOpeningBalance();

        double finalBalAmount = balanceAmount - totalSupportAmt;

        finalBalanceTV.setText("Balance Amount: $" + String.valueOf(Utils.decimalFormatter(balanceAmount)));
        supportDeducTV.setText("Support Deductions: $" + String.valueOf(Utils.decimalFormatter(totalSupportAmt)));
        finalBalBtn.setText("$" + String.valueOf(Utils.decimalFormatter(finalBalAmount)));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
