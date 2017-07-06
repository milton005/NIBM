package com.nibmglobal.nibm.ui.videos.samplevideos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.ui.videos.detail.VideoShowingActivity;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/9/15.
 */
public class SampleVideosActivity extends WebServiceActivity {
    private Toolbar toolbar;
    private ArrayList<SupportVideos> allSampleVideos;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private VideoAdapter adapter;

    /*public static void start(Context context) {
        Intent i = new Intent(context, SampleVideosActivity.class);
        context.startActivity(i);
    }*/

    @Override
    protected void getResponseObject(String object) {


    }

    @Override
    protected void getResponseArray(String jsonArray) {
        if (jsonArray == null)
            return;

        try {
            parseDetails(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplevideos);

        setActionbar();
        initUi();
        allSampleVideos = new ArrayList<>();
        initListner();
        setRecyclerView();
        new Popup().show(getSupportFragmentManager(), "");

        if (isNetworkAvailable()) {
            getWebService();
        }else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }
    }

    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sample Videos");
    }

    private void initUi() {
        mRecyclerView = (RecyclerView) findViewById(R.id.studymaterials);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void setRecyclerView() {
        adapter = new VideoAdapter(allSampleVideos, this);
        mRecyclerView.setAdapter(adapter);
    }

    private void initListner() {
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                gotoDetail(adapter.getObject(position));
            }
        });
    }

    private void gotoDetail(SupportVideos video) {
        Intent i = new Intent(this, VideoShowingActivity.class);
        i.putExtra(Const.TAG_DETAIL, (Serializable) video);
        startActivity(i);
    }

    private void getWebService() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "SampleVideos");
        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i< jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String thumb_url = jsonObject.getString("thumb_url");
            String video_name = jsonObject.getString("video_name");
            String video_thumb = jsonObject.getString("thumb_url");
            String video_url = jsonObject.getString("video_url");

            SupportVideos video = new SupportVideos();
            video.setVideoName(video_name);
            video.setVideoThumb(video_thumb);
            video.setVideoThumbUrl(thumb_url);
            video.setVideoUrl(video_url);

            allSampleVideos.add(video);
        }

        adapter.notifyDataSetChanged();
    }

}
