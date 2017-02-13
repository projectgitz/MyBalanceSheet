package com.chethan.balancesheet.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chethan.balancesheet.R;

/**
 * Created by 3164 on 24-01-2017.
 */

public class OtherDeductionFragment extends BaseBalanceSheetFragment {

    private FloatingActionButton floatingActionBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_other_deductions, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionBtn = (FloatingActionButton) view.findViewById(R.id.floating_btn);
        floatingActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherDeductionDialogFragment otherDeductionDialogFragment = new OtherDeductionDialogFragment();
                otherDeductionDialogFragment.show(getFragmentManager(), "otherDeductionDialogFragment");
            }
        });
    }
}
