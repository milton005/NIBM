package com.nibmglobal.nibm.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.ui.base.BaseFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mindlabs on 11/30/15.
 */
public class HomeFragment extends BaseFragment {

//    private GridView gridHome;
//    private HomeAdapter adapter;
    public static String TAG = "home";
    private Resources res;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    HomeAdapter_ adapter;
    private IntrfceMain mCallbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (IntrfceMain) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, null);
        mCallbacks.onActionbarSetTitle("Home");
        initUi(v);
        res = getResources();
        setHomeView();
        initListner();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initUi(View view) {
//        gridHome = (GridView) view.findViewById(R.id.gridView_home);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.gridView_home);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


    }

    private void initListner() {
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                mCallbacks.onHomeItemClicked(position);
            }
        });
    }

    private void setHomeView() {
        adapter = new HomeAdapter_(getDrawerImages(), getHomeTitles(), getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    private List<String> getHomeTitles() {
        String[] itemsArray = res.getStringArray(R.array.home_items_array);
        return Arrays.asList(itemsArray);
    }

    private TypedArray getDrawerImages() {
        TypedArray icons = res.obtainTypedArray(R.array.home_icons);
        return icons;
    }


}
