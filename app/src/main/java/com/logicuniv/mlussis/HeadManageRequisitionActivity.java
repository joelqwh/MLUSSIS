package com.logicuniv.mlussis;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeadManageRequisitionActivity extends Activity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_manage_requisition);
        setTitle("View Pending Requisitions");

        ListView lv_manageReq = findViewById(R.id.listView_managereqlist_deputy);

        ArrayList<Requisition> alr = (ArrayList<Requisition>)Requisition.getPendingRequisitions();

        ListAdapter adapt = new SimpleAdapter(this,alr,R.layout.row_list_managereq_deputy, new String[]{"IssuedBy", "ReqNo", "DateIssued"},
                new int[]{R.id.textView__managereq_empname, R.id.textView_managereq_reqno, R.id.textView_managereq_reqdate});
        lv_manageReq.setAdapter(adapt);
        lv_manageReq.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Requisition r = (Requisition) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, ViewPendingRequisitionDetailsActivity.class);
        intent.putExtra("Req",(String)r.get("ReqNo"));

        startActivity(intent);
    }
}
