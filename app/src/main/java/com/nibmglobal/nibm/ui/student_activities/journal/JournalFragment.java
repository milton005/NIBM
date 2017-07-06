package com.nibmglobal.nibm.ui.student_activities.journal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibmglobal.nibm.R;
import com.nibmglobal.nibm.Utilities.Const;
import com.nibmglobal.nibm.Utilities.ItemClickSupport;
import com.nibmglobal.nibm.ui.main.IntrfceMain;
import com.nibmglobal.nibm.ui.webservice.WebServiceFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/2/15.
 */
public class JournalFragment extends WebServiceFragment {

    public static String TAG = "journal";

    private RecyclerView recyclerView;
    private ArrayList<SupportJournal> allJournal;
    private JournalAdapter adapter;
    private IntrfceMain mCallbacks;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (IntrfceMain) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_journall, null);
        mCallbacks.onActionbarSetTitle("Journals");
        initUi(v);
        initListner();
        allJournal = new ArrayList<>();
        setRecyclerView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.studymaterials);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void initListner() {

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {



                if (isNetworkAvailable())
                    setupDownloadingProcedure("http://www.nibmglobal.com/prospectus/nibm_prospectus_application_form.pdf", "Journals", Const.FORMAT_PDF);
//                    setupDownloadingProcedure(adapter.getLink(position), "Journals", Const.FORMAT_PDF);

            }
        });

    }

    private void setRecyclerView() {
        adapter = new JournalAdapter(getActivity(), allJournal);
        recyclerView.setAdapter(adapter);
    }


    private void getWebServiceData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "journals");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

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

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allJournal);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        super.onRestoreState(savedInstanceState);
        allJournal = savedInstanceState.getParcelableArrayList(TAG);
        setRecyclerView();
    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();
        if (isNetworkAvailable()){
            getWebServiceData();
        }else {
            showSnackBar(getActivity().getResources().getString(R.string.connectivity), false);
        }
    }
}
