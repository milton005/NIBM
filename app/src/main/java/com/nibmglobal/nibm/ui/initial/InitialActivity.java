package com.nibmglobal.nibm.ui.initial;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.appsflyer.AppsFlyerLib;
import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.ui.Freeprospectus.Freeprospectusactivity;
import com.nibmglobal.nibm.ui.Webinar.Webinar_activity;
import com.nibmglobal.nibm.ui.aboutus.AboutUsActivity;
import com.nibmglobal.nibm.ui.auth.login.LoginActivity;
import com.nibmglobal.nibm.ui.auth.register.RegisterActivity;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.contact.ContactusActivity;
import com.nibmglobal.nibm.ui.course.CourseActivity;
import com.nibmglobal.nibm.ui.download.ProspectusDownload;
import com.nibmglobal.nibm.ui.initial.detail.FreeMBAJournalActivity;
import com.nibmglobal.nibm.ui.videos.samplevideos.SampleVideosActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mindlabs on 12/1/15.
 */
public class InitialActivity extends BaseActivity {

    private GridView gridView;
    InitialActivityAdapter adapter;
    private Resources res;

    /*public static void start(Context context) {
        context.startActivity(new Intent(context, InitialActivity.class));
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        AppsFlyerLib.getInstance().startTracking(getApplication(),"b8PhUDurEcrgVMHymN9HnH");

        initUi();
        res = getResources();
        setInitialView();
        initListner();
    }

    private void initUi() {
        gridView = (GridView) findViewById(R.id.gridView_initial);

    }

    private void setInitialView() {
        adapter = new InitialActivityAdapter(getDrawerImages(), getHomeTitles(), this);
        gridView.setAdapter(adapter);
    }

    private List<String> getHomeTitles() {
        String[] itemsArray = res.getStringArray(R.array.initial_items_array);
        return Arrays.asList(itemsArray);
    }

    private TypedArray getDrawerImages() {
        TypedArray icons = res.obtainTypedArray(R.array.initial_icons);
        return icons;
    }

    private void initListner() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotActivities(position);
            }
        });
    }

    private void gotActivities(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, CourseActivity.class));
//                CourseActivity.start(this);
                break;
            case 1:
//                RegisterActivity.start(this);
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, SampleVideosActivity.class));
//                SampleVideosActivity.start(this);
                break;
            case 3:
                startActivity(new Intent(this, ProspectusDownload.class));
//                ProspectusDownload.start(this);
                break;
            case 4:
                startActivity(new Intent(this, LoginActivity.class));
//                LoginActivity.start(this);
                break;
            case 5:
                startActivity(new Intent(this, ContactusActivity.class));
//                ContactusActivity.start(this);
//                LoginActivity.start(this);
                break;
            case 6:
                startActivity(new Intent(this, AboutUsActivity.class));
//                AboutUsActivity.start(this);

                break;
            case 7:
                startActivity(new Intent(this, FreeMBAJournalActivity.class));
//                FreeMBAJournalActivity.start(this);
                break;
            case 8:startActivity(new Intent(this, Webinar_activity.class));
                break;
            case 9:startActivity(new Intent(this, Freeprospectusactivity.class));

            default:
                break;

        }
    }


}
