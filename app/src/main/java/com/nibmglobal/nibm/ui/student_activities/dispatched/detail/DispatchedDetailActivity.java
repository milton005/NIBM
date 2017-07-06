package com.nibmglobal.nibm.ui.student_activities.dispatched.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.student_activities.dispatched.SubSupportDispatchedMaterial;
import com.nibmglobal.nibm.ui.student_activities.dispatched.SupportDispatchedMaterials;

import java.util.ArrayList;

/**
 * Created by mindlabs on 12/4/15.
 */
public class DispatchedDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SupportDispatchedMaterials object;
    private ArrayList<SubSupportDispatchedMaterial> allSemester;
    private DispatchedDetailAdapter adapter;

    private TextView textViewId;
    private TextView textViewDispatchedMedium;
    private TextView textViewDispatchedDate;
    private TextView textViewIssueStatus;
    private TextView textViewComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatched_detail);

        allSemester = new ArrayList<>();
        if (getIntent().hasExtra(Const.TAG_DETAIL)){
            object = (SupportDispatchedMaterials) getIntent().getSerializableExtra(Const.TAG_DETAIL);
            allSemester = getIntent().getParcelableArrayListExtra("hh");
//            allSemester.addAll(object.getSemesters());
        }
        setActionbar();
        initUi();
        setDetails();
        setRecyclerView();


    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dispatched Study Materials");
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.studymaterials);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        textViewId = (TextView) findViewById(R.id.textDispatchedId);
        textViewDispatchedMedium = (TextView) findViewById(R.id.textDispatchedMedium);
        textViewDispatchedDate = (TextView) findViewById(R.id.textDispatchedDate);
        textViewIssueStatus = (TextView) findViewById(R.id.textDispatchedIsssueStatus);
        textViewComments = (TextView) findViewById(R.id.textDispatchedComments);
    }


    private void setDetails() {
        textViewId.setText(object.getId());
        textViewDispatchedMedium.setText(object.getDispatchMedium());
        textViewDispatchedDate.setText(object.getDispatchedDate());
        textViewIssueStatus.setText(object.getIssueStatus());
        textViewComments.setText(object.getComments());
    }

    private void setRecyclerView() {
        adapter = new DispatchedDetailAdapter(this, allSemester);
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
}
