package com.nibmglobal.nibm.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.initial.InitialActivity;
import com.nibmglobal.nibm.ui.main.MainActivity;

/**
 * Created by mindlabs on 11/26/15.
 */
public class SplashActivity extends BaseActivity {
    public int TAG_TIMER_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initializeTimerTask();
    }

    public void initializeTimerTask() {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (getPref(Const.TAG_ISLOGGED_IN, false)) {

                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
//                    MainActivity.start(SplashActivity.this);
                } else {
                    startActivity(new Intent(SplashActivity.this, InitialActivity.class));
//                    InitialActivity.start(SplashActivity.this);
                }
                finish();
            }


        }, TAG_TIMER_DELAY);
    }
}
