package com.chethan.balancesheet.view.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chethan.balancesheet.R;
import com.chethan.balancesheet.utils.AppConstants;
import com.chethan.balancesheet.utils.Utils;

public class SettingsActvity extends BaseBalanceSheetActivity implements View.OnClickListener {

    private EditText settingsRateET;
    SharedPreferences mPref;
    private Button setBtn;
    private TextView settingValueTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_actvity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        settingsRateET = (EditText) findViewById(R.id.settings_rate_et);
        settingValueTV = (TextView) findViewById(R.id.settings_rate_tv_value);
        setBtn = (Button) findViewById(R.id.settings_add_btn);
        setBtn.setText("EDIT");
        setBtn.setOnClickListener(this);
        mPref = this.getSharedPreferences(AppConstants.PREF_NAME, MODE_PRIVATE);
        updateRateValue();
    }

    @Override
    public void onClick(View v) {
        if(v == setBtn) {
            if(setBtn.getText().toString() == "EDIT") {
                settingsRateET.setText("");
                setBtn.setText("SET");
                Utils.showSoftKeyboard(settingsRateET);
                settingValueTV.setVisibility(View.GONE);
                settingsRateET.setVisibility(View.VISIBLE);
            } else if(setBtn.getText().toString() == "SET"){
                if(!TextUtils.isEmpty(settingsRateET.getText().toString())) {
                    setBtn.setText("EDIT");
                    Utils.hideSoftKeyboard(settingsRateET);
                    settingValueTV.setVisibility(View.VISIBLE);
                    settingsRateET.setVisibility(View.GONE);
                    mPref.edit()
                            .putFloat(AppConstants.RATE , Float.valueOf(settingsRateET.getText().toString()))
                            .commit();
                    updateRateValue();
                } else {
                    Toast.makeText(this, "Please Enter Rate..", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void updateRateValue() {
        settingsRateET.setText(String.valueOf(mPref.getFloat(AppConstants.RATE,0f)));
        settingValueTV.setText(String.valueOf(mPref.getFloat(AppConstants.RATE,0f)));
    }

}