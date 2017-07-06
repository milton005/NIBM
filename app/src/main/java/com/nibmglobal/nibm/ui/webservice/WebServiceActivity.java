package com.nibmglobal.nibm.ui.webservice;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.app.Nibm;
import com.nibmglobal.nibm.ui.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 11/13/15.
 */
public abstract class WebServiceActivity extends BaseActivity {

    private String TAG = "webservice";
    private int urlMethod;

    protected abstract void getResponseObject(String object);
    protected  abstract void getResponseArray(String jsonArray);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void startWebServiceJsonObject(String url, final Map<String, String> params, int method) {
        showProgress();

        setMethod(method);

        startWebServiceObject(url, params, urlMethod);
    }


    protected void startWebServiceJsonArray(String url, final Map<String, String> params, int method) {

        showProgress();
        setMethod(method);
        getArrayResponse(url, params, urlMethod);
    }

    private void setMethod(int method) {
        switch (method) {
            case Const.POST:
                this.urlMethod = Method.POST;
                break;
            case Const.GET:
                this.urlMethod = Method.GET;
                break;
            default:
                break;
        }
    }


    private void startWebServiceObject(String url, final Map<String, String> params, int method) {

        StringRequest jsonObjReq = new StringRequest(method, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
//                        Log.d(TAG, response.toString());
                        hideProgress();
                        getResponseObject(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgress();
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

    private void startWebServiceObject(String url, int method) {


        StringRequest jsonObjReq = new StringRequest(method, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                hideProgress();
                getResponseObject(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgress();
            }
        });


// Adding request to request queue
        Nibm.getInstance().addToRequestQueue(jsonObjReq);

    }

    private void getArrayResponse(String url, final Map<String, String> params, int method) {

        StringRequest jsonArrayRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                hideProgress();
                getResponseArray(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgress();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        Nibm.getInstance().addToRequestQueue(jsonArrayRequest,"tag");
    }


}
