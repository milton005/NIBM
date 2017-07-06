package com.nibmglobal.nibm.ui.fees.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibmglobal.nibm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindlabs on 11/10/15.
 */
public class FeeDetailAdapter extends RecyclerView.Adapter<FeeDetailAdapter.ViewHolder_> {

    private Context context;
    private ArrayList<String> list;
    private List<String> types;

    public FeeDetailAdapter(Context context, ArrayList<String> list, List<String> types) {
        this.context = context;
        this.list = list;
        this.types = types;
    }


    @Override
    public ViewHolder_ onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_exam_schedule_detail, parent, false);
        return new ViewHolder_(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder_ holder, int position) {

        try {

            holder.textViewType.setText(types.get(position));
            holder.textViewValue.setText(list.get(position));
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.types.size();
    }

    public static class ViewHolder_ extends RecyclerView.ViewHolder {

        TextView textViewType;
        TextView textViewValue;

        public ViewHolder_(View itemView) {
            super(itemView);

            textViewType = (TextView) itemView.findViewById(R.id.type);
            textViewValue = (TextView) itemView.findViewById(R.id.value);
        }
    }
}


