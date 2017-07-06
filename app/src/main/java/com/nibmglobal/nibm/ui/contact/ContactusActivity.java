package com.nibmglobal.nibm.ui.contact;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.download.CountryAdapter;
import com.nibmglobal.nibm.ui.download.CourseAdapter;
import com.nibmglobal.nibm.ui.download.InsertCountry;
import com.nibmglobal.nibm.ui.download.InsertCourse;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/9/15.
 */
public class ContactusActivity extends WebServiceActivity {

    CourseAdapter adapter;
    CountryAdapter adapterCountry;
    private Button buttonSubmit;
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
    private Toolbar toolbar;
//    private ArrayList<SupportContact> courses;


    /*public static void start(Context context) {
        Intent i = new Intent(context, ContactusActivity.class);
        context.startActivity(i);
    }*/

    @Override
    protected void getResponseObject(String object) {
        if (object != null) {
            try {
                parseDetails(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void getResponseArray(String jsonArray) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setActionbar();
        this.courses = new InsertCourse();
        this.country = new InsertCountry();
        initUi();
        setCourseList();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContactusActivity.this.isNetworkAvailable()) {
                    ContactusActivity.this.validateForm();
                }
            }
        });
    }

    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Us");
    }

    private void initUi() {
        this.editTextName = (EditText) findViewById(R.id.edittextName);
        this.editTextPlace = (EditText) findViewById(R.id.edittextPlace);
        this.editTextEmail = (EditText) findViewById(R.id.edittextEmail);
        this.editTextEnquiry = (EditText) findViewById(R.id.edittextEnquiry);
        this.spinnerCourses = (Spinner) findViewById(R.id.spinnerCourses);
        this.buttonSubmit = (Button) findViewById(R.id.btn_submit);
        this.editTextPhone = (EditText) findViewById(R.id.edittextPhone);
    }


    private void setCourseList() {
        this.adapter = new CourseAdapter(this, this.courses.getCourses());
        this.spinnerCourses.setAdapter(this.adapter);
    }


    private void validateForm() {
        if (checkName() && checkCourse() && checkPlace() && checkPhone() && checkEmail() && checkEnquiryDetails()) {
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
        startWebServiceJsonObject(Const.ParentURL, params, 1);
    }


    private void parseDetails(String data) throws JSONException {

        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
