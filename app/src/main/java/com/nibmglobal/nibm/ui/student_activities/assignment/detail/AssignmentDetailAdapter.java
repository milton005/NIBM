package com.nibmglobal.nibm.ui.student_activities.assignment.detail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.ui.student_activities.DownloadListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindlabs on 11/10/15.
 */
public class AssignmentDetailAdapter extends RecyclerView.Adapter<AssignmentDetailAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> list;
    private List<String> types;
    private DownloadListner mCallbacks;

    public AssignmentDetailAdapter(Context context, ArrayList<String> list, List<String> types) {
        this.context = context;
        this.list = list;
        this.types = types;
    }

    public void setCallbacks() {
        mCallbacks = (DownloadListner) context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_detail, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        try {

            holder.textViewType.setText(types.get(position));
            if (URLUtil.isHttpUrl(list.get(position))){

                holder.textViewValue.setTextColor(Color.BLUE);
                holder.textViewValue.setPaintFlags(holder.textViewValue.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            }else {
                holder.textViewValue.setTextColor(Color.WHITE);
                holder.textViewValue.setPaintFlags(0);
            }
            holder.textViewValue.setText(list.get(position));
        } catch (Exception e) {

        }

        holder.textViewValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (URLUtil.isHttpUrl(list.get(position))){
                    mCallbacks.onDownloadItem(list.get(position));

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.types.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewType;
        TextView textViewValue;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewType = (TextView) itemView.findViewById(R.id.type);
            textViewValue = (TextView) itemView.findViewById(R.id.value);

        }

    }
}


