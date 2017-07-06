package com.nibmglobal.nibm.ui.Webinar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubeStandalonePlayer;
//import com.google.android.youtube.player.YouTubeThumbnailLoader;
//import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 8/11/2016.
 */
public class Webinar_adapter extends RecyclerView.Adapter<Webinar_adapter.Viewholder> {
//    String[] VideoID = {"P3mAtvs5Elc", "nCgQDjiotG0", "P3mAtvs5Elc"};
    private Context context;
    private  ArrayList<Support_webinar>list;
    public Webinar_adapter(Context context, ArrayList<Support_webinar> list) {
        this.context=context;
        this.list=list;
    }
    public Support_webinar getObject(int position) {
        return list.get(position);
    }
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_webinar, parent, false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        String s1=list.get(position).getname();

       holder.textView.setText(Html.fromHtml(s1));
//        String s2=list.get(position).getDescription();
//        holder.textView2.setText(Html.fromHtml(s2));
//        holder.webView.loadUrl(list.get(position).getYoutubeId());
//        Picasso.with(context).load(list.get(position).getYoutubeId()).into(holder.imageView);
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener= new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }
        };
        holder.youTubeThumbnailView.initialize(Const.youtubekey, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(list.get(position).getYoutubeId());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });


    }
//    @Override
//    public void onViewDetachedFromWindow(Viewholder holder) {
//        super.onViewDetachedFromWindow(holder);
//
//        holder.webView.onPause();
//    }

    @Override
    public int getItemCount() {
return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
      protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
      YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;
//        WebView webView;
        TextView textView;
//        TextView textView2;
        ImageView imageView;
        public Viewholder(View itemView) {
            super(itemView);
            playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);
//          imageView= (ImageView) itemView.findViewById(R.id.homeImages);
          playButton.setOnClickListener(this);
//            webView= (WebView) itemView.findViewById(R.id.thumbnailyotube);
//            textView2= (TextView) itemView.findViewById(R.id.textViewwebinar2);
            textView= (TextView) itemView.findViewById(R.id.textwebinar);
           relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
          youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);

        }

        @Override
        public void onClick(View v) {
            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) context, Const.youtubekey, list.get(getPosition()).getYoutubeId());
          context.startActivity(intent);
        }
    }
}
