package com.nibmglobal.nibm.ui.questionpaper;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.student_activities.DownloadListner;
import com.nibmglobal.nibm.ui.student_activities.assignment.detail.AssignmentDetailAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mindlabs on 12/11/15.
 */
public class QuestionPaperDetailActivity extends BaseActivity implements DownloadListner{

    private SupportQuestionPaper object;

    private ArrayList<String> listValues;
    private QuestionPaperDetailAdapter adapter;

    private Toolbar toolbar;
    private RecyclerView recylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionpaper_detail_);

        if (getIntent().hasExtra(Const.TAG_DETAIL)){
            object = (SupportQuestionPaper) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        }
        setActionbar();
        initUi();
        listValues = new ArrayList<>();
        adapter = new QuestionPaperDetailAdapter(this, listValues, getStringFileDatas());
        adapter.setCallbacks();
        recylerView.setAdapter(adapter);
        addListValues();
    }

    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Question Paper");
    }

    private void initUi() {

        recylerView = (RecyclerView) findViewById(R.id.detailRecyler);
        recylerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(llm);

    }

    private List<String> getStringFileDatas() {
        String[] itemsArray = getResources().getStringArray(R.array.questionpapers_items_array);
        return Arrays.asList(itemsArray);
    }

    private void addListValues() {

        if (object != null) {
            listValues.add(object.getId());
            listValues.add(object.getYear());
            listValues.add(object.getMonth());
            listValues.add(object.getMode());
            listValues.add(object.getExamDate());
            listValues.add(object.getTimeFrom());
            listValues.add(object.getTimeTo());
            listValues.add(object.getEndtime());
            listValues.add(object.getFromDate());
            listValues.add(object.getToDate());
            listValues.add(object.getCourseName());
            listValues.add(object.getSubjectName());
            listValues.add(object.getScheduleName());
            listValues.add(object.getSemsesteName());
            listValues.add(object.getExamPaperfile());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDownloadItem(String data) {
        setupDownloadingProcedure(data, "Question_Papers", Const.FORMAT_DOC);
    }
}
