package com.chethan.balancesheet.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chethan.balancesheet.R;
import com.chethan.balancesheet.adapter.SupportAdapter;
import com.chethan.balancesheet.database.BalanceSheetDBHandler;
import com.chethan.balancesheet.model.SupportTableDetails;

import java.util.List;

public class SupportFragment extends BaseBalanceSheetFragment implements View.OnClickListener {

    private EditText monthYearTV;
    private EditText amountET;
    private Button addBtn;
    private SupportTableDetails supportTableDetails;
    private TextView totDeducTV;
    private RecyclerView mRecyclerView;
    private SupportAdapter mAdapter;
    private List<SupportTableDetails> supportItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_support, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        supportTableDetails = new SupportTableDetails();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        supportItems = BalanceSheetDBHandler.getInstance().getSupportItems();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new SupportAdapter(supportItems);
        mRecyclerView.setAdapter(mAdapter);

        monthYearTV = (EditText) view.findViewById(R.id.select_date_tv);
        amountET = (EditText) view.findViewById(R.id.enter_amount_et);
        totDeducTV = (TextView) view.findViewById(R.id.your_deductions_tv);
        addBtn = (Button) view.findViewById(R.id.add_btn);
        addBtn.setOnClickListener(this);

        double totalSupportAmt = BalanceSheetDBHandler.getInstance().getTotalSupportAmt();
        totDeducTV.setText(String.valueOf(totalSupportAmt));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn) {
            insertSupportDetailsintoDB();
        }
    }

    private void insertSupportDetailsintoDB() {
        supportTableDetails.setCost(Integer.parseInt(amountET.getText().toString()));
        supportTableDetails.setDate(monthYearTV.getText().toString());
        if (BalanceSheetDBHandler.getInstance().insertSupportAmount(supportTableDetails)) {
            supportItems.add(supportTableDetails);
        }
        mAdapter.notifyDataSetChanged();

    }
}
