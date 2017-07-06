package com.nibmglobal.nibm.ui.student_activities.dispatched.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.ui.student_activities.dispatched.SubSupportDispatchedMaterial;
import com.nibmglobal.nibm.ui.student_activities.dispatched.SupportDispatchedMaterials;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindlabs on 11/10/15.
 */
public class DispatchedDetailAdapter extends RecyclerView.Adapter<DispatchedDetailAdapter.ViewHolder_> {

    private Context context;
    private ArrayList<SubSupportDispatchedMaterial> list;

    public DispatchedDetailAdapter(Context context, ArrayList<SubSupportDispatchedMaterial> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder_ onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_dispatched_detail, parent, false);
        return new ViewHolder_(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder_ holder, int position) {

        try {

            holder.textViewType.setText(list.get(position).getSemName());
            holder.textViewValue.setText(list.get(position).getName());
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
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


