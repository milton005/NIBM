package com.nibmglobal.nibm.ui.Freeprospectus;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 8/11/2016.
 */
public class Freeprospectusactivity extends WebServiceActivity {
    private EditText name;
    private EditText email;
    private EditText phone;
    private EditText housename;
    private  EditText houseno;
    private EditText street;
    private  EditText location;
    private  EditText postOffice;
    private  EditText cityname;
    private  EditText pincode;
    private  EditText state;
    String uname,uemail,uphone,uhousename,uhouseno,ustreet,ulocation,upostoffice,ucityname,upincode,ustate;
    Button Submit;
    private  Toolbar toolbar;
    @Override
    protected void getResponseObject(String object) {
        if (object != null)

            try {
                ParseDetails(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }


    }

    private void ParseDetails(String object)throws JSONException {
        JSONObject jsonobject=new JSONObject(object);
        if(jsonobject.has("status")) {
            String status = jsonobject.getString("status");
            if (status.equals("true")) {
                showSnackBar("Application Submitted Successfully", true);
            }
            else {
                showSnackBar("Already Submitted",false);
            }
        }
        else
        {
            showSnackBar("Missing Required Parameters",false);
        }
    }


    @Override
    protected void getResponseArray(String jsonArray) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freeprospectus_activity);
        initui();
        setupActionbar();
        setupui();


    }

    private void setupui() {
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable())
                    validateForm();

            }
        });
    }

    private void validateForm() {

        if (checkName()&&checkEmail() && CheckHousename() && checkhouseno() && checkstreet()&&checklocation()&&check_postoffice()&&
               checkcity() && checkpin()&& checkstate()&&checkphone())
            submitform();
    }

    private boolean checkphone() {
        uphone=phone.getText().toString().trim();
        if(!uphone.isEmpty())
        {
            return true;
        }
        else
        {
            showSnackBar("Please Enter phone number",false);
            return false;
        }
    }


    private void submitform() {
        Map<String, String> params = new HashMap<String, String>();
       params.put("type","freeprospectus");
        params.put("name",uname);
        params.put("hname",uhousename);
        params.put("hnumber",uhouseno);
        params.put("street",ustreet);
        params.put("location",ulocation);
        params.put("post_office",upostoffice);
        params.put("city",ucityname);
        params.put("pin",upincode);
        params.put("state",ustate);
        params.put("mail",uemail);
        params.put("contact",uphone);
        startWebServiceJsonObject(Const.ParentURL,params,Const.POST);


    }



    private boolean checkstate() {
        ustate=state.getText().toString().trim();
        if (!ustate.isEmpty())
        {
            return true;
        }
        else
        {
            showSnackBar("Please enter your state",false);
            return false;
        }
    }

    private boolean checkpin() {
        upincode=pincode.getText().toString().trim();
        if (!upincode.isEmpty())
        {
            return true;
        }
        else
        {
            showSnackBar("Please enter your pin code",false);
            return false;
        }
    }

    private boolean checkcity() {
        ucityname=cityname.getText().toString().trim();
        if (!ucityname.isEmpty())
        {
            return true;
        }
        else
        {
            showSnackBar("Please enter City name",false);
            return false;
        }
    }

    private boolean check_postoffice() {
        upostoffice=postOffice.getText().toString().trim();
        if (!upostoffice.isEmpty())
        {
            return true;
        }
        else
        {
            showSnackBar("Please enter your post office",false);
            return false;
        }
    }

    private boolean checklocation() {
        ulocation=location.getText().toString().trim();
        if (!ulocation.isEmpty())
        {
            return true;
        }
        else
        {
            showSnackBar("Please enter location",false);
            return false;
        }
    }

    private boolean checkstreet() {
        ustreet=street.getText().toString().trim();
        if(!ustreet.isEmpty())
        {
            return true;
        }
        else
        {
            showSnackBar("Plese enter Street/lane name",false);
            return false;
        }
    }

    private boolean checkhouseno() {
        uhouseno=houseno.getText().toString().trim();
        if(!uhouseno.isEmpty())
        {
            return true;
        }
        else{
            showSnackBar("Please enter house no",false);
            return false;
        }

    }

    private boolean CheckHousename() {
        uhousename=housename.getText().toString().trim();
        if(!uhousename.isEmpty())
        {
            return  true;
        }
        else
        {
            showSnackBar("Please enter House name",false);
            return false;
        }
    }

    private boolean checkName() {
        this.uname=this.name.getText().toString().trim();
        if(!this.uname.isEmpty()) {
            return true;
        }
        else {
            showSnackBar("please enter name", false);
            return false;
        }

    }

    private boolean checkEmail() {

        uemail=email.getText().toString().trim();
        if (!uemail.isEmpty()) {
            if (isValidEmail(uemail)) {
                return true;

            } else {
                showSnackBar("Please enter valid Email", false);
                return false;
            }
        }
            else {
            showSnackBar("Please enter Email Address",false);
            return false;
        }



    }


    private void setupActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Free Prospectus");
    }

    private void initui() {
        name= (EditText) findViewById(R.id.prosname);
        email= (EditText) findViewById(R.id.prosemail);
        phone= (EditText) findViewById(R.id.ProsPhone);
        housename= (EditText) findViewById(R.id.proshousenme);
        houseno= (EditText) findViewById(R.id.proshouseno);
        street= (EditText) findViewById(R.id.prosstreet);
        location= (EditText) findViewById(R.id.proslocation);
        postOffice= (EditText) findViewById(R.id.prospostoffice);
        cityname= (EditText) findViewById(R.id.proscity);
        pincode= (EditText) findViewById(R.id.prospin);
        state= (EditText) findViewById(R.id.prosstate);
        Submit= (Button) findViewById(R.id.btn_submit_pros);

    }
}
