package com.nibmglobal.nibm.ui.fees.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.ui.base.BaseActivity;
import com.nibmglobal.nibm.ui.fees.SupportFees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mindlabs on 12/4/15.
 */
public class FeeDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SupportFees object;
    private ArrayList<String> list;
    private FeeDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_detail_);
        if (getIntent().hasExtra(Const.TAG_DETAIL)) {
            object = (SupportFees) getIntent().getSerializableExtra(Const.TAG_DETAIL);
        }
        setActionbar();
        initUi();
        list = new ArrayList<>();
        setRecyclerView();
        addListValues();
    }

    private void setActionbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (object != null)
            getSupportActionBar().setTitle(object.getFeesName());
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.detailRecyler);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

    }
    private void setRecyclerView() {
        adapter = new FeeDetailAdapter(this, list, getStringFileDatas());
        recyclerView.setAdapter(adapter);
    }

    private List<String> getStringFileDatas() {
        String[] itemsArray = getResources().getStringArray(R.array.fees_items_array);
        return Arrays.asList(itemsArray);
    }

    private void addListValues() {

        if (object != null) {
            list.add(object.getFeesName());
            list.add(object.getFeesAmount());
            list.add(object.getPaidFees());
            list.add(object.getPaymentType());
            list.add(object.getPaymentMode());
            list.add(object.getPayStatus());
            list.add(object.getReciptNo());
            list.add(object.getDdNo());
            list.add(object.getDdDate());
            list.add(object.getChecqueNo());
            list.add(object.getChecqueDate());
            list.add(object.getBankName());
            list.add(object.getCardType());
            list.add(object.getCardNumber());
            list.add(object.getTaransationId());
            list.add(object.getTransationDate());
        }


        adapter.notifyDataSetChanged();
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
