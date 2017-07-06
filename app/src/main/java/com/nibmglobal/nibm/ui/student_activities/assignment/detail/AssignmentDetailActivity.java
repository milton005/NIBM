package com.nibmglobal.nibm.ui.student_activities.assignment.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.student_activities.DownloadListner;
import com.nibmglobal.nibm.ui.student_activities.assignment.SupportAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mindlabs on 12/3/15.
 */
public class AssignmentDetailActivity extends BaseActivity implements DownloadListner {

    private SupportAssignment object;

    private ArrayList<String> listValues;
    private AssignmentDetailAdapter adapter;

    private Toolbar toolbar;
    private RecyclerView recylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail_);

        if (getIntent().hasExtra(Const.TAG_DETAIL)){
            object = (SupportAssignment) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        }
        setActionbar();
        initUi();
        listValues = new ArrayList<>();
        adapter = new AssignmentDetailAdapter(this, listValues, getStringFileDatas());
        adapter.setCallbacks();
        recylerView.setAdapter(adapter);
        addListValues();


    }

    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Student Assignment");
    }

    private void initUi() {

        recylerView = (RecyclerView) findViewById(R.id.detailRecyler);
        recylerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(llm);

    }

    private List<String> getStringFileDatas() {
        String[] itemsArray = getResources().getStringArray(R.array.assignmentdetail_items_array);
        return Arrays.asList(itemsArray);
    }

    private void addListValues() {

        if (object != null) {
            listValues.add(object.getAssignmentTitle());
            listValues.add(object.getAssignmentType());
            listValues.add(object.getCourseName());
            listValues.add(object.getSemsterName());
            listValues.add(object.getBatchYear());
            listValues.add(object.getBatchMonth());
            listValues.add(object.getAssignmentDate());
            listValues.add(object.getCompleteion_date());
            listValues.add(object.getAssignmentFile());
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
        setupDownloadingProcedure(data, "Assignments", Const.FORMAT_DOC);
    }
}
