package com.nibmglobal.nibm.ui.Webinar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.augtonov.webivew.HTML5WebView;
import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.videos.samplevideos.SupportVideos;

/**
 * Created by user on 8/11/2016.
 */
public class Webinar_videoshowing_activity extends BaseActivity {
    private Toolbar toolbar;
    private WebView webView;
public  String link;
    private SupportVideos video;
    HTML5WebView mWebView;


    /*public static void start(Context context) {
        Intent i = new Intent(context, VideoShowingActivity.class);
        context.startActivity(i);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new HTML5WebView(this);
link= "https://www.youtube.com/embed/SSt69AC2L3A";
//        if (getIntent().hasExtra(Const.TAG_DETAIL)) {
//            video = (SupportVideos) getIntent().getSerializableExtra(Const.TAG_DETAIL);
//        }

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {
            if (isNetworkAvailable()){

            String html = "<html><body><iframe src=\"video_link\" width=\"500\" height=\"300\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe></body></html>";

//                String html = "<html><body><iframe src=\"https://player.vimeo.com/video/147356726\" width=\"500\" height=\"281\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>\n</body></html>";

                mWebView.loadData(html.replaceAll("video_link", link), "text/html", "utf-8");
                this.mWebView.zoomIn();
            }else {
                showSnackBar(getResources().getString(R.string.connectivity), false);

            }

        }

        setContentView(mWebView.getLayout());

    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Video");
    }

    private void initUi() {

        webView = (WebView) findViewById(R.id.webViewRegister);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mWebView.stopLoading();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.inCustomView()) {
                mWebView.hideCustomView();
                //  mWebView.goBack();
                //mWebView.goBack();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mWebView.stopLoading();
        mWebView.onPause();
        super.onPause();
    }
}
