package com.nibmglobal.nibm.ui.main;

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

public class OtherPaymentActivity extends BaseActivity {
    private String URL_PAY = "http://www.nibmglobal.com/ebs/payment-other-mobile.php";
    private String URL_REDIRECT = "http://www.nibmglobal.com/php/vpc_php_serverhost_dr.php?";
    private WebView mWebView;
    private Toolbar toolbar;

    /*public static void start(Context context) {
        context.startActivity(new Intent(context, OtherPaymentActivity.class));
    }*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherpayment);
        initUi();
        setActionbar();
        this.mWebView.setWebViewClient(new MyWebViewClient());
        this.mWebView.loadUrl(this.URL_PAY);
        this.mWebView.setWebChromeClient(new MyWebChromeClient());
    }

    private void setActionbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Other Payment Options");
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
}
