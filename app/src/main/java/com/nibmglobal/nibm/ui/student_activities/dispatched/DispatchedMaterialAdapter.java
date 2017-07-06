package com.nibmglobal.nibm.ui.student_activities.dispatched;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.ui.student_activities.journal.SupportJournal;

import java.util.ArrayList;

/**
 * Created by mindlabs on 11/10/15.
 */
public class DispatchedMaterialAdapter extends RecyclerView.Adapter<DispatchedMaterialAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<SupportDispatchedMaterials> list;

    public DispatchedMaterialAdapter(Context context, ArrayList<SupportDispatchedMaterials> list) {
        this.context = context;
        this.list = list;
    }

    public SupportDispatchedMaterials getObject(int position) {
        return list.get(position);
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_dispatched_material, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {


            holder.textViewSemester.setText("Dispatched Date : "+list.get(position).getDispatchedDate());
            holder.textViewHeading.setText(list.get(position).getDispatchMedium());
            holder.textViewBook.setText("Status : "+list.get(position).getIssueStatus());
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView textViewHeading;
        TextView textViewSemester;
        TextView textViewBook;

        public ProfileViewHolder(View itemView) {
            super(itemView);

            textViewSemester = (TextView) itemView.findViewById(R.id.textSemester);
            textViewHeading = (TextView) itemView.findViewById(R.id.textHeading);
            textViewBook = (TextView) itemView.findViewById(R.id.textBook);
        }
    }
}


