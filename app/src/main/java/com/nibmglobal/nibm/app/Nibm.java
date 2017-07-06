package com.nibmglobal.nibm.app;

import android.app.Application;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.appsflyer.AppsFlyerLib;
import com.nibmglobal.nibm.Utilities.Util;
import com.onesignal.OneSignal;
//import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by mindlabs on 11/26/15.
 */
public class Nibm extends Application {


    public static final String TAG = Nibm.class
            .getSimpleName();

    private static Nibm instance;
    private RequestQueue mRequestQueue;


    public static synchronized Nibm getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
     OneSignal.startInit(this).init();
        instance = this;
        new Util(this);

       OneSignal.enableInAppAlertNotification(true);
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    }

