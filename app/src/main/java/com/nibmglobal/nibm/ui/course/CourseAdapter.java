package com.nibmglobal.nibm.ui.course;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.ui.student_activities.assignment.SupportAssignment;

import java.util.ArrayList;

/**
 * Created by mindlabs on 11/10/15.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder_> {

    private Context context;
    private ArrayList<SupportCourse> list;

    public CourseAdapter(Context context, ArrayList<SupportCourse> list) {
        this.context = context;
        this.list = list;
    }

    public SupportCourse getObject(int position) {
        return list.get(position);
    }




    @Override
    public ViewHolder_ onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_course, parent, false);
        return new ViewHolder_(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder_ holder, int position) {

        try {

            holder.textViewHeading.setText(list.get(position).getCourseTitle());
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ViewHolder_ extends RecyclerView.ViewHolder {

        TextView textViewHeading;

        public ViewHolder_(View itemView) {
            super(itemView);

            textViewHeading = (TextView) itemView.findViewById(R.id.textHeading);
        }
    }
}


