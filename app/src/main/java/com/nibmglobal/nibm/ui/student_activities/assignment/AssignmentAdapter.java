package com.nibmglobal.nibm.ui.student_activities.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibmglobal.nibm.R;

import java.util.ArrayList;

/**
 * Created by mindlabs on 11/10/15.
 */
public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<SupportAssignment> list;

    public AssignmentAdapter(Context context, ArrayList<SupportAssignment> list) {
        this.context = context;
        this.list = list;
    }

    public SupportAssignment getObject(int position) {
        return list.get(position);
    }




    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_assignment, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {


            holder.textViewSubHeading.setText(list.get(position).getAssignmentType());
            holder.textViewHeading.setText(list.get(position).getAssignmentTitle());
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSubHeading;
        TextView textViewHeading;

        public ProfileViewHolder(View itemView) {
            super(itemView);

            textViewSubHeading = (TextView) itemView.findViewById(R.id.textType);
            textViewHeading = (TextView) itemView.findViewById(R.id.textHeading);
        }
    }
}


