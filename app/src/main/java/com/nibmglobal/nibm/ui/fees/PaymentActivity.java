package com.nibmglobal.nibm.ui.fees;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;

import java.io.UnsupportedEncodingException;

public class PaymentActivity extends BaseActivity {
    private String URL_PAY = "http://crm.kingsteredu.com/exam_due/api_post";
    private WebView mWebView;
    ModelPayment modelPayment;
    private Toolbar toolbar;

    private class MyWebViewClient extends WebViewClient {
        MyWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        MyWebChromeClient() {
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }


    public static void start(Context context, ModelPayment modelPayment) {
        Intent i = new Intent(context, PaymentActivity.class);
        i.putExtra(Const.TAG_DETAIL, modelPayment);
        context.startActivity(i);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initUi();
        setActionbar();
        this.modelPayment = (ModelPayment) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        this.mWebView.setWebViewClient(new MyWebViewClient());
        byte[] data = new byte[0];
        try {
            data = ("late_fee=" + this.modelPayment.getLate_fee() + "&late_fee_tax=" + this.modelPayment.getLate_fee_tax() + "&tax_amount=" + this.modelPayment.getTax_amount() + "&total_including_tax=" + this.modelPayment.getTotal_including_tax() + "&subjectfeetaxamount=" + this.modelPayment.getSubjectfeetaxamount() + "&subject_fee_including_tax=" +
                    this.modelPayment.getSubject_fee_including_tax() + "&subject_fee=" + this.modelPayment.getSubject_fee() + "dd_amount" + this.modelPayment.getDd_amount() + "&stud_enrol_id=" + this.modelPayment.getStud_enrol_id() + "&course_id=" + this.modelPayment.getCourse_id() + "&total_subjects=" + this.modelPayment.getTotal_subjects() + "&semester_id=" +
                    this.modelPayment.getSemester_id() + "&year=" + this.modelPayment.getYear() + "&month=" + this.modelPayment.getMonth()).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.mWebView.postUrl(this.URL_PAY, data);
        this.mWebView.setWebChromeClient(new MyWebChromeClient());
    }

    private void setActionbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fee Payment");
    }

    private void initUi() {
        this.mWebView = (WebView) findViewById(R.id.web_layout);
        this.mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setDisplayZoomControls(false);
        this.mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.mWebView.getSettings().setSupportZoom(true);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setSupportMultipleWindows(true);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case ItemTouchHelper.LEFT :
                    if (this.mWebView.canGoBack()) {
                        this.mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!this.mWebView.canGoBack()) {
                    onBackPressed();
                    break;
                }
                this.mWebView.goBack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
