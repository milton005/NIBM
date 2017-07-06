package com.nibmglobal.nibm.ui.profile;

import android.content.Context;
import android.content.Intent;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mindlabs on 12/1/15.
 */
public class ProfileActivity extends WebServiceActivity {

    private ArrayList<String> profiles;
    private Toolbar toolbar;
    private RecyclerView recylerView;
    private ProfileAdapter adapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ProfileActivity.class));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
//        setActionbar();
        initUi();

        profiles = new ArrayList<>();
        adapter = new ProfileAdapter(this, profiles, getProfileHeading());
        recylerView.setAdapter(adapter);

        if (isNetworkAvailable())
            getProfile((String) getPref(Const.TAG_USERNAME));
    }


    private void initUi(){
        recylerView = (RecyclerView) findViewById(R.id.locationList);
        recylerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(llm);
    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Personal Profile");
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
        for (int i = 0; i<= jsonArray.length(); i++) {
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
}
