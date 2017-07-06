package com.nibmglobal.nibm.ui.videos.samplevideos;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.Util;
import com.nibmglobal.nibm.app.Nibm;
import com.nibmglobal.nibm.ui.download.CourseAdapter;
import com.nibmglobal.nibm.ui.download.InsertCountry;
import com.nibmglobal.nibm.ui.download.InsertCourse;

import java.util.HashMap;
import java.util.Map;

public class Popup extends DialogFragment {
    CourseAdapter adapter;
    SampleVideosActivity context;
    private InsertCountry country;
    private String countryId;
    private String course;
    private String courseID;
    private InsertCourse courses;
    private EditText editTextEmail;
    private EditText editTextEnquiry;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextPlace;
    private String email;
    private String enquiryDetail;
    private String name;
    private String phone;
    private String place;
    private Spinner spinnerCourses;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (SampleVideosActivity) context;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_popup_contact, container, false);
        getDialog().getWindow().requestFeature(1);
        this.courses = new InsertCourse();
        initUI(view);
        setCourseList();
        view.findViewById(R.id.close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.btn_submit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
        return view;
    }

    private void initUI(View view) {
        this.editTextName = (EditText) view.findViewById(R.id.edittextName);
        this.editTextPlace = (EditText) view.findViewById(R.id.edittextPlace);
        this.editTextEmail = (EditText) view.findViewById(R.id.edittextEmail);
        this.editTextEnquiry = (EditText) view.findViewById(R.id.edittextEnquiry);
        this.spinnerCourses = (Spinner) view.findViewById(R.id.spinnerCourses);
        this.editTextPhone = (EditText) view.findViewById(R.id.edittextPhone);
    }

    private void setCourseList() {
        this.adapter = new CourseAdapter(getActivity(), this.courses.getCourses());
        this.spinnerCourses.setAdapter(this.adapter);
    }

    private void validateForm() {
        if (checkName() && checkCourse() && checkPlace() && checkPhone() && checkEmail() && checkEnquiryDetails()) {
            this.context.showProgress();
            submitForm();
        }
    }

    private boolean checkName() {
        this.name = this.editTextName.getText().toString().trim();
        if (!this.name.isEmpty()) {
            return true;
        }
        showSnackBar("Enter name", false);
        return false;
    }

    private boolean checkCourse() {
        this.courseID = String.valueOf(this.spinnerCourses.getSelectedView().getTag(R.id.txt_item));
        if (!this.courseID.contentEquals("-1")) {
            return true;
        }
        showSnackBar("Please Select Course", false);
        return false;
    }

    private boolean checkPlace() {
        this.place = this.editTextPlace.getText().toString().trim();
        if (!this.place.isEmpty()) {
            return true;
        }
        showSnackBar("Enter Place", false);
        return false;
    }

    private boolean checkPhone() {
        this.phone = this.editTextPhone.getText().toString().trim();
        if (!this.phone.isEmpty()) {
            return true;
        }
        showSnackBar("Enter Phone Number", false);
        return false;
    }

    private boolean checkEmail() {
        this.email = this.editTextEmail.getText().toString().trim();
        if (!this.email.isEmpty() && isValidEmail(this.email)) {
            return true;
        }
        showSnackBar("Enter email", false);
        return false;
    }

    private boolean checkEnquiryDetails() {
        this.enquiryDetail = this.editTextEnquiry.getText().toString().trim();
        if (!this.enquiryDetail.isEmpty()) {
            return true;
        }
        showSnackBar("Enter Enquiry details", false);
        return false;
    }

    public boolean isValidEmail(CharSequence target) {
        return Util.getUtils().isValidEmail(target);
    }

    public void showSnackBar(String msg, boolean isSuccess) {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(-1);
        if (isSuccess) {
            view.setBackgroundColor(Color.GREEN);
        } else {
            view.setBackgroundColor(Color.RED);
        }
        snackbar.show();
    }

    private void submitForm() {
        Map<String, String> params = new HashMap();
        params.put("type", "prospectus");
        params.put("name", this.name);
        params.put("course", this.courseID);
        params.put("place", this.place);
        params.put("phone", this.phone);
        params.put("email", this.email);
        params.put("enquiry", this.enquiryDetail);
        params.put("ref", "Android");
        startWebServiceObject(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws Exception {
        dismiss();
    }


    private void startWebServiceObject(String url, final Map<String, String> params, int method) {

        StringRequest jsonObjReq = new StringRequest(method, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                        Log.d(TAG, response.toString());
                context.hideProgress();
                try {
                    parseDetails(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                context.hideProgress();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };


// Adding request to request queue
        Nibm.getInstance().addToRequestQueue(jsonObjReq, "tag");

    }
}
