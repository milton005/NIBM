package com.nibmglobal.nibm.ui.aboutus;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.contact.SupportContact;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/9/15.
 */
public class AboutUsActivity extends WebServiceActivity {

    private Toolbar toolbar;
    private WebView webViewAboutUs;


    /*public static void start(Context context) {
        Intent i = new Intent(context, AboutUsActivity.class);
        context.startActivity(i);
    }*/

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
        setContentView(R.layout.activity_aboutus);
        setActionbar();
        initUi();

        if (isNetworkAvailable()) {
            getWebService();
        } else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }
    }


    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About Us");
    }

    private void initUi() {
        webViewAboutUs = (WebView) findViewById(R.id.textViewContact);
    }


    private void getWebService() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "aboutus");
        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        String contents = "";

        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            contents = jsonObject.getString("contents");

            SupportContact course = new SupportContact();
            course.setContact(contents);
        }
        loadView(contents);

//        textViewContact.setText(Html.fromHtml(contents));

    }

    private void loadView(String string) {
        webViewAboutUs.setVisibility(View.VISIBLE);
        webViewAboutUs.setWebChromeClient(new WebChromeClient());
        webViewAboutUs.getSettings().setJavaScriptEnabled(true);
        webViewAboutUs.loadData(string, "text/html", "utf-8");
    }
}
