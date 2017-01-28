package com.chetan.balancesheet.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chetan.balancesheet.R;
import com.chetan.balancesheet.base.BalanceSheetApplication;
import com.chetan.balancesheet.database.BalanceSheetDBHandler;
import com.chetan.balancesheet.utils.Utils;
import com.chetan.balancesheet.view.activities.PayPeriodActivity;

/**
 * Created by 3164 on 24-01-2017.
 */

public class BalanceSheetFragment extends BaseBalanceSheetFragment implements View.OnClickListener {

    private Button firstPayPeriodButton;
    private Button secondPayPeriodButton;
    private TextView finalBalanceTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_balance_sheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstPayPeriodButton = (Button) view.findViewById(R.id.first_pay_period_btn);
        secondPayPeriodButton = (Button) view.findViewById(R.id.second_pay_period_btn);
        finalBalanceTV = (TextView) view.findViewById(R.id.bs_final_balance_tv);

        double balanceAmount = BalanceSheetDBHandler.getInstance().getOpeningBalance();
        String decimalFormattedBalAmt = Utils.decimalFormatter(balanceAmount);
        finalBalanceTV.setText("Balance: $" + "00.00");
        finalBalanceTV.setText("Balance: $" + decimalFormattedBalAmt);

        firstPayPeriodButton.setOnClickListener(this);
        secondPayPeriodButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == firstPayPeriodButton) {
            Intent intent = new Intent(getContext(), PayPeriodActivity.class);
            startActivity(intent);
        } else if (v == secondPayPeriodButton) {
            Intent intent = new Intent(getContext(), PayPeriodActivity.class);
            startActivity(intent);
        }
    }

}
