package com.nibmglobal.nibm.ui.course;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/9/15.
 */
public class CourseActivity extends WebServiceActivity {


    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<SupportCourse> courses;
    CourseAdapter adapter;

    /*public static void start(Context context) {
        Intent i = new Intent(context, CourseActivity.class);
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
        setContentView(R.layout.activity_course);

        setActionbar();
        initUi();
        initListner();

        courses = new ArrayList<>();

        setRecyclerView();

        if (isNetworkAvailable()) {
            getWebService();
        }else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }

        ItemClickSupport.addTo(this.recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                CourseDetail.start(CourseActivity.this, CourseActivity.this.adapter.getObject(position));
            }
        });
    }


    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Courses");
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.studymaterials);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void initListner() {


    }

    private void setRecyclerView() {
        adapter = new CourseAdapter(this, courses);
        recyclerView.setAdapter(adapter);
    }



    private void getWebService() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "course");
        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i< jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String course_name = jsonObject.getString("course_name");
            String courseDetail = jsonObject.getString("course_details");

            SupportCourse course = new SupportCourse();
            course.setCourseTitle(course_name);
            course.setCourseDetails(courseDetail);
            courses.add(course);
        }

        adapter.notifyDataSetChanged();
    }
}
