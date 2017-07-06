package com.nibmglobal.nibm.ui.exam.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.exam.SupportExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mindlabs on 12/4/15.
 */
public class ExamScheduleDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SupportExam object;
    private ArrayList<String> list;
    private ExamScheduleDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_schedule_detail_);

        if (getIntent().hasExtra(Const.TAG_DETAIL)) {
            object = (SupportExam) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        }
        setActionbar();
        initUi();
        list = new ArrayList<>();
        setRecyclerView();
        addListValues();
    }

    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (object != null)
        getSupportActionBar().setTitle(object.getSubjectName());
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.detailRecyler);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

    }
    private void setRecyclerView() {
        adapter = new ExamScheduleDetailAdapter(this, list, getStringFileDatas());
        recyclerView.setAdapter(adapter);
    }

    private List<String> getStringFileDatas() {
        String[] itemsArray = getResources().getStringArray(R.array.exam_schedule_items_array);
        return Arrays.asList(itemsArray);
    }

    private void addListValues() {

        if (object != null) {
            list.add(object.getSubjectName());
            list.add(object.getCourseName());
            list.add(object.getSemesterName());
            list.add(object.getExamYear());
            list.add(object.getExamMonth());
            list.add(object.getExamDate());
            list.add(object.getExamToDate());
            list.add(object.getTimeFrom());
            list.add(object.getTimeTo());
            list.add(object.getMode());
            list.add(object.geteStatus());
        }


        adapter.notifyDataSetChanged();
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
}
