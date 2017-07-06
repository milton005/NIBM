package com.nibmglobal.nibm.ui.course;

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
import android.widget.Button;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.ui.auth.register.RegisterActivity;
import com.nibmglobal.nibm.ui.base.BaseActivity;

public class CourseDetail extends BaseActivity {
    private Button buttonApplyOnline;
    SupportCourse course;
    private WebView mWebView;
    private Toolbar toolbar;



    public static void start(Context context, SupportCourse course) {
        Intent i = new Intent(context, CourseDetail.class);
        i.putExtra("data", course);
        context.startActivity(i);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        if (getIntent().hasExtra("data")) {
            this.course = (SupportCourse) getIntent().getSerializableExtra("data");
        }
        initUi();
        setActionbar();
        this.mWebView.setWebViewClient(new MyWebviewClient());

        if (this.course != null) {
            this.mWebView.loadDataWithBaseURL(null, this.course.getCourseDetails(), "text/html", "UTF-8", null);
        }
        this.mWebView.setWebChromeClient(new MyWebChromeClient());
        
        buttonApplyOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseDetail.this, RegisterActivity.class));
//                RegisterActivity.start(CourseDetail.this);
            }
        });
    }

    private void setActionbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (this.course != null) {
            getSupportActionBar().setTitle(this.course.getCourseTitle());
        }
    }

    private void initUi() {
        this.buttonApplyOnline = (Button) findViewById(R.id.buttonOnline);
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

    public class MyWebviewClient extends WebViewClient {
        MyWebviewClient() {
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

    /* renamed from: com.nibmglobal.nibm.ui.course.CourseDetail.2 */
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
}
