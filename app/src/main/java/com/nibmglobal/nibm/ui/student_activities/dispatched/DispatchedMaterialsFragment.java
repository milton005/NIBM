package com.nibmglobal.nibm.ui.student_activities.dispatched;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.nibmglobal.nibm.ui.student_activities.dispatched.detail.DispatchedDetailActivity;
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
public class DispatchedMaterialsFragment extends WebServiceFragment {
    public static String TAG = "dispatchedmaterial";

    private RecyclerView recyclerView;
    private ArrayList<SupportDispatchedMaterials> allDispatchedMaterials;
    private DispatchedMaterialAdapter adapter;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dispatched_material, null);
        mCallbacks.onActionbarSetTitle("Dispatched Study Materials");
        initUi(v);
        initListner();
        allDispatchedMaterials = new ArrayList<>();
        setRecyclerView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.studymaterials);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


    }

    private void initListner() {

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent i = new Intent(getActivity(), DispatchedDetailActivity.class);
                i.putExtra(Const.TAG_DETAIL, (Serializable) adapter.getObject(position));
                i.putParcelableArrayListExtra("hh", adapter.getObject(position).getSemesters());
                getActivity().startActivity(i);
            }
        });
    }

    private void setRecyclerView() {
        adapter = new DispatchedMaterialAdapter(getActivity(), allDispatchedMaterials);
        recyclerView.setAdapter(adapter);
    }


    private void getWebServiceData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "dispatched_study_materials");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        ArrayList<SubSupportDispatchedMaterial> semesters;

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            semesters = new ArrayList<>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String issue_status = jsonObject.getString("issue_status");
            String id = jsonObject.getString("id");
            String dispatch_medium = jsonObject.getString("dispatch_medium");
            String dispatched_date = jsonObject.getString("dispatched_date");
            String comments = jsonObject.getString("comments");


            JSONArray subJsonArray = jsonObject.getJSONArray("materials");
            for (int j=0;j<subJsonArray.length(); j++) {

                JSONObject subJsonObject = subJsonArray.getJSONObject(j);
                String semname = subJsonObject.getString("sem_name");
                String name = subJsonObject.getString("name");

                SubSupportDispatchedMaterial subSupportDispatchedMaterial = new SubSupportDispatchedMaterial();
                subSupportDispatchedMaterial.setName(name);
                subSupportDispatchedMaterial.setSemName(semname);
                semesters.add(subSupportDispatchedMaterial);
            }

            SupportDispatchedMaterials object = new SupportDispatchedMaterials();
            object.setId(id);
            object.setComments(comments);
            object.setDispatchedDate(dispatched_date);
            object.setDispatchMedium(dispatch_medium);
            object.setIssueStatus(issue_status);
            object.setSemesters(semesters);

            allDispatchedMaterials.add(object);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allDispatchedMaterials);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        super.onRestoreState(savedInstanceState);
//        allAssignments.clear();
        allDispatchedMaterials = savedInstanceState.getParcelableArrayList(TAG);
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
