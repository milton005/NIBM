package com.nibmglobal.nibm.ui.questionpaper;

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
public class QuestionPaperAdapter extends RecyclerView.Adapter<QuestionPaperAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<SupportQuestionPaper> list;

    public QuestionPaperAdapter(Context context, ArrayList<SupportQuestionPaper> list) {
        this.context = context;
        this.list = list;
    }

    public SupportQuestionPaper getObject(int position) {
        return list.get(position);
    }




    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_questionpaper, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {


            holder.textViewCourseName.setText(list.get(position).getCourseName());
            holder.textViewSemester.setText(list.get(position).getSemsesteName());
            holder.textViewSubjectName.setText(list.get(position).getSubjectName());
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCourseName;
        TextView textViewSemester;
        TextView textViewSubjectName;

        public ProfileViewHolder(View itemView) {
            super(itemView);

            textViewCourseName = (TextView) itemView.findViewById(R.id.textCourse);
            textViewSemester = (TextView) itemView.findViewById(R.id.textSemester);
            textViewSubjectName = (TextView) itemView.findViewById(R.id.textSubject);
        }
    }
}


