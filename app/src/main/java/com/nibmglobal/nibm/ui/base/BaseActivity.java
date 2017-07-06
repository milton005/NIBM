package com.nibmglobal.nibm.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.Util;
import com.nibmglobal.nibm.ui.views.CustomProgressDialog;

/**
 * Created by mindlabs on 11/26/15.
 */
public class BaseActivity extends AppCompatActivity {

    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private ProgressDialog getProgressDialog() {
        if (this.pDialog == null) {
            this.pDialog = CustomProgressDialog.nowRunningDialog(this);
        }
        return this.pDialog;
    }

    public void showProgress() {
        getProgressDialog();
        if (this.pDialog != null) {
            this.pDialog.show();
        }

    }

    public void hideProgress() {
        if (pDialog != null && this.pDialog.isShowing()) {
            this.pDialog.dismiss();
            pDialog = null;
        }
    }

    public  boolean isValidEmail(CharSequence target) {
        return Util.getUtils().isValidEmail(target);
    }

    public void showSnackBar(String msg, boolean isSuccess){

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT);
        View view  = snackbar.getView();
        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        if (isSuccess){
            view.setBackgroundColor(Color.GREEN);
        }else {
            view.setBackgroundColor(Color.RED);
        }

        snackbar.show();

//        Snackbar.make(context.findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();
    }

    public void showLog(String msg){

        Log.d(Const.LOG_TAG, "-------------" + msg);
    }
    public void showLog(String msg, Throwable tr){

        Log.d(Const.LOG_TAG, "-------------" + msg+"----", tr);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void delete(String key) {
        Util.getUtils().delete(key);
    }


    public void savePref(String key, Object value) {
        Util.getUtils().savePref(key, value);
    }



    @SuppressWarnings("unchecked")
    public <T> T getPref(String key) {
        return (T) Util.getUtils().getPref(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key, T defValue) {
        return Util.getUtils().getPref(key, defValue);
    }

    public boolean isPrefExists(String key) {
        return Util.getUtils().isPrefExists(key);
    }


    public void setupDownloadingProcedure(String fileUrl, String folderName, int fileFormat) {
        // TODO Auto-generated method stub
        Util.getUtils().setupDownloadingProcedure(fileUrl, folderName, this, fileFormat);

    }

    public void openWebPage(String url) {
        Util.getUtils().openWebPage(url, this);
    }
    public void composeEmail(String[] addresses) {
        Util.getUtils().composeEmail(addresses, this);
    }
    public void dialPhoneNumber(String ph) {
        Util.getUtils().dialPhoneNumber(ph, this);
    }

}
