package com.chetan.balancesheet.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chetan.balancesheet.R;

/**
 * Created by 3164 on 24-01-2017.
 */

public class SupportFragment extends BaseBalanceSheetFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_support,container,false);
    }


}
