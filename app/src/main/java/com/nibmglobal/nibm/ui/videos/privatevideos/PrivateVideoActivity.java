package com.nibmglobal.nibm.ui.videos.privatevideos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.Utilities.ItemClickSupport.OnItemClickListener;
import com.nibmglobal.nibm.ui.videos.detail.VideoShowingActivity;
import com.nibmglobal.nibm.ui.videos.samplevideos.SupportVideos;
import com.nibmglobal.nibm.ui.videos.samplevideos.VideoAdapter;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrivateVideoActivity extends WebServiceActivity {
    private VideoAdapter adapter;
    private ArrayList<SupportVideos> allSampleVideos;
    LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private TextView noView;
    private SupportPrivateVideo supportPrivateVideo;
    private Toolbar toolbar;


    /*public static void start(Context context) {
        context.startActivity(new Intent(context, PrivateVideoActivity.class));
    }*/

    @Override
    protected void getResponseObject(String object) {
    }

    @Override
    protected void getResponseArray(String jsonArray) {
        if (jsonArray != null) {
            try {
                parseDetails(jsonArray);
            } catch (JSONException e) {
                this.noView.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplevideos);
        if (getIntent().hasExtra(Const.TAG_DETAIL)) {
            this.supportPrivateVideo = (SupportPrivateVideo) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        }
        setActionbar();
        initUi();
        this.allSampleVideos = new ArrayList();
        initListner();
        setRecyclerView();
        if (isNetworkAvailable()) {
            getWebService();
        } else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }
    }

    private void setActionbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (this.supportPrivateVideo != null) {
            getSupportActionBar().setTitle(this.supportPrivateVideo.getSubjectName());
        }
    }

    private void initUi() {
        this.noView = (TextView) findViewById(R.id.noView);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.studymaterials);
        this.mRecyclerView.setHasFixedSize(true);
        this.mLayoutManager = new GridLayoutManager(this, 2);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
    }

    private void setRecyclerView() {
        this.adapter = new VideoAdapter(this.allSampleVideos, this);
        this.mRecyclerView.setAdapter(this.adapter);
    }

    private void initListner() {
        ItemClickSupport.addTo(this.mRecyclerView).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                gotoDetail(adapter.getObject(position));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoDetail(SupportVideos video) {
        Intent i = new Intent(this, VideoShowingActivity.class);
        i.putExtra(Const.TAG_DETAIL, (Serializable) video);
        startActivity(i);
    }

    private void getWebService() {
        Map<String, String> params = new HashMap();
        params.put("type", "video");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));
        params.put("subjectID", this.supportPrivateVideo.getSubjectID());
        startWebServiceJsonArray(Const.ParentURL, params, 1);
    }

    private void parseDetails(String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String thumb_url = jsonObject.getString("VideoThumbPath");
            String video_name = jsonObject.getString("VideoName");
            String video_thumb = jsonObject.getString("VideoThumb");
            String video_url = jsonObject.getString("mobile_url");
            String video_subject = jsonObject.getString("SubjectName");
            SupportVideos video = new SupportVideos();
            video.setVideoName(video_name);
            video.setVideoThumb(video_thumb);
            video.setVideoThumbUrl(thumb_url);
            video.setVideoUrl(video_url);
            video.setSubjectNAme(video_subject);
            this.allSampleVideos.add(video);
        }
        if (this.allSampleVideos.size() == 0) {
            this.noView.setVisibility(View.VISIBLE);
        }
        this.adapter.notifyDataSetChanged();
    }
}
