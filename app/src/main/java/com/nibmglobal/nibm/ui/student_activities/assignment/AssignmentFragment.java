package com.nibmglobal.nibm.ui.student_activities.assignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.nibmglobal.nibm.ui.student_activities.assignment.detail.AssignmentDetailActivity;
import com.nibmglobal.nibm.ui.webservice.WebServiceFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/2/15.
 */
public class AssignmentFragment extends WebServiceFragment {

    public static String TAG = "assignments";

    private RecyclerView recyclerView;
    private ArrayList<SupportAssignment> allAssignments;
    private AssignmentAdapter adapter;
    private IntrfceMain mCallbacks;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (IntrfceMain) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment, null);
        mCallbacks.onActionbarSetTitle("Student Assignment");
        initUi(view);
        initListner();
        allAssignments = new ArrayList<>();
        setRecyclerView();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.assignments);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void initListner() {

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                startActivity(position);

            }
        });

    }

    private void setRecyclerView() {
        adapter = new AssignmentAdapter(getActivity(), allAssignments);
        recyclerView.setAdapter(adapter);
    }


    private void startActivity(int position) {
        Intent i = new Intent(getActivity(), AssignmentDetailActivity.class);
        i.putExtra(Const.TAG_DETAIL, (Serializable) adapter.getObject(position));
        getActivity().startActivity(i);
    }

    private void getStudentAssignments() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "student_assignment");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String assignmentTitle = jsonObject.getString("assignment_title");
            String assignmentType = jsonObject.getString("type");
            String batchYear = jsonObject.getString("batch_year");
            String batchMonth = jsonObject.getString("batch_month");
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
            assignment.setBatchMonth(batchMonth);
            assignment.setCourseName(courseName);
            assignment.setSemsterName(semsterName);
            assignment.setAssignmentDate(assignmentDate);
            assignment.setCompleteion_date(completeion_date);
            assignment.setAssignmentFile(assignmentFile);

            allAssignments.add(assignment);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allAssignments);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        super.onRestoreState(savedInstanceState);
//        allAssignments.clear();
        allAssignments = savedInstanceState.getParcelableArrayList(TAG);
        setRecyclerView();
    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();
        if (isNetworkAvailable()){
            getStudentAssignments();
        }else {
            showSnackBar(getActivity().getResources().getString(R.string.connectivity), false);
        }
    }
}
