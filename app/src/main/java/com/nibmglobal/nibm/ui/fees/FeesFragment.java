package com.nibmglobal.nibm.ui.fees;

import android.content.Context;
import android.content.Intent;
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
import com.nibmglobal.nibm.ui.fees.detail.FeeDetailActivity;
import com.nibmglobal.nibm.ui.main.IntrfceMain;
import com.nibmglobal.nibm.ui.webservice.WebServiceFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mindlabs on 12/2/15.
 */
public class FeesFragment extends WebServiceFragment implements PaymentCall{
    public static String TAG = "fees";

    private RecyclerView recyclerView;
    private ArrayList<SupportFees> allFees;
    private FeesAdapter adapter;
    private IntrfceMain mCallbacks;

    private boolean callFirstApi = true;
    @Override
    protected void getResponseObject(String object) {

    }

    @Override
    protected void getResponseArray(String jsonArray) {

        if (jsonArray != null) {
            try {
                if (callFirstApi) {
                    parseDetails(jsonArray);
                } else {
                    parsePayment(jsonArray);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (IntrfceMain) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fees, null);
        mCallbacks.onActionbarSetTitle("Fees Details");
        initUi(v);
        initListner();
        allFees = new ArrayList<>();
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

                Intent i = new Intent(getActivity(), FeeDetailActivity.class);
                i.putExtra(Const.TAG_DETAIL, (Serializable) adapter.getObject(position));
                getActivity().startActivity(i);
            }
        });
    }

    private void setRecyclerView() {
        adapter = new FeesAdapter(getActivity(), allFees);
        adapter.setCallbackListner(this);
        recyclerView.setAdapter(adapter);
    }

    private void getWebServiceData() {
        this.callFirstApi = true;
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", "fees");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));

        startWebServiceJsonArray(Const.ParentURL, params, Const.POST);
    }

    private void parseDetails(String data) throws JSONException {

        JSONArray jsonArray = new JSONArray(data);
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String transaction_id = jsonObject.getString("transaction_id");
            String transaction_date = jsonObject.getString("transaction_date");
            String receipt_no = jsonObject.getString("receipt_no");
            String paystatus = jsonObject.getString("paystatus");
            String payment_type = jsonObject.getString("payment_type");
            String payment_mode = jsonObject.getString("payment_mode");
            String paid_fee = jsonObject.getString("paid_fee");
            String fee_amount = jsonObject.getString("fee_amount");
            String FeeName = jsonObject.getString("FeeName");
            String dd_no = jsonObject.getString("dd_no");
            String dd_date = jsonObject.getString("dd_date");
            String cheque_no = jsonObject.getString("cheque_no");
            String cheque_date = jsonObject.getString("cheque_date");
            String card_type = jsonObject.getString("card_type");
            String card_number = jsonObject.getString("card_number");
            String bank_name = jsonObject.getString("bank_name");

            SupportFees object = new SupportFees();
            object.setTaransationId(transaction_id);
            object.setTransationDate(transaction_date);
            object.setReciptNo(receipt_no);

            if (paystatus.contentEquals("P")){
                object.setPayStatus("paid");
            }else if (paystatus.contentEquals("PP")) {
                object.setPayStatus("Due");
            }

            object.setPaymentMode(payment_mode);
            object.setPaymentType(payment_type);
            object.setPaidFees(paid_fee);
            object.setFeesAmount(fee_amount);
            object.setFeesName(FeeName);
            object.setDdNo(dd_no);
            object.setDdDate(dd_date);
            object.setChecqueNo(cheque_no);
            object.setChecqueDate(cheque_date);
            object.setCardType(card_type);
            object.setCardNumber(card_number);
            object.setBankName(bank_name);

            allFees.add(object);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);
        outState.putParcelableArrayList(TAG, allFees);
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);
        super.onRestoreState(savedInstanceState);
//        allAssignments.clear();
        allFees = savedInstanceState.getParcelableArrayList(TAG);
        setRecyclerView();
    }

    private void parsePayment(String jsonArray) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonArray);
        String late_fee = jsonObject.getString("late_fee");
        String late_fee_tax = jsonObject.getString("late_fee_tax");
        String tax_amount = jsonObject.getString("tax_amount");
        String total_including_tax = jsonObject.getString("total_including_tax");
        String subjectfeetaxamount = jsonObject.getString("subjectfeetaxamount");
        String subject_fee_including_tax = jsonObject.getString("subject_fee_including_tax");
        String subject_fee = jsonObject.getString("subject_fee");
        String dd_amount = jsonObject.getString("dd_amount");
        String stud_enrol_id = jsonObject.getString("stud_enrol_id");
        String course_id = jsonObject.getString("course_id");
        String total_subjects = jsonObject.getString("total_subjects");
        String semester_id = jsonObject.getString("semester_id");
        String year = jsonObject.getString("year");
        String month = jsonObject.getString("month");
        ModelPayment modelPayment = new ModelPayment();
        modelPayment.setLate_fee(late_fee);
        modelPayment.setLate_fee_tax(late_fee_tax);
        modelPayment.setTax_amount(tax_amount);
        modelPayment.setTotal_including_tax(total_including_tax);
        modelPayment.setSubjectfeetaxamount(subjectfeetaxamount);
        modelPayment.setSubject_fee_including_tax(subject_fee_including_tax);
        modelPayment.setSubject_fee(subject_fee);
        modelPayment.setDd_amount(dd_amount);
        modelPayment.setStud_enrol_id(stud_enrol_id);
        modelPayment.setCourse_id(course_id);
        modelPayment.setTotal_subjects(total_subjects);
        modelPayment.setSemester_id(semester_id);
        modelPayment.setYear(year);
        modelPayment.setMonth(month);
        if (modelPayment != null) {
            PaymentActivity.start(getActivity(), modelPayment);
        }
    }



    public void activatePayment() {
        this.callFirstApi = false;
        Map<String, String> params = new HashMap();
        params.put("type", "exam_register_details");
        params.put("uname", (String) getPref(Const.TAG_USERNAME));
        startWebServiceJsonArray(Const.ParentURL, params, 1);
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

    @Override
    public void onResume() {
        super.onResume();
        if (isNetworkAvailable()) {
            getWebServiceData();
        } else {
            showSnackBar(getActivity().getResources().getString(R.string.connectivity), false);
        }
    }
}
