package com.nibmglobal.nibm.ui.notice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<SupportNoticeBoard> list;

    public NoticeBoardAdapter(Context context, ArrayList<SupportNoticeBoard> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_notice, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {


            holder.textViewHeading.setText(Html.fromHtml(list.get(position).getNoticeMessage()));
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


