package com.chethan.balancesheet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chethan.balancesheet.R;
import com.chethan.balancesheet.base.BalanceSheetApplication;
import com.chethan.balancesheet.model.SupportTableDetails;

import java.util.List;

/**
 * Created by chethanbanala on 2/8/17.
 */

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.MyViewHolder> {

    private final List<SupportTableDetails> supportItems;

    public SupportAdapter(List<SupportTableDetails> supportItems) {
        this.supportItems=supportItems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView monthYearTV;
        private TextView amountTV;

        public MyViewHolder(View itemView) {
            super(itemView);
            monthYearTV = (TextView) itemView.findViewById(R.id.month_year_item_tv);
            amountTV = (TextView) itemView.findViewById(R.id.amount_item_tv);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.support_list_item, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SupportTableDetails supportTableDetails = supportItems.get(position);
        holder.amountTV.setText(String.valueOf(supportTableDetails.getCost()));
        holder.monthYearTV.setText(supportTableDetails.getDate());
    }

    @Override
    public int getItemCount() {
        return supportItems.size();
    }

}
