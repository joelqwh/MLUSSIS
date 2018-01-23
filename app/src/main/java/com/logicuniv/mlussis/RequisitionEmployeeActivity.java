package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RequisitionEmployeeActivity extends Activity {

    Requisition req;
    ArrayList<RequisitionDetail> reqDetList;
    SharedPreferences pref;
    RequisitionEmployeeArrayAdapter adapt;
    String saveReq;
    SharedPreferences.Editor edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_employee);
        //restoreInstance(savedInstanceState);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String requisitionNo = pref.getString("ReqNo",saveReq);
//        Log.e("joel",requisitionNo.toString());




        Button button_addItem = (Button) findViewById(R.id.button_requisition_employee_addItem);
        Button button_submitReq = (Button) findViewById(R.id.button_requisition_employee_submit);
        Button button_cancelReq = (Button) findViewById(R.id.button_requisition_employee_cancelReq);
        ListView reqItemList = (ListView) findViewById(R.id.listView_requisition_employee_raised);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("bundle");

        String itemNo = b.getString("ItemNo");

        StationeryCatalogue sc = StationeryCatalogue.searchCatalogueById(itemNo);

        String qty =b.getString("qty");

        if(requisitionNo!=null)
        {
            req= Requisition.getRequisitionById(requisitionNo);
            reqDetList=RequisitionDetail.getRequisitionDetail(requisitionNo);
        }

        if (req!=null||reqDetList!=(null))
        {
            RequisitionDetail reqDetAdd = new RequisitionDetail((String)req.get("ReqNo"),sc.get("ItemNo"),qty);

            Log.e("joel",reqDetAdd.toString());
            reqDetList.add(reqDetAdd);
            //adapt.add(reqDetAdd);
        }
        else
        {

            Date currentTime = Calendar.getInstance().getTime();
            req = new Requisition("R1"," E001",Calendar.getInstance().getTime());
            reqDetList= new ArrayList<>();      //createRequisitionDetailsArrayList
            RequisitionDetail reqDet = new RequisitionDetail((String)req.get("ReqNo"),sc.get("ItemNo"),qty);
            reqDetList.add(reqDet);
        }
            adapt = new RequisitionEmployeeArrayAdapter(this,reqDetList);
        reqItemList.setAdapter(adapt);


//        ArrayAdapter<RequisitionDetail> requisitionDetailArrayAdapter = new ArrayAdapter<RequisitionDetail>(this, R.layout.row_list_catalogue_employee,reqDetList);
//        reqItemList.setAdapter(requisitionDetailArrayAdapter);

        View.OnClickListener ocl_addItem = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = pref.edit();
                saveReq = (String)req.get("ReqNo");
                edit.putString("ReqNo",saveReq);
                edit.apply();
                Intent intent = new Intent(RequisitionEmployeeActivity.this,Catalogue_EmployeeActivity.class);
                startActivity(intent); //check if Requisition gets lost when adding new item. need to ensure that it stays. StartActivityForResult?

            }
        };
        button_addItem.setOnClickListener(ocl_addItem);

        View.OnClickListener ocl_reject = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.equals(null);
                reqDetList.equals(null); //or set it to null?
                adapt.clear();
                edit = pref.edit();
                edit.clear();
                edit.commit();
                Toast.makeText(RequisitionEmployeeActivity.this, "Requisition removed", Toast.LENGTH_LONG).show();
            }
        };
        button_cancelReq.setOnClickListener(ocl_reject);

        View.OnClickListener ocl_submit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Requisition.addRequisition(req);
                RequisitionDetail.addRequisitionDetail(reqDetList);
                Toast.makeText(RequisitionEmployeeActivity.this, "Requisition submitted", Toast.LENGTH_LONG).show();
                req.equals(null);
                reqDetList.equals(null); //or set it to null?
                edit = pref.edit();
                edit.clear();
                edit.commit();
                adapt.clear();
            }
        };
        button_submitReq.setOnClickListener(ocl_submit);



    }
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putSerializable("Req",req);
//        outState.putSerializable("List", reqDetList);
//    }
//
//    void restoreInstance (Bundle state)
//    {
//        if (state!=null)
//        {
//            req = (Requisition)state.get("Req");
//            Log.e("joel",req.toString());
//            reqDetList = (ArrayList<RequisitionDetail>) state.get("List");
//            Log.e("joel",reqDetList.toString());
//        }
//    }
}
