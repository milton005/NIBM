package com.nibmglobal.nibm.ui.student_activities.studymaterial.detail;

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
public class StudyMaterialChapterAdapter extends RecyclerView.Adapter<StudyMaterialChapterAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<SupportStudyMaterialChapter> list;

    public StudyMaterialChapterAdapter(Context context, ArrayList<SupportStudyMaterialChapter> list) {
        this.context = context;
        this.list = list;
    }

    public SupportStudyMaterialChapter getObject(int position) {
        return list.get(position);
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_study_material_detail, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {

            holder.textViewHeading.setText(list.get(position).getLessonName());
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView textViewHeading;

        public ProfileViewHolder(View itemView) {
            super(itemView);

            textViewHeading = (TextView) itemView.findViewById(R.id.textHeading);
        }
    }
}


