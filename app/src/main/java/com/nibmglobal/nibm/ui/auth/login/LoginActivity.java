package com.nibmglobal.nibm.ui.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.main.MainActivity;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 11/26/15.
 */
public class LoginActivity extends WebServiceActivity {

    private TextInputLayout inputLayoutUsername;
    private TextInputLayout inputLayoutPassword;

    private EditText editTextUSername;
    private EditText editTextPassword;

    private Button btnLogin;

    private Toolbar toolbar;

    private String username;
    private String password;

    /*public static void start(Context context) {
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }*/

    @Override
    protected void getResponseObject(String object) {
        if (object == null)
            return;

        showLog(object);
        parseJsonObject(object);

    }

    @Override
    protected void getResponseArray(String jsonArray) {
        if (jsonArray == null)
            return;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setToolbar();
        initUi();
        initListner();
    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");
    }


    private void initUi() {

        inputLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        editTextUSername = (EditText) findViewById(R.id.edittextUserName);
        editTextPassword = (EditText) findViewById(R.id.edittextPassword);

        btnLogin = (Button) findViewById(R.id.btn_login);
    }
    private void initListner() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkAvailable()) {
                    checkValidation();
                } else {
                    showSnackBar("Check your Connectivity", false);
                }

            }
        });
    }

    private void checkValidation() {

        if (checkUsername() && checkPassword()) {
            submitLogin(this.username, this.password);
        }

        /*if (!checkUsername())
            return;

        if (!checkPassword())
            return;*/

//        username = "mskochar";
//        password = "tanveer1";
//        username = "kavitha1980";
//        password = "wDVXzk7E";
//        username = "tabraz";
//        password = "lifeis786";
//        username = "anishn";
//        password = "yfCDabS3";
//        submitLogin(username, password);

    }

    private boolean checkUsername() {

       username = editTextUSername.getText().toString().trim();
        if (username.isEmpty()) {
            editTextUSername.setError("Enter User Name");
            requestFocus(editTextUSername);
            return false;
        }else {
            editTextUSername.setError(null);
            return true;
        }
    }

    private boolean checkPassword() {

        password = editTextPassword.getText().toString().trim();

        if (password.isEmpty()) {
            editTextPassword.setError("Enter password");
            requestFocus(editTextPassword);
            return false;
        }else {
            editTextPassword.setError(null);
            return true;
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private void submitLogin(String usrname, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "login");
        params.put("uname", usrname);
        params.put("pwd", password);

        startWebServiceJsonObject(Const.ParentURL, params, Const.POST);
    }


    private void parseJsonObject(String object) {
        String isSucess = "";
        try {
            JSONObject jsonObject = new JSONObject(object);
            isSucess = jsonObject.getString("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            if (isSucess.contentEquals(this.username)) {

                savePref(Const.TAG_USERNAME, username);
                savePref(Const.TAG_ISLOGGED_IN, true);

                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
//                startActivity(new Intent(this, MainActivity.class));
//                MainActivity.start(this);
                finish();

            }else {
                showSnackBar("Login Error", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
