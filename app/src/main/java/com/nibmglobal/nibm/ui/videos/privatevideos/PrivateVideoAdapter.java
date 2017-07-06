package com.nibmglobal.nibm.ui.videos.privatevideos;

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
public class PrivateVideoAdapter extends RecyclerView.Adapter<PrivateVideoAdapter.HomeViewHolder> {

    private Context context;
    private ArrayList<SupportPrivateVideo> videos;
    public PrivateVideoAdapter(ArrayList<SupportPrivateVideo> videos, Context context) {

        this.videos = videos;
        this.context = context;
    }

    public SupportPrivateVideo getObject(int position) {
        return videos.get(position);
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_private_video_subject, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        try {

            holder.textTitle.setText(videos.get(position).getSubjectName());
            /*Picasso.with(context)
                    .load(videos.get(position).getVideoThumbUrl())
                    .placeholder(R.drawable.ic_action_logo) // optional
                    .error(R.drawable.ic_action_logo)         // optional
                    .into(holder.imageDrawer);*/
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.videos.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
//        ImageView imageDrawer;

        public HomeViewHolder(View itemView) {
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.textHeading);
//            imageDrawer = (ImageView) itemView.findViewById(R.id.homeIcon);
        }

    }

}


