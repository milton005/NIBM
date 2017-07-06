package com.nibmglobal.nibm.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.appsflyer.AppsFlyerLib;
import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.auth.login.LoginActivity;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.exam.ExamFragment;
import com.nibmglobal.nibm.ui.fees.FeesFragment;
import com.nibmglobal.nibm.ui.notice.NoticeBoardFragment;
import com.nibmglobal.nibm.ui.profile.ProfileFragment;
import com.nibmglobal.nibm.ui.profile.batch.BatchProfileFragment;
import com.nibmglobal.nibm.ui.questionpaper.QuestionPaperFragment;
import com.nibmglobal.nibm.ui.student_activities.assignment.AssignmentFragment;
import com.nibmglobal.nibm.ui.student_activities.dispatched.DispatchedMaterialsFragment;
import com.nibmglobal.nibm.ui.student_activities.journal.JournalFragment;
import com.nibmglobal.nibm.ui.student_activities.studymaterial.StudyMaterialFragment;
import com.nibmglobal.nibm.ui.support.SupportActivity;
import com.nibmglobal.nibm.ui.support.SupportFragment;
import com.nibmglobal.nibm.ui.videos.privatevideos.PrivateVideoFragment;


public class MainActivity extends BaseActivity implements NavigationDrawerCallbacks, IntrfceMain{

    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private AlertDialog.Builder builder;
    private Resources res;
    FragmentManager fragmentmanager;


    /*public static void start(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppsFlyerLib.getInstance().startTracking(getApplication(),"b8PhUDurEcrgVMHymN9HnH");

        initUi();
        setupActionBar();

        fragmentmanager = getSupportFragmentManager();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        gotToFragmentsWithoutBackStack(new HomeFragment());

    }

    private void initUi() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);


    }

    private void gotToFragments(Fragment fragment, String tag) {
        boolean fragmentPopped = fragmentmanager.popBackStackImmediate(tag, 0);
        showLog(!fragmentPopped+"");
        if (fragment != null && !fragmentPopped) {
            fragmentmanager.beginTransaction().replace(R.id.frame, fragment)
                    .addToBackStack(tag)
                    .commit();
        }
    }
    private void gotToFragmentsWithoutBackStack(Fragment fragment) {

        if (fragment != null) {
            fragmentmanager.beginTransaction().replace(R.id.frame, fragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            getSupportFragmentManager().popBackStack();
        } else {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)){
                drawerLayout.closeDrawer(Gravity.LEFT);
            }else {
                super.onBackPressed();
            }


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:

                showDialog("Logout", R.drawable.ic_action_exit);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showDialog(String title, int icon) {

        if (builder == null){
            builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        }
        builder.setTitle(title);
        builder.setMessage("Do you want to logout?");
        builder.setIcon(icon);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                gotoLogin();

            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void gotoLogin() {
        savePref(Const.TAG_USERNAME, "");
        savePref(Const.TAG_ISLOGGED_IN, false);
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
        public void onNavigationDrawerItemSelected ( int position){
            drawerLayout.closeDrawer(Gravity.LEFT);
            gotoActivities(position);
        }


    private void gotoActivities(int id) {
        switch (id) {

            case R.id.textHome:
                gotToFragments(new HomeFragment(), HomeFragment.TAG);
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                break;
            case R.id.textPersonalProfile:
                gotToFragments(new ProfileFragment(), ProfileFragment.TAG);

                break;
            case R.id.textBatchProfile:
                gotToFragments(new BatchProfileFragment(), BatchProfileFragment.TAG);
                break;

            case R.id.textStudentAssignment:

                gotToFragments(new AssignmentFragment(), AssignmentFragment.TAG);
                break;

            case R.id.textStudyMaterial:

                gotToFragments(new StudyMaterialFragment(), StudyMaterialFragment.TAG);
                break;

            case R.id.textJournals:

                gotToFragments(new JournalFragment(), JournalFragment.TAG);
                break;
            case R.id.textDispatchedMaterials:

                gotToFragments(new DispatchedMaterialsFragment(), DispatchedMaterialsFragment.TAG);
                break;
            case R.id.textExamSchedule:

                gotToFragments(new ExamFragment(), ExamFragment.TAG);
                break;
            case R.id.textOnlineQuestionPAper:
                gotToFragments(new QuestionPaperFragment(), QuestionPaperFragment.TAG);
                break;
            case R.id.textFees:
                gotToFragments(new FeesFragment(), FeesFragment.TAG);
                break;
            case R.id.textNotice:

                gotToFragments(new NoticeBoardFragment(), NoticeBoardFragment.TAG);

                break;
            case R.id.textSupport:

                gotToFragments(new SupportFragment(), SupportFragment.TAG);
                break;
            default:

                break;

        }
    }

    @Override
    public void onActionbarSetTitle(String title) {
        try {
            getSupportActionBar().setTitle(title);
        } catch (Exception e) {

        }
    }

    @Override
    public void onHomeItemClicked(int position) {

        gotToHomeFragments(position);
    }

    private void gotToHomeFragments(int position) {
        switch (position) {
            case 0:

                gotToFragments(new StudyMaterialFragment(), StudyMaterialFragment.TAG);
                break;
            case 1:

                gotToFragments(new PrivateVideoFragment(), PrivateVideoFragment.TAG);
                break;
            case 2:

                gotToFragments(new AssignmentFragment(), AssignmentFragment.TAG);
                break;
            case 3:
                gotToFragments(new QuestionPaperFragment(), QuestionPaperFragment.TAG);
                break;
            case 4:

                gotToFragments(new ExamFragment(), ExamFragment.TAG);
                break;
            case 5:
                gotToFragments(new FeesFragment(), FeesFragment.TAG);

                break;
            case 6:
//                gotToFragments(new SupportFragment(), SupportFragment.TAG);
                startActivity(new Intent(this, SupportActivity.class));
//                SupportActivity.start(this);
                break;
            case 7:
                startActivity(new Intent(this, OtherPaymentActivity.class));
//                OtherPaymentActivity.start(this);
                break;
            default:
                break;

        }
    }
}
