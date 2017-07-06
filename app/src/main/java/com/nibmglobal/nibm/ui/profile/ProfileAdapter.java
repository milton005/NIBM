package com.nibmglobal.nibm.ui.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindlabs on 11/10/15.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private Context context;
    private ArrayList<String> list;
    private List<String> types;

    public ProfileAdapter(Context context, ArrayList<String> list, List<String> types) {
        this.context = context;
        this.list = list;
        this.types = types;
    }


    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_profile, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        try {

            holder.textViewType.setText(types.get(position));
            holder.textViewValue.setText(list.get(position));
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return this.types.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView textViewType;
        TextView textViewValue;

        public ProfileViewHolder(View itemView) {
            super(itemView);

            textViewType = (TextView) itemView.findViewById(R.id.type);
            textViewValue = (TextView) itemView.findViewById(R.id.value);
        }
    }
}


