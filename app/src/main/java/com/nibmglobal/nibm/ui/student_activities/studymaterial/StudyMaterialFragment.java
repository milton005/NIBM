package com.nibmglobal.nibm.ui.student_activities.studymaterial;

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
import com.nibmglobal.nibm.ui.student_activities.studymaterial.detail.StudyMaterialChaperActivity;
import com.nibmglobal.nibm.ui.webservice.WebServiceFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/2/15.
 */
public class StudyMaterialFragment extends WebServiceFragment {

    public static String TAG = "studymaterial";

    private RecyclerView recyclerView;
    private ArrayList<SupportStudyMaterial> allStudyMaterials;
    StudyMaterialAdapter adapter;
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
        View v = inflater.inflate(R.layout.fragment_studymaterial, null);
        mCallbacks.onActionbarSetTitle("Study Materials");
        initUi(v);
        initListner();
        allStudyMaterials = new ArrayList<>();
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

                Intent i = new Intent(getActivity(), StudyMaterialChaperActivity.class);
                i.putExtra(Const.TAG_DETAIL, adapter.getMaterialId(position));
                getActivity().startActivity(i);
            }
        });
    }

    private void setRecyclerView() {
        adapter = new StudyMaterialAdapter(getActivity(), allStudyMaterials);
        recyclerView.setAdapter(adapter);
    }


    private void getWebServiceData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "study_materials");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String id = jsonObject.getString("id");
            String semester = jsonObject.getString("semister");
            String book = jsonObject.getString("book");
            String name = jsonObject.getString("name");

            SupportStudyMaterial object = new SupportStudyMaterial();
            object.setBook(book);
            object.setSemester(semester);
            object.setId(id);
            object.setName(name);

            allStudyMaterials.add(object);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allStudyMaterials);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        allStudyMaterials = savedInstanceState.getParcelableArrayList(TAG);
        setRecyclerView();
    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();
        if (isNetworkAvailable()) {
            getWebServiceData();
        } else {
            showSnackBar(getActivity().getResources().getString(R.string.connectivity), false);
        }
    }
}
