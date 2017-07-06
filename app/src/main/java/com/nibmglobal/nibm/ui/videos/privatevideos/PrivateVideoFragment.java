package com.nibmglobal.nibm.ui.videos.privatevideos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.ui.main.IntrfceMain;
import com.nibmglobal.nibm.ui.webservice.WebServiceFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/10/15.
 */
public class PrivateVideoFragment extends WebServiceFragment {

    public static String TAG = "private_video";

    private ArrayList<SupportPrivateVideo> allSampleVideos;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private PrivateVideoAdapter adapter;
    IntrfceMain mCallbacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (IntrfceMain) context;
    }

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private_videos, null);
        mCallbacks.onActionbarSetTitle("Videos");
        initUi(view);
        initListner();
        allSampleVideos = new ArrayList<>();
        setRecylerView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initUi(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.studymaterials);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initListner() {
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                gotoDetail(adapter.getObject(position));
            }
        });
    }

    private void setRecylerView() {
        adapter = new PrivateVideoAdapter(allSampleVideos, getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    private void gotoDetail(SupportPrivateVideo video) {
        Intent i = new Intent(getActivity(), PrivateVideoActivity.class);
        i.putExtra(Const.TAG_DETAIL, (Serializable) video);
        startActivity(i);
    }

    private void loadWebService() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "video-subjects");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));
        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data)throws JSONException{

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i< jsonArray.length(); i++) {
            /*JSONObject jsonObject = jsonArray.getJSONObject(i);

            String thumb_url = jsonObject.getString("VideoThumbPath");
            String video_name = jsonObject.getString("VideoName");
            String video_thumb = jsonObject.getString("VideoThumb");
            String video_url = jsonObject.getString("video_url");

            SupportVideos video = new SupportVideos();
            video.setVideoName(video_name);
            video.setVideoThumb(video_thumb);
            video.setVideoThumbUrl(thumb_url);
            video.setVideoUrl(video_url);

            allSampleVideos.add(video);*/

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String SubjectID = jsonObject.getString("SubjectID");
            String SubjectName = jsonObject.getString("SubjectName");
            SupportPrivateVideo video = new SupportPrivateVideo();
            video.setSubjectName(SubjectName);
            video.setSubjectID(SubjectID);
            this.allSampleVideos.add(video);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allSampleVideos);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        allSampleVideos = savedInstanceState.getParcelableArrayList(TAG);
        setRecylerView();
    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();
        if (isNetworkAvailable()) {
            loadWebService();
        }else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }
    }
}
