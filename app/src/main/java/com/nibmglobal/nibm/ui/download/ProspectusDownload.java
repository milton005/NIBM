package com.nibmglobal.nibm.ui.download;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/1/15.
 */
public class ProspectusDownload extends WebServiceActivity {


    private EditText editTextName;
    private EditText editTextPlace;
    private EditText editTextEmail;
    private EditText editTextEnquiry;
    private Button buttonSubmit;
    private Spinner spinnerCourses;
//    private Spinner spinnerCountrys;
    private EditText editTextPhone;

    private Toolbar toolbar;
    CourseAdapter adapter;
    CountryAdapter adapterCountry;
    private String name;
    private String course;
    private String countryId;
    private String courseID;
    private String place;
    private String email;
    private String phone;
    private String enquiryDetail;
    private InsertCountry country;
    private InsertCourse courses;


    /*public static void start(Context context) {
        context.startActivity(new Intent(context, ProspectusDownload.class));
    }*/
    @Override
    protected void getResponseObject(String object) {
        if (object == null)
            return;

        try {
            parseDetails(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void getResponseArray(String jsonArray) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        setupActionbar();
        courses = new InsertCourse();
        country = new InsertCountry();
        initUI();
        setCourseList();
//        setCountryList();
        initListner();
    }

    private void setupActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Prospectus Download Form");
    }

    private void initUI() {

        editTextName = (EditText) findViewById(R.id.edittextName);
        editTextPlace = (EditText) findViewById(R.id.edittextPlace);
        editTextEmail = (EditText) findViewById(R.id.edittextEmail);
        editTextEnquiry = (EditText) findViewById(R.id.edittextEnquiry);
        spinnerCourses = (Spinner) findViewById(R.id.spinnerCourses);
        buttonSubmit = (Button) findViewById(R.id.btn_submit);
//        spinnerCountrys = (Spinner) findViewById(R.id.spinnerCountry);
        editTextPhone = (EditText) findViewById(R.id.edittextPhone);

    }

    private void setCourseList() {
        adapter = new CourseAdapter(this, courses.getCourses());
        spinnerCourses.setAdapter(adapter);
    }
    /*private void setCountryList() {
        adapterCountry = new CountryAdapter(this, country.getAllCountrys());
        spinnerCountrys.setAdapter(adapterCountry);
    }*/

    private void initListner() {

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable())
                    validateForm();

            }
        });

    }

   /* private List<String> getCourses() {
        String[] itemsArray = getResources().getStringArray(R.array.courses_items_array);
        return Arrays.asList(itemsArray);
    }*/

    private void validateForm() {

        if (!checkName())
            return;

        if (!checkCourse())
            return;

        if (!checkPlace())
            return;

        /*if (!checkCountry())
            return;*/

        if (!checkPhone())
            return;

        if (!checkEmail())
            return;

        if (!checkEnquiryDetails())
            return;

        submitForm();
    }



    private boolean checkName() {

        name = editTextName.getText().toString().trim();
        if (name.isEmpty()) {
            showSnackBar("Enter name", false);
            return false;
        }
        else
            return true;

    }
    private boolean checkCourse() {

        View v = spinnerCourses.getSelectedView();
//        TextView tv_option = (TextView) v.findViewById(R.id.txt_item);
        courseID = String.valueOf(v.getTag(R.id.txt_item));
//        course = tv_option.getText().toString().trim();

        if (courseID.contentEquals("-1")){
//            tv_option.setError("Please Select");
            showSnackBar("Please Select Course", false);
            return false;
        }else
            return true;

    }

    private boolean checkPlace() {

        place = editTextPlace.getText().toString().trim();
        if (place.isEmpty()) {
//            editTextPlace.setError("Enter place");
            showSnackBar("Enter Place", false);
            return false;
        }
        else
            return true;

    }

    /*private boolean checkCountry() {
        View v = spinnerCountrys.getSelectedView();
//        TextView tv_option = (TextView) v.findViewById(R.id.txt_item);
        countryId = String.valueOf(v.getTag(R.id.txt_item));
//        countryId = tv_option.getText().toString().trim();

        if (countryId.contentEquals("-1")){
//            tv_option.setError("Please Select");
            showSnackBar("Please Select Country", false);
            return false;
        }else
            return true;

    }*/

    private boolean checkPhone() {


        phone = editTextPhone.getText().toString().trim();
        if (!phone.isEmpty()) {

            return true;
        }
        else {
            showSnackBar("Enter Phone Number", false);
            return false;
        }
    }

    private boolean checkEmail() {

        email = editTextEmail.getText().toString().trim();
        if ((!email.isEmpty()) && isValidEmail(email)) {

            return true;
        }
        else {
//            editTextPlace.setError("Enter Email");
            showSnackBar("Enter email", false);
            return false;
        }

    }


    private boolean checkEnquiryDetails() {

        enquiryDetail = editTextEnquiry.getText().toString().trim();
        if (enquiryDetail.isEmpty()) {
//            editTextEnquiry.setError("Enter Enquiry details");
            showSnackBar("Enter Enquiry details", false);
            return false;
        }
        else
            return true;

    }

    private void submitForm() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "prospectus");
        params.put("name", name);
        params.put("course", courseID);
        params.put("place", place);
//        params.put("country", countryId);
        params.put("phone", phone);
        params.put("email", email);
        params.put("enquiry", enquiryDetail);
        params.put("ref", "andrprosp");

        startWebServiceJsonObject(Const.ParentURL, params, Const.POST);

//        r showLog(name+"-"+course+"-"+place+"-"+email+"-"+enquiryDetail+"-"+countryId+"-"+phone);
    }

    private void parseDetails(String data) throws JSONException {


        JSONObject jsonObject = new JSONObject(data);
        String pdfToDownload = jsonObject.getString("prospectus");
        if (!pdfToDownload.isEmpty()){
            setupDownloadingProcedure(pdfToDownload, "Prospectus", Const.FORMAT_PDF);
        }

    }
}
