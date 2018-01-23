package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewPendingRequisitionDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending_requisition_details);

        Intent i = getIntent();
        Requisition req = (Requisition)i.getExtras().get("Req");
        ArrayList<RequisitionDetail> al_rd = RequisitionDetail.getRequisitionDetail((String)req.get("ReqNo"));
    }


}
