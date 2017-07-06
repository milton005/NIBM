package com.nibmglobal.nibm.ui.main;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nibmglobal.nibm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindlabs on 11/10/15.
 */
public class HomeAdapter_ extends RecyclerView.Adapter<HomeAdapter_.HomeViewHolder> {

    private TypedArray images;
    private List<String> titles;
    private Context context;
    public HomeAdapter_(TypedArray images, List<String> titles, Context context) {

        this.images = images;
        this.titles = titles;
        this.context = context;
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_grid_home, parent, false);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        try {

            holder.textTitle.setText(titles.get(position));
            holder.imageDrawer.setImageDrawable(images.getDrawable(position));
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.titles.size();
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


