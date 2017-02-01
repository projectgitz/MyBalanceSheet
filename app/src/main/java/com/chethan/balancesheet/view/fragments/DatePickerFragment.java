package com.chethan.balancesheet.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.chethan.balancesheet.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ckumo on 1/26/2017.
 */
public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener dateListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current date as the default date in the picker
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), dateListener, year, month, day);
    }

    public void setDatePickerListener(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.dateListener=onDateSetListener;
    }
}
