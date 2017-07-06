package com.nibmglobal.nibm.ui.student_activities.studymaterial.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
 * Created by mindlabs on 12/3/15.
 */
public class StudyMaterialChaperActivity extends WebServiceActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<SupportStudyMaterialChapter> allChapters;
    private StudyMaterialChapterAdapter adapter;

    private String materialId;
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
        setContentView(R.layout.activity_study_material_chapter);

        if (getIntent().hasExtra(Const.TAG_DETAIL)) {
            materialId = getIntent().getStringExtra(Const.TAG_DETAIL);
        }
        setActionbar();
        initUi();
        initListner();
        allChapters = new ArrayList<>();
        setRecyclerView();

        if (isNetworkAvailable()){
            getWebServiceData();
        }else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }
    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chapters");
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.studymaterials);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void initListner() {

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                /*Intent i = new Intent(StudyMaterialChaperActivity.this, ChapterDetailActivity.class);
                i.putExtra(Const.TAG_DETAIL, adapter.getObject(position));
                startActivity(i);*/

                if (isNetworkAvailable()) {
                    setupDownloadingProcedure(adapter.getObject(position).getBookFile(), "Study Material", Const.FORMAT_PDF);
                }
//                Util.getUtils().showWebview(adapter.getObject(position).getBookFile(), StudyMaterialChaperActivity.this);
            }
        });
    }

    private void setRecyclerView() {
        adapter = new StudyMaterialChapterAdapter(this, allChapters);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getWebServiceData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "chapter_names");
        params.put("material_id", materialId);
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String book_file = jsonObject.getString("book_file");
            String lesson_name = jsonObject.getString("lesson_name");

            SupportStudyMaterialChapter object = new SupportStudyMaterialChapter();
            object.setBookFile(book_file);
            object.setLessonName(lesson_name);

            allChapters.add(object);
        }
        adapter.notifyDataSetChanged();
    }


}
