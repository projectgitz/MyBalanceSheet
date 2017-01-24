package com.chetan.balancesheet.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chetan.balancesheet.R;
import com.chetan.balancesheet.database.BalanceSheetDBHandler;
import com.chetan.balancesheet.model.BalanceSheetDetails;

public class PayPeriodActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    private TextView titleTV;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_pay_period);
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        setTitle(title);
        titleTV = (TextView) findViewById(R.id.first_pay_check_main_tv);
        titleTV.setText(title);
        //TODO create dialogPicker for picking dates for below fields
        findViewById(R.id.first_pay_period_et);
        findViewById(R.id.end_period_et);
        findViewById(R.id.first_paycheck_date_et);

        findViewById(R.id.first_opening_balance_et);
        findViewById(R.id.first_rate_et);
        findViewById(R.id.first_hours_et);
        findViewById(R.id.first_credit_et);
        findViewById(R.id.first_debit_et);
        submit = (Button) findViewById(R.id.first_save_btn);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        BalanceSheetDetails mBalanceSheetDetails = new BalanceSheetDetails();
        //TODO initialize balanceSheetDetails
        BalanceSheetDBHandler.getInstance().insertPayCheck(mBalanceSheetDetails);
        //TODO Toast for sussfully msg
        finish();
    }

}
