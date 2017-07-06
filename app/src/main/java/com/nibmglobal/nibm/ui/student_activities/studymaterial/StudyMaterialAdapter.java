package com.nibmglobal.nibm.ui.student_activities.studymaterial;

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
public class StudyMaterialAdapter extends RecyclerView.Adapter<StudyMaterialAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<SupportStudyMaterial> list;

    public StudyMaterialAdapter(Context context, ArrayList<SupportStudyMaterial> list) {
        this.context = context;
        this.list = list;
    }

    public String getMaterialId(int position) {
        return list.get(position).getId();
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_study_materials, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {


            holder.textViewSemester.setText(list.get(position).getSemester());
            holder.textViewHeading.setText(list.get(position).getName());
            holder.textViewBook.setText(list.get(position).getBook());
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


