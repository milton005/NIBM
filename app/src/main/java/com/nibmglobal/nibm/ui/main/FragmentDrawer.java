package com.nibmglobal.nibm.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.ui.base.BaseFragment;

/**
 * Created by mindlabs on 11/30/15.
 */
public class FragmentDrawer extends BaseFragment implements View.OnClickListener{


    private NavigationDrawerCallbacks mCallbacks;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallbacks = (NavigationDrawerCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_drawer, null);
        initUi(v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void initUi(View view) {

        ImageView imageProfile = (ImageView) view.findViewById(R.id.imageviewProfile);
        TextView textPersonalProfile = (TextView) view.findViewById(R.id.textPersonalProfile);
        TextView textBatchProfile = (TextView) view.findViewById(R.id.textBatchProfile);
        TextView textStudentAssignment = (TextView) view.findViewById(R.id.textStudentAssignment);
        TextView textStudyMaterial = (TextView) view.findViewById(R.id.textStudyMaterial);
        TextView textJournals = (TextView) view.findViewById(R.id.textJournals);
        TextView textDispatchedMaterials = (TextView) view.findViewById(R.id.textDispatchedMaterials);
        TextView textExamSchedule = (TextView) view.findViewById(R.id.textExamSchedule);
        TextView textOnlineQuestionPaper = (TextView) view.findViewById(R.id.textOnlineQuestionPAper);
        TextView textFees = (TextView) view.findViewById(R.id.textFees);
        TextView textNotices = (TextView) view.findViewById(R.id.textNotice);
        TextView textSupport = (TextView) view.findViewById(R.id.textSupport);
        TextView textHome = (TextView) view.findViewById(R.id.textHome);

//        imageProfile.setOnClickListener(this);
        textPersonalProfile.setOnClickListener(this);
        textBatchProfile.setOnClickListener(this);
        textStudentAssignment.setOnClickListener(this);
        textStudyMaterial.setOnClickListener(this);
        textJournals.setOnClickListener(this);
        textDispatchedMaterials.setOnClickListener(this);
        textExamSchedule.setOnClickListener(this);
        textOnlineQuestionPaper.setOnClickListener(this);
        textFees.setOnClickListener(this);
        textNotices.setOnClickListener(this);
        textSupport.setOnClickListener(this);
        textHome.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        mCallbacks.onNavigationDrawerItemSelected(v.getId());
    }
}
