package com.nibmglobal.nibm.ui.profile.batch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.Util;
import com.nibmglobal.nibm.ui.main.IntrfceMain;
import com.nibmglobal.nibm.ui.profile.ProfileAdapter;
import com.nibmglobal.nibm.ui.webservice.WebServiceFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mindlabs on 12/2/15.
 */
public class BatchProfileFragment extends WebServiceFragment {

    public static String TAG = "batchprofile";

    private ArrayList<String> profiles;
    private RecyclerView recylerView;
    private BatchProfileAdapter adapter;
    private IntrfceMain mCallbacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (IntrfceMain) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, null);

        mCallbacks.onActionbarSetTitle("Batch Profile");
        initUi(v);

        profiles = new ArrayList<>();
        setRecylerView();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initUi(View view){
        recylerView = (RecyclerView) view.findViewById(R.id.locationList);
        recylerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(llm);
    }

private void setRecylerView() {
    adapter = new BatchProfileAdapter(getActivity(), profiles, getProfileHeading());
    recylerView.setAdapter(adapter);
}

    private List<String> getProfileHeading() {
        String[] itemsArray = getResources().getStringArray(R.array.batchprofile_items_array);
        return Arrays.asList(itemsArray);
    }

    void getProfile(String usrname) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "studDetails");
        params.put("uname", usrname);

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    void parseDetails(String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String photo = jsonObject.getString("photo");
            String institute = jsonObject.getString("institute");
            String course = jsonObject.getString("course");
            String batchYear = jsonObject.getString("batch_year");
            String batchMonth = jsonObject.getString("batch_month");
            String enrollmentNo = jsonObject.getString("enrollment_no");
            String examRegNo = jsonObject.getString("exam_registration_no");
            String enquiryDate = jsonObject.getString("enquiry_date");
            String enrollmentDate = jsonObject.getString("enrollment_date");
            String paymentType = jsonObject.getString("Payment_type");
            String paymentMode = jsonObject.getString("payment_mode");
            String electives = jsonObject.getString("electives");
            String photoUrl = jsonObject.getString("photo_url");

            profiles.add(institute);
            profiles.add(course);
            profiles.add(batchYear);
            profiles.add(Util.getUtils().getMonth(Integer.parseInt(batchMonth)));
            profiles.add(examRegNo);
            profiles.add(enrollmentNo);
            profiles.add(enrollmentDate);
            profiles.add(enquiryDate);
            profiles.add(paymentType);
            profiles.add(paymentMode);
            profiles.add(electives);

            adapter.notifyDataSetChanged();


        }
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putStringArrayList(TAG, profiles);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        profiles = savedInstanceState.getStringArrayList(TAG);
        setRecylerView();

    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();
        if (isNetworkAvailable()){
            getProfile((String) getPref(Const.TAG_USERNAME));
        }else {
            showSnackBar(getActivity().getResources().getString(R.string.connectivity), false);
        }
    }
}
