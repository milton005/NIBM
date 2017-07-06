package com.nibmglobal.nibm.ui.exam;

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
import com.nibmglobal.nibm.Utilities.Util;
import com.nibmglobal.nibm.ui.exam.detail.ExamScheduleDetailActivity;
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
 * Created by mindlabs on 12/2/15.
 */
public class ExamFragment extends WebServiceFragment {
    public static String TAG = "exam";

    private RecyclerView recyclerView;
    private ArrayList<SupportExam> allExamlist;
    private ExamAdapter adapter;
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
        View v = inflater.inflate(R.layout.fragment_exam, null);
        mCallbacks.onActionbarSetTitle("Exam Schedule");
        initUi(v);
        initListner();
        allExamlist = new ArrayList<>();
        setRecyclerView();
        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.studymaterials);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void initListner() {

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent i = new Intent(getActivity(), ExamScheduleDetailActivity.class);
                i.putExtra(Const.TAG_DETAIL, (Serializable) adapter.getObject(position));
                getActivity().startActivity(i);
            }
        });
    }

    private void setRecyclerView() {
        adapter = new ExamAdapter(getActivity(), allExamlist);
        recyclerView.setAdapter(adapter);
    }


    private void getWebServiceData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "exam_schedule");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String course_name = jsonObject.getString("course_name");
            String estatus = jsonObject.getString("estatus");
            String exam_date = jsonObject.getString("exam_date");
            String exam_month = jsonObject.getString("exam_month");
            String exam_to_date = jsonObject.getString("exam_to_date");
            String exam_year = jsonObject.getString("exam_year");
            String mode = jsonObject.getString("mode");
            String semester_name = jsonObject.getString("semester_name");
            String subject_name = jsonObject.getString("subject_name");
            String time_from = jsonObject.getString("time_from");
            String time_to = jsonObject.getString("time_to");


            SupportExam object = new SupportExam();
            object.setCourseName(course_name);
            object.seteStatus(estatus);
            object.setExamDate(exam_date);
            object.setExamMonth(Util.getUtils().getMonth(Integer.parseInt(exam_month)));
            object.setExamToDate(exam_to_date);
            object.setExamYear(exam_year);
            object.setMode(mode);
            object.setSemesterName(semester_name);
            object.setSubjectName(subject_name);
            object.setTimeFrom(time_from);
            object.setTimeTo(time_to);

            allExamlist.add(object);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allExamlist);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        super.onRestoreState(savedInstanceState);
//        allAssignments.clear();
        allExamlist = savedInstanceState.getParcelableArrayList(TAG);
        setRecyclerView();
    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();
        if (isNetworkAvailable()){
            getWebServiceData();
        }else {
            showSnackBar(getActivity().getResources().getString(R.string.connectivity), false);
        }
    }
}
