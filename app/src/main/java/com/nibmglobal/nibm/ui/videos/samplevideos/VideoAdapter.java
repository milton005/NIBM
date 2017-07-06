package com.nibmglobal.nibm.ui.videos.samplevideos;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindlabs on 11/10/15.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.HomeViewHolder> {

    private ArrayList<SupportVideos> videos;
    private Context context;
    public VideoAdapter(ArrayList<SupportVideos> videos, Context context) {

        this.videos = videos;
        this.context = context;
    }

    public SupportVideos getObject(int position) {
        return videos.get(position);
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_videos, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        try {

            holder.textTitle.setText(videos.get(position).getVideoName());
            Picasso.with(context)
                    .load(videos.get(position).getVideoThumbUrl())
                    .placeholder(R.drawable.ic_action_logo) // optional
                    .error(R.drawable.ic_action_logo)         // optional
                    .into(holder.imageDrawer);
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.videos.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        ImageView imageDrawer;

        public HomeViewHolder(View itemView) {
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            imageDrawer = (ImageView) itemView.findViewById(R.id.homeIcon);
        }

    }

}


