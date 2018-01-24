package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.DisbursementDetails;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

public class StoreClerk_ConfirmationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk__confirmation);
        Bundle b = getIntent().getBundleExtra("bundle");
        TextView confDeptName = (TextView)findViewById(R.id.confDeptName);
        confDeptName.setText(b.getString("deptName"));
        TextView confDeptRep = (TextView)findViewById(R.id.confDeptRep);
        confDeptRep.setText(b.getString("repName"));
        String confdisbNo = b.getString("disbNo");
        Log.e("fish", confdisbNo);

//        ArrayList<DisbursementDetail> disAdd = DisbursementDetailController.getDisbursementDetail(confdisbNo);
//        DisbursementItemArrayAdapter ddadapt = new DisbursementItemArrayAdapter(this,disAdd);
//        ListView conf = (ListView)findViewById(R.id.listView_confirmation_storeclerk);
//        conf.setAdapter(ddadapt);
    };
}
