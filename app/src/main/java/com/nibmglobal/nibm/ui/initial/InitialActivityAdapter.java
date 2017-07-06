package com.nibmglobal.nibm.ui.initial;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibmglobal.nibm.R;

import java.util.List;

/**
 * Created by mindlabs on 11/30/15.
 */
public class InitialActivityAdapter extends BaseAdapter {

    private TypedArray images;
    private List<String> titles;
    private Context context;
    private LayoutInflater inflater;

    public InitialActivityAdapter(TypedArray images, List<String> titles, Context context) {
        this.images = images;
        this.titles = titles;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.inflate_grid_initial, null);
            viewHolder = new ViewHolder();
            viewHolder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
            viewHolder.imageDrawer = (ImageView) convertView.findViewById(R.id.homeIcon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textTitle.setText(titles.get(position));
        viewHolder.imageDrawer.setImageDrawable(images.getDrawable(position));
        return convertView;
    }

    private static class ViewHolder {

        TextView textTitle;
        ImageView imageDrawer;
    }
}
