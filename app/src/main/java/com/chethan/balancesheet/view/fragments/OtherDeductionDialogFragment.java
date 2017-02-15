package com.chethan.balancesheet.view.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chethan.balancesheet.R;

/**
 * Created by chethanbanala on 2/12/17.
 */

public class OtherDeductionDialogFragment extends DialogFragment {

    private TextView selectDateTV;
    private EditText amountET;
    private EditText otherDescriptionET;
    private Button addBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_other_deduction_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectDateTV = (TextView) view.findViewById(R.id.others_date_tv);
        amountET = (EditText) view.findViewById(R.id.other_amount_et);
        otherDescriptionET = (EditText) view.findViewById(R.id.other_description);
        addBtn = (Button) view.findViewById(R.id.other_add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("date", selectDateTV.getText().toString());
                intent.putExtra("amount" , amountET.getText().toString());
                intent.putExtra("description" , otherDescriptionET.getText().toString());
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                dismiss();
            }

        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


}