package com.nibmglobal.nibm.ui.student_activities.assignment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/2/15.
 */
public class StudentAssignments extends WebServiceActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<SupportAssignment> allAssignments;
    AssignmentAdapter adapter;

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
        setContentView(R.layout.fragment_assignment);

//        setActionbar();
        initUi();
        initListner();

        allAssignments = new ArrayList<>();
        setRecyclerView();
    }

    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Student Assignment");
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.assignments);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void initListner() {


    }

    private void setRecyclerView() {
        adapter = new AssignmentAdapter(this, allAssignments);
        recyclerView.setAdapter(adapter);
    }

    private void getStudentAssignments() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "student_assignment");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<=jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String assignmentTitle = jsonObject.getString("assignment_title");
            String assignmentType = jsonObject.getString("type");
            String batchYear = jsonObject.getString("batch_year");
            String courseName = jsonObject.getString("course_name");
            String semsterName = jsonObject.getString("semester_name");
            String assignmentDate = jsonObject.getString("assignment_date");
            String completeion_date = jsonObject.getString("completion_date");
            String assignmentFile = jsonObject.getString("assignment_file");

            SupportAssignment assignment = new SupportAssignment();
            assignment.setAssignmentTitle(assignmentTitle);
            if (assignmentType.contentEquals("C")){
                assignment.setAssignmentType("Case Study");
            }else if (assignmentType.contentEquals("A")) {
                assignment.setAssignmentType("Assignment");
            }
            assignment.setBatchYear(batchYear);
            assignment.setCourseName(courseName);
            assignment.setSemsterName(semsterName);
            assignment.setAssignmentDate(assignmentDate);
            assignment.setCompleteion_date(completeion_date);
            assignment.setAssignmentFile(assignmentFile);

            allAssignments.add(assignment);
        }
    }
}
