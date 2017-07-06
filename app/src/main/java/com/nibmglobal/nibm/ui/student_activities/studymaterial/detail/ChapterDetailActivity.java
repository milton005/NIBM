package com.nibmglobal.nibm.ui.student_activities.studymaterial.detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.augtonov.webivew.HTML5WebView;
import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;

/**
 * Created by mindlabs on 12/3/15.
 */
public class ChapterDetailActivity extends BaseActivity{

    private Toolbar toolbar;
//    private static WebView webViewChapter;
    private SupportStudyMaterialChapter eachChapter;

    HTML5WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_chapter_detail);

        if (getIntent().hasExtra(Const.TAG_DETAIL)){
            eachChapter = (SupportStudyMaterialChapter) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        }

        setActionbar();
        initUi();*/

        if (getIntent().hasExtra(Const.TAG_DETAIL)){
            eachChapter = (SupportStudyMaterialChapter) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        }

        mWebView = new HTML5WebView(this);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            if (isNetworkAvailable()){
                loadView();
            }else {
                showSnackBar(getResources().getString(R.string.connectivity), false);
            }

        }

        setContentView(mWebView.getLayout());


    }

    private void setActionbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(eachChapter.getLessonName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUi() {
//        webViewChapter = (WebView) findViewById(R.id.webviewChapter);
    }

    public void loadView() {
//        mWebView.loadData(html, "text/html", "utf-8");
        mWebView.loadUrl(eachChapter.getBookFile());
//        openWebPage(eachChapter.getBookFile());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
