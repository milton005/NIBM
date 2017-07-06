package com.nibmglobal.nibm.ui.questionpaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class QuestionPaperFragment extends WebServiceFragment {

    public static String TAG = "questionpaper";

    private TextView noView;
    private RecyclerView recyclerView;
    private ArrayList<SupportQuestionPaper> allQuestionPapers;
    private QuestionPaperAdapter adapter;
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
            noView.setVisibility(View.VISIBLE);
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
        View view = inflater.inflate(R.layout.fragment_question_paper, null);
        initUi(view);
        initListner();
        allQuestionPapers = new ArrayList<>();
        setRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allQuestionPapers);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        allQuestionPapers = savedInstanceState.getParcelableArrayList(TAG);
        setRecyclerView();
    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();

        if (isNetworkAvailable()){
            getWebserviceData();
        }else {
            showSnackBar(getActivity().getResources().getString(R.string.connectivity), false);
        }
    }

    private void initUi(View view) {
        noView = (TextView) view.findViewById(R.id.noView);
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
        adapter = new QuestionPaperAdapter(getActivity(), allQuestionPapers);
        recyclerView.setAdapter(adapter);
    }


    private void startActivity(int position) {
        Intent i = new Intent(getActivity(), QuestionPaperDetailActivity.class);
        i.putExtra(Const.TAG_DETAIL, (Serializable) adapter.getObject(position));
        getActivity().startActivity(i);
    }

    private void getWebserviceData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "question_papers");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {
        Log.d("hai", data);
        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String course_name = jsonObject.getString("course_name");
            String endtime = jsonObject.getString("endtime");
            String exam_date = jsonObject.getString("exam_date");
            String exam_paper_file = jsonObject.getString("exam_paper_file");
            String from_date = jsonObject.getString("from_date");
            String id = jsonObject.getString("id");
            String mode = jsonObject.getString("mode");
            String month = jsonObject.getString("month");
            String schedule_name = jsonObject.getString("schedule_name");
            String semester_name = jsonObject.getString("semester_name");
            String subject_name = jsonObject.getString("subject_name");
            String time_from = jsonObject.getString("time_from");
            String time_to = jsonObject.getString("time_to");
            String to_date = jsonObject.getString("to_date");
            String year = jsonObject.getString("year");

            SupportQuestionPaper assignment = new SupportQuestionPaper();
            assignment.setCourseName(course_name);
            assignment.setEndtime(endtime);
            assignment.setExamDate(exam_date);
            assignment.setExamPaperfile(exam_paper_file);
            assignment.setFromDate(from_date);
            assignment.setId(id);
            assignment.setMode(mode);
            assignment.setMonth(month);
            assignment.setScheduleName(schedule_name);
            assignment.setSemsesteName(semester_name);
            assignment.setSubjectName(subject_name);
            assignment.setTimeFrom(time_from);
            assignment.setTimeTo(time_to);
            assignment.setToDate(to_date);
            assignment.setYear(year);

            allQuestionPapers.add(assignment);
        }

        if (allQuestionPapers.size() == 0) {
            noView.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }
}
