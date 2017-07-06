package com.nibmglobal.nibm.ui.profile;

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
import com.nibmglobal.nibm.ui.main.IntrfceMain;
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
public class ProfileFragment extends WebServiceFragment {

    public static String TAG = "profile";

    private ArrayList<String> profiles;
    private Toolbar toolbar;
    private RecyclerView recylerView;
    private ProfileAdapter adapter;
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

        mCallbacks.onActionbarSetTitle("Personal Profile");
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
    adapter = new ProfileAdapter(getActivity(), profiles, getProfileHeading());
    recylerView.setAdapter(adapter);
}

    private List<String> getProfileHeading() {
        String[] itemsArray = getResources().getStringArray(R.array.profile_items_array);
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
            String firstname = jsonObject.getString("first_name");
            String lastname = jsonObject.getString("last_name");
            String dob = jsonObject.getString("date_of_birth");
            String gender = jsonObject.getString("gender");
            String fathername = jsonObject.getString("father_name");
            String email = jsonObject.getString("email");
            String address = jsonObject.getString("address");
            String country = jsonObject.getString("country");
            String state = jsonObject.getString("state");
            String district = jsonObject.getString("district");
            String city = jsonObject.getString("city");
            String pincode = jsonObject.getString("picode");
            String cntryforcommunication = jsonObject.getString("country_for_communication");
            String stateForCommunication = jsonObject.getString("state_for_communication");
            String district_for_communication = jsonObject.getString("district_for_communication");
            String cityForCommunication = jsonObject.getString("city_for_communication");
            String pincodeForCommunication = jsonObject.getString("pincode_for_communication");
            String phone = jsonObject.getString("phone");
            String mobile = jsonObject.getString("mobile");
            String qualification = jsonObject.getString("qualification");
            String employed = jsonObject.getString("employed");
            String organizationname = jsonObject.getString("organisation_name");
            String organizationPlace = jsonObject.getString("organisation_place");
            String jobNature = jsonObject.getString("job_nature");

            String photo = jsonObject.getString("photo");
            String photoUrl = jsonObject.getString("photo_url");

            profiles.add(firstname);
            profiles.add(lastname);
            profiles.add(dob);
            profiles.add(gender);
            profiles.add(fathername);
            profiles.add(country);
            profiles.add(state);
            profiles.add(district);
            profiles.add(city);
            profiles.add(pincode);
            profiles.add(address);
            profiles.add(email);
            profiles.add(phone);
            profiles.add(mobile);
            profiles.add(cntryforcommunication);
            profiles.add(stateForCommunication);
            profiles.add(district_for_communication);
            profiles.add(cityForCommunication);
            profiles.add(pincodeForCommunication);
            profiles.add(qualification);
            profiles.add(employed);
            profiles.add(organizationname);
            profiles.add(organizationPlace);
            profiles.add(jobNature);

            adapter.notifyDataSetChanged();

            /*profiles.add(photo);
            profiles.add(institute);
            profiles.add(course);
            profiles.add(batchYear);
            profiles.add(batchMonth);
            profiles.add(enrollmentNo);
            profiles.add(enrollmentDate);
            profiles.add(paymentType);
            profiles.add(paymentMode);
            profiles.add(electives);*/



            /*SupportProfile profile = new SupportProfile();
            profile.setFirstname(firstname);
            profile.setLastname(lastname);
            profile.setDob(dob);
            profile.setGender(gender);
            profile.setFathername(fathername);
            profile.setEmail(email);
            profile.setAddress(address);
            profile.setCountry(country);
            profile.setState(state);
            profile.setDistrict(district);
            profile.setCity(city);
            profile.setPincode(pincode);
            profile.setCntryforcommunication(cntryforcommunication);
            profile.setStateForCommunication(stateForCommunication);
            profile.setCityForCommunication(cityForCommunication);
            profile.setDistrictForCommunication(district_for_communication);
            profile.setPincodeForCommunication(pincodeForCommunication);
            profile.setPhone(phone);
            profile.setMobile(mobile);
            profile.setQualification(qualification);
            profile.setEmployed(employed);
            profile.setOrganizationname(organizationname);
            profile.setOrganizationPlace(organizationPlace);
            profile.setJobNature(jobNature);
            profile.setPhoto(photo);
            profile.setInstitute(institute);
            profile.setCourse(course);
            profile.setBatchYear(batchYear);
            profile.setBatchMonth(batchMonth);
            profile.setEnrollmentNo(enrollmentNo);
            profile.setEnrollmentDate(enrollmentDate);
            profile.setExamRegNo(examRegNo);
            profile.setEnquiryDate(enquiryDate);
            profile.setPaymentType(paymentType);
            profile.setPaymentMode(paymentMode);
            profile.setElectives(electives);
            profile.setPhotoUrl(photoUrl);*/


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
