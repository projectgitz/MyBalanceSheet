package com.chetan.balancesheet.view.activities;

import android.content.SharedPreferences;
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
import com.chetan.balancesheet.base.BalanceSheetApplication;
import com.chetan.balancesheet.database.BalanceSheetDBHandler;
import com.chetan.balancesheet.model.BalanceSheetDetails;

public class PayPeriodActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    private TextView titleTV;
    private Button submit;

    private TextView openingBalanceTV;
    private TextView endingBalanceTV;

    private TextView rateET;

    private EditText hoursET;
    private TextView creditET;
    private EditText debitET;
    private TextView finalBalanceTV;

    //Shared Preferences
    private SharedPreferences mPref;
    private String savedPrefValue;
    private String SAVED_OPENING_BALANACE_KEY = "OPENING_BALANCE_KEY";
    private String PREF_NAME = "BALANCESHEET_PREF";

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
                creditET.setText(String.valueOf(credit));

                if( debitET.getText().length() > 0) {
                    Float debit = Float.valueOf(debitET.getText().toString());
                    Float endingBalance = Float.valueOf(openingBalance + (credit - debit));
                    endingBalanceTV.setText(String.valueOf(endingBalance));
                    finalBalanceTV.setText(String.valueOf(endingBalance));
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
                    endingBalanceTV.setText(String.valueOf(endingBalance));
                    finalBalanceTV.setText(String.valueOf(endingBalance));
                }
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period);
        mPref = BalanceSheetApplication.getInstance().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        setTitle(title);
        titleTV = (TextView) findViewById(R.id.pay_check_main_tv);
        titleTV.setText(title);

        //TODO create dialogPicker for picking dates for below fields
        findViewById(R.id.start_pay_period_et); //Date Picker
        findViewById(R.id.end_pay_period_et); // Data picker
        findViewById(R.id.paycheck_date_et); // Date Picker

        // TODO: This balance will get from table
        //For now this value is getting from shared preferences
        openingBalanceTV = (TextView) findViewById(R.id.opening_balance_et);
        endingBalanceTV = (TextView) findViewById(R.id.ending_balance_et);
        //For now this value is stored in shared preferences
        finalBalanceTV = (TextView) findViewById(R.id.final_balance_tv);

        //TODO: Get the rate from the settings
        rateET = (TextView) findViewById(R.id.rate_et);
        rateET.setText("45.60");

        savedPrefValue = mPref.getString(SAVED_OPENING_BALANACE_KEY, "");
        if(savedPrefValue.length() == 0) {
            openingBalanceTV.setText("0");
        } else {
            openingBalanceTV.setText(savedPrefValue);
            finalBalanceTV.setText(savedPrefValue);
        }

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
        BalanceSheetDetails mBalanceSheetDetails = new BalanceSheetDetails();
        //TODO initialize balanceSheetDetails (Set Date Picker Values)
        mBalanceSheetDetails.setOpeningBalance(Float.parseFloat(openingBalanceTV.getText().toString()));
        mBalanceSheetDetails.setRate(Float.parseFloat(rateET.getText().toString()));
        mBalanceSheetDetails.setHours(Integer.parseInt(hoursET.getText().toString()));
        mBalanceSheetDetails.setCredit(Float.parseFloat(creditET.getText().toString()));
        mBalanceSheetDetails.setDebit(Float.parseFloat(debitET.getText().toString()));
        mBalanceSheetDetails.setEndingBalance(Float.parseFloat(endingBalanceTV.getText().toString()));

        BalanceSheetDBHandler.getInstance().insertPayCheck(mBalanceSheetDetails);
        //TODO Toast for successfully msg
        Toast.makeText(this, "PayCheck Inserted: ", Toast.LENGTH_SHORT).show();
        mPref.edit().putString(SAVED_OPENING_BALANACE_KEY, finalBalanceTV.getText().toString()).commit();
        finish();
    }

}