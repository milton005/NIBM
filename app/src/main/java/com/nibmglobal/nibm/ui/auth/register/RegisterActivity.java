package com.nibmglobal.nibm.ui.auth.register;

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
import com.nibmglobal.nibm.ui.base.BaseActivity;

/**
 * Created by mindlabs on 11/26/15.
 */
public class RegisterActivity extends BaseActivity {
    private String URL_PAY = "http://m.nibmglobal.com/payment-mobile.php";
    private String URL_REDIRECT = "http://www.nibmglobal.com/php/vpc_php_serverhost_dr.php?";
    private WebView mWebView;
    private Toolbar toolbar;



    /*public static void start(Context context) {
        Intent i = new Intent(context, RegisterActivity.class);
        context.startActivity(i);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setActionbar();
        initUi();

//        loadUi();

        this.mWebView.setWebViewClient(new MyWebViewClient());
        this.mWebView.loadUrl(this.URL_PAY);
        this.mWebView.setWebChromeClient(new MyWebChromeClient());

    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Apply Online");
    }

    private void initUi() {

        mWebView = (WebView) findViewById(R.id.webViewRegister);
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

    @Override
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

    private class MyWebChromeClient extends WebChromeClient {
        MyWebChromeClient() {
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    }


    /* private void loadUi() {

        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);


        webView.loadUrl(Const.URL_REGISTER);
    }*/



}
