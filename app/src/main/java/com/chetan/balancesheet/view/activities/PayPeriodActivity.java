package com.chetan.balancesheet.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chetan.balancesheet.R;
import com.chetan.balancesheet.database.BalanceSheetDBHandler;
import com.chetan.balancesheet.model.BalanceSheetDetails;
import com.chetan.balancesheet.utils.Utils;
import com.chetan.balancesheet.view.fragments.DatePickerFragment;

public class PayPeriodActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    private Button submit;

    private TextView openingBalanceTV;
    private TextView endingBalanceTV;

    private TextView rateET;

    private EditText hoursET;
    private TextView creditET;
    private EditText debitET;
    private TextView finalBalanceTV;

    private TextWatcher hoursTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.length() == 0 ) {
                return;
            } else {
                Float openingBalance = Float.valueOf(openingBalanceTV.getText().toString());
                Integer hours = Integer.valueOf(editable.toString());
                Float rate = Float.valueOf(rateET.getText().toString());
                Float credit = Float.valueOf(rate * hours);
                creditET.setText(Utils.decimalFormatter(Double.valueOf(credit)));

                if( debitET.getText().length() > 0) {
                    Float debit = Float.valueOf(debitET.getText().toString());
                    Float endingBalance = Float.valueOf(openingBalance + (credit - debit));
                    endingBalanceTV.setText(Utils.decimalFormatter(Double.valueOf(endingBalance)));
                    finalBalanceTV.setText(Utils.decimalFormatter(Double.valueOf(endingBalance)));
                }

            }

        }
    };


    private TextWatcher debitTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

            if(editable.length() == 0) {
                return;
            } else if(editable.length() > 0) {
                if(hoursET.getText().length() > 0 && creditET.getText().length() > 0
                        && openingBalanceTV.getText().length() > 0) {
                    // openingBalanceTV + (creditET - debitET) = endingBalanceTV
                    Float openingBalance = Float.valueOf(openingBalanceTV.getText().toString());
                    Float credit = Float.valueOf(creditET.getText().toString());
                    Float debit = Float.valueOf(editable.toString());
                    Float endingBalance = Float.valueOf(openingBalance + (credit - debit));
                    endingBalanceTV.setText(Utils.decimalFormatter(Double.valueOf(endingBalance)));
                    finalBalanceTV.setText(Utils.decimalFormatter(Double.valueOf(endingBalance)));
                }
            }

        }
    };
    private TextView startPayDate;
    private TextView endPayDate;
    private TextView payCheckDate;

    private double balanceAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period);
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        setTitle(title);

        //TODO create dialogPicker for picking dates for below fields
        startPayDate = (TextView) findViewById(R.id.start_pay_period_et);//Date Picker
        endPayDate = (TextView) findViewById(R.id.end_pay_period_et);// Data picker
        payCheckDate = (TextView) findViewById(R.id.paycheck_date_et);// Date Picker

        startPayDate.setOnClickListener(this);
        endPayDate.setOnClickListener(this);
        payCheckDate.setOnClickListener(this);

        // TODO: This balance will get from table
        //For now this value is getting from shared preferences
        openingBalanceTV = (TextView) findViewById(R.id.opening_balance_et);
        endingBalanceTV = (TextView) findViewById(R.id.ending_balance_et);
        //For now this value is stored in shared preferences
        finalBalanceTV = (TextView) findViewById(R.id.final_balance_tv);

        //TODO: Get the rate from the settings
        rateET = (TextView) findViewById(R.id.rate_et);
        rateET.setText("45.60");

        balanceAmount = BalanceSheetDBHandler.getInstance().getOpeningBalance();
        String decimalFormattedBalAmt = Utils.decimalFormatter(balanceAmount);
        openingBalanceTV.setText(String.valueOf(decimalFormattedBalAmt));
        finalBalanceTV.setText("$" + decimalFormattedBalAmt);

        hoursET = (EditText) findViewById(R.id.hours_et);
        debitET = (EditText) findViewById(R.id.debit_et);

        hoursET.addTextChangedListener(hoursTextWatcher);
        debitET.addTextChangedListener(debitTextWatcher);

        creditET = (TextView) findViewById(R.id.credit_et);

        submit = (Button) findViewById(R.id.submit_btn);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submit_btn) {
            BalanceSheetDetails mBalanceSheetDetails = new BalanceSheetDetails();
            //TODO initialize balanceSheetDetails (Set Date Picker Values)
            mBalanceSheetDetails.setStartDate(startPayDate.getText().toString());
            mBalanceSheetDetails.setEndDate(endPayDate.getText().toString());
            mBalanceSheetDetails.setPayCheckDate(payCheckDate.getText().toString());
            mBalanceSheetDetails.setOpeningBalance(Float.parseFloat(openingBalanceTV.getText().toString()));
            mBalanceSheetDetails.setRate(Float.parseFloat(rateET.getText().toString()));
            mBalanceSheetDetails.setHours(Integer.parseInt(hoursET.getText().toString()));
            mBalanceSheetDetails.setCredit(Float.parseFloat(creditET.getText().toString()));
            mBalanceSheetDetails.setDebit(Float.parseFloat(debitET.getText().toString()));
            mBalanceSheetDetails.setEndingBalance(Float.parseFloat(endingBalanceTV.getText().toString()));

            BalanceSheetDBHandler.getInstance().insertPayCheck(mBalanceSheetDetails);
            //TODO Toast for successfully msg
            Toast.makeText(this, "PayCheck Inserted: ", Toast.LENGTH_SHORT).show();
            finish();
        } else if ( v.getId() == R.id.start_pay_period_et) {
            showDatePickerDialog(v);
        } else if ( v.getId() == R.id.end_pay_period_et) {
            showDatePickerDialog(v);
        } else if (v.getId() == R.id.paycheck_date_et) {
            showDatePickerDialog(v);
        }

    }

    public void showDatePickerDialog(View view) {
        DatePickerFragment picker = new DatePickerFragment();
        picker.show(getFragmentManager(), "datePicker");
        picker.DateView(view);
    }
}