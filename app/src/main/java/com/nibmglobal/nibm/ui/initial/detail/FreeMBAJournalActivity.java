package com.nibmglobal.nibm.ui.initial.detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.ui.student_activities.journal.JournalAdapter;
import com.nibmglobal.nibm.ui.student_activities.journal.SupportJournal;
import com.nibmglobal.nibm.ui.webservice.WebServiceActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/5/15.
 */
public class FreeMBAJournalActivity extends WebServiceActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<SupportJournal> allJournal;
    private JournalAdapter adapter;

    /*public static void start(Context context) {
        context.startActivity(new Intent(context, FreeMBAJournalActivity.class));
    }*/

    @Override
    protected void getResponseObject(String object) {

    }

    @Override
    protected void getResponseArray(String jsonArray) {


        if (jsonArray == null)
            return;

        try {
            parseDetails(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_journall);
        setActionbar();
        initUi();
        initListner();
        allJournal = new ArrayList<>();
        setRecyclerView();

        if (isNetworkAvailable()) {
            getWebServiceData();
        }else {
            showSnackBar(getResources().getString(R.string.connectivity), false);
        }
    }

    private void setActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Journals");
    }

    private void initUi() {
        recyclerView = (RecyclerView) findViewById(R.id.studymaterials);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void initListner() {

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                if (isNetworkAvailable())
                    setupDownloadingProcedure(adapter.getLink(position), "Free_Journals", Const.FORMAT_PDF);

            }
        });

    }

    private void setRecyclerView() {
        adapter = new JournalAdapter(this, allJournal);
        recyclerView.setAdapter(adapter);
    }


    private void getWebServiceData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "journalsfull");

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String file_url = jsonObject.getString("file_url");
            String journal_file = jsonObject.getString("journal_file");
            String journal_type = jsonObject.getString("journal_type");
            String month = jsonObject.getString("month");
            String title = jsonObject.getString("title");
            String volume = jsonObject.getString("volume");
            String year = jsonObject.getString("year");

            SupportJournal object = new SupportJournal();
            object.setFileUrl(file_url);
            object.setJournalFile(journal_file);
            object.setJournalType(journal_type);
            object.setMonth(month);
            object.setTitle(title);
            object.setVolume(volume);
            object.setYear(year);

            allJournal.add(object);
        }
        adapter.notifyDataSetChanged();
    }
}
