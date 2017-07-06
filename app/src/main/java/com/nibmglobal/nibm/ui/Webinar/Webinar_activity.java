package com.nibmglobal.nibm.ui.Webinar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.ui.videos.samplevideos.SupportVideos;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 8/11/2016.
 */
public class Webinar_activity extends WebServiceActivity {
    final String youTubeUrlRegEx = "^(https?)?(://)?(www.)?(m.)?((youtube.com)|(youtu.be))/";
    final String[] videoIdRegex = { "\\?vi?=([^&]*)","watch\\?.*v=([^&]*)", "(?:embed|vi?)/([^/?]*)", "^([A-Za-z0-9\\-]*)"};
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    public String f;
    Webinar_adapter adapter;
    private ArrayList<Support_webinar>list;
String link;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void getResponseObject(String object) {


    }



    @Override
    protected void getResponseArray(String jsonArray) {



        try {
            parseDetails(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseDetails(String jsonArray) throws JSONException{
        JSONArray jsonarray = new JSONArray(jsonArray);

        for (int i=0; i< jsonarray.length(); i++) {
            JSONObject jsonObject=jsonarray.getJSONObject(i);
            String meeting_name = jsonObject.getString("meetingName");
            String youtube_id = jsonObject.getString("youtube_id");
            String description=jsonObject.getString("description");
            Support_webinar web = new Support_webinar();
            web.setname(meeting_name);
            web.setYoutubeId(youtube_id);
            web.setDescription(description);
            list.add(web);
        }

        adapter.notifyDataSetChanged();
    }

//    public String extractYTId(String ytUrl) {
//
//
//
////        <---->
//        String video_id = "";
//
//        try {
//            if(ytUrl!= null && ytUrl.trim().length() > 0 && ytUrl.startsWith("http")) {
//             String expression = "^.*((youtu.be" + "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
////                String expression = "^.*(?:youtu.be\\/|v\\/|e\\/|u\\/\\w+\\/|embed\\/|v=)([^#\\&\\?]*).*";
//                CharSequence input = ytUrl;
//                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
//                Matcher matcher = pattern.matcher(input);
//                if(matcher.matches()) {
//                    String groupIndex1 = matcher.group(7);
//                    if(groupIndex1 != null && groupIndex1.length() == 11)
//                        video_id = groupIndex1;
//                }
//            }
//        } catch(Exception e) {
//            Log.e("YoutubeActivity", "extractYTId " + e.getMessage());
//        }
//
//        return video_id;
////        <---->
////        String vId = null;
////        Pattern pattern = Pattern.compile(
////                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
////                Pattern.CASE_INSENSITIVE);
////        Matcher matcher = pattern.matcher(ytUrl);
////        if (matcher.matches()){
////            vId = matcher.group(1);
////        }
////        return vId;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webinar_activity);
        setActionbar();
        initUi();
        initListner();
//        link="https://www.youtube.com/embed/PmmQOWmnhLg";
//      link="http:\\/\\/www.youtube.com\\/embed\\/XFpC9gcBvvo?wmode=opaque\\n\\t<http:\\/\\/www.youtube.com\\/embed\\/XFpC9gcBvvo?wmode=opaque&modestbranding=1&autoplay=1&controls=0&fs=0&iv_load_policy=3&rel=0&showinfo=0&theme=light&color=white&autohide=0&disablekb=1>\\n\\t&modestbranding=1&autoplay=1&controls=0&fs=0&iv_load_policy=3&rel=0&showinfo=0&theme=light&color=white&autohide=0&disablekb=1";
        list=new ArrayList<>();
        setRecyclerView();
        if (isNetworkAvailable()) {
            getWebService();
        }else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }
//String ytid=extractYTId(link);
//        showSnackBar(ytid,true);
    }

    private void getWebService() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type","webinar-full-list");
        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);

    }

    private void setRecyclerView() {
        adapter=new Webinar_adapter(this,list);
        recyclerView.setAdapter(adapter);


    }

    private void initListner() {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                gotoDetail(adapter.getObject(position));
            }
        });

    }

    private void gotoDetail(Support_webinar object) {
//        Intent i= new Intent(this,Webinar_videoshowing_activity.class);
//        startActivity(i);
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.Recycleview_imageActivity);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Webinar");

    }
}
