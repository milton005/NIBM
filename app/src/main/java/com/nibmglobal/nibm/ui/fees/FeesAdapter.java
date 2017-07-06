package com.nibmglobal.nibm.ui.fees;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nibmglobal.nibm.R;

import java.util.ArrayList;

/**
 * Created by mindlabs on 11/10/15.
 */
public class FeesAdapter extends RecyclerView.Adapter<FeesAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<SupportFees> list;
    PaymentCall paymentCall;

    public FeesAdapter(Context context, ArrayList<SupportFees> list) {
        this.context = context;
        this.list = list;
    }

    public SupportFees getObject(int position) {
        return list.get(position);
    }

    public void setCallbackListner(FeesFragment context) {
        this.paymentCall = context;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_fees, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {
            holder.textViewSemester.setText((this.list.get(position)).getPaymentMode());
            holder.textViewHeading.setText((this.list.get(position)).getFeesName());
            holder.textViewBook.setText((this.list.get(position)).getPayStatus());
            if ((this.list.get(position)).getPayStatus().equalsIgnoreCase("Due")) {
                holder.buttonPay.setVisibility(View.VISIBLE);
            } else {
                holder.buttonPay.setVisibility(View.GONE);
            }
            holder.buttonPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paymentCall.activatePayment();
                }
            });
        } catch (Exception e) {
        }

        try {


            holder.textViewSemester.setText(list.get(position).getPaymentMode());
            holder.textViewHeading.setText(list.get(position).getFeesName());
            holder.textViewBook.setText(list.get(position).getPayStatus());
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        Button buttonPay;
        TextView textViewHeading;
        TextView textViewSemester;
        TextView textViewBook;

        public ProfileViewHolder(View itemView) {
            super(itemView);

            textViewSemester = (TextView) itemView.findViewById(R.id.textSemester);
            textViewHeading = (TextView) itemView.findViewById(R.id.textHeading);
            textViewBook = (TextView) itemView.findViewById(R.id.textBook);
            this.buttonPay = (Button) itemView.findViewById(R.id.buttonPay);
        }
    }
}


