package com.chetan.balancesheet.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
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

import static android.R.attr.editable;

public class PayPeriodActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_PAYPERIOD_TYPE = "EXTRA_PAY_PERIOD_TYPE";

    private Button submit;
    private TextView openingBalanceTV;
    private TextView endingBalanceTV;
    private TextView rateET;
    private EditText hoursET;
    private TextView creditET;
    private EditText debitET;
    private TextView finalBalanceTV;
    private TextView startPayDate;
    private TextView endPayDate;
    private TextView payCheckDate;

    private BalanceSheetDetails mBalanceSheetDetails;

    private void evaluateBalanceSheet() {
        int hours = mBalanceSheetDetails.getHours();
        double openingBalance = mBalanceSheetDetails.getOpeningBalance();
        float rate = mBalanceSheetDetails.getRate();
        double credit = rate * hours;
        double debits = mBalanceSheetDetails.getDebit();

        creditET.setText(Utils.decimalFormatter(credit));
        double endingBalance = openingBalance + (credit - debits);
        endingBalanceTV.setText(Utils.decimalFormatter(endingBalance));
        finalBalanceTV.setText(Utils.decimalFormatter(endingBalance));
    }

    private TextWatcher hoursTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            int hours = 0;
            if (!TextUtils.isEmpty(charSequence.toString())) {
                hours = Integer.valueOf(charSequence.toString());
            }
            mBalanceSheetDetails.setHours(hours);
            evaluateBalanceSheet();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
    private TextWatcher debitTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            double debit = 0;
            if (!TextUtils.isEmpty(charSequence.toString())) {
                debit = Double.parseDouble(charSequence.toString());
            }
            mBalanceSheetDetails.setDebit(debit);
            evaluateBalanceSheet();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period);
        mBalanceSheetDetails = new BalanceSheetDetails();

        int type = getIntent().getIntExtra(EXTRA_PAYPERIOD_TYPE, 1);
        if (type == 1) {
            setTitle(getString(R.string.first_pay_period));
        } else {
            setTitle(getString(R.string.second_pay_period));
        }
        //TODO create dialogPicker for picking dates for below fields
        startPayDate = (TextView) findViewById(R.id.start_pay_period_et);//Date Picker
        endPayDate = (TextView) findViewById(R.id.end_pay_period_et);// Data picker
        payCheckDate = (TextView) findViewById(R.id.paycheck_date_et);// Date Picker

        startPayDate.setOnClickListener(this);
        endPayDate.setOnClickListener(this);
        payCheckDate.setOnClickListener(this);

        openingBalanceTV = (TextView) findViewById(R.id.opening_balance_et);
        endingBalanceTV = (TextView) findViewById(R.id.ending_balance_et);
        finalBalanceTV = (TextView) findViewById(R.id.final_balance_tv);

        //TODO: Get the rate from the settings
        rateET = (TextView) findViewById(R.id.rate_et);
        rateET.setText("45.60");
        mBalanceSheetDetails.setRate(45.60f);

        double balanceAmount = BalanceSheetDBHandler.getInstance().getOpeningBalance();
        mBalanceSheetDetails.setOpeningBalance(balanceAmount);
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
        if (v.getId() == R.id.submit_btn) {
            BalanceSheetDBHandler.getInstance().insertPayCheck(mBalanceSheetDetails);
            Toast.makeText(this, "PayCheck Inserted: ", Toast.LENGTH_SHORT).show();
            finish();
        } else if (v.getId() == R.id.start_pay_period_et) {
            showDatePickerDialog(v);
        } else if (v.getId() == R.id.end_pay_period_et) {
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