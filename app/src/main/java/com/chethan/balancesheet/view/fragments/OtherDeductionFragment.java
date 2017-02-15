package com.chethan.balancesheet.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chethan.balancesheet.R;

/**
 * Created by 3164 on 24-01-2017.
 */

public class OtherDeductionFragment extends BaseBalanceSheetFragment {
    private static final int OTHER_DEDUCTION_FRAGMENT_DIALOG = 1;
    private FloatingActionButton floatingActionBtn;
    private TextView testTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_other_deductions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testTV = (TextView) view.findViewById(R.id.test_fragment);
        floatingActionBtn = (FloatingActionButton) view.findViewById(R.id.floating_btn);
        floatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment();
            }
        });
    }

    private void displayFragment() {
        OtherDeductionDialogFragment otherDeductionDialogFragment = new OtherDeductionDialogFragment();
        otherDeductionDialogFragment.setTargetFragment(this, OTHER_DEDUCTION_FRAGMENT_DIALOG);
        otherDeductionDialogFragment.show(getFragmentManager().beginTransaction(), "otherDeductionDialogFragment");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)  {
            case OTHER_DEDUCTION_FRAGMENT_DIALOG:
                if(resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String date = bundle.getString("date");
                    String amount = bundle.getString("amount");
                    String description = bundle.getString("description");
                    Toast.makeText(getActivity(), "Values are : " + date + "\n" + amount + "\n" + description,
                            Toast.LENGTH_SHORT).show();
                    StringBuilder testStr = new StringBuilder(100);
                    testStr.append(date)
                            .append("\n")
                            .append(amount)
                            .append("\n")
                            .append(description);
                    testTV.setText(testStr);
                }
                break;
        }
    }
}