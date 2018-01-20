package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RequisitionEmployeeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_employee);

        Button button_addItem = (Button) findViewById(R.id.button_requisition_employee_addItem);
        Button button_submitReq = (Button) findViewById(R.id.button_requisition_employee_submit);
        Button button_cancelReq = (Button) findViewById(R.id.button_requisition_employee_cancelReq);
        ListView reqItemList = (ListView) findViewById(R.id.listView_requisition_employee_raised);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        String qty = (String) b.get("Qty");
        StationeryCatalogue sc = (StationeryCatalogue) b.get("addItem");

        Requisition req=null;
        ArrayList<RequisitionDetail> reqDetList=null;

        if (req.equals(null)||reqDetList.equals(null))
        {
            Date currentTime = Calendar.getInstance().getTime();
            req = new Requisition("1","E1",currentTime);
            reqDetList= new ArrayList<>();
            RequisitionDetail reqDet = new RequisitionDetail((String)req.get("reqNo"),sc.get("ItemNo"),qty);
            reqDetList.add(reqDet);
        }
        else
        {
            RequisitionDetail reqDetAdd = new RequisitionDetail((String)req.get("reqNo"),sc.get("ItemNo"),qty);
            reqDetList.add(reqDetAdd);
        }



    }
}
