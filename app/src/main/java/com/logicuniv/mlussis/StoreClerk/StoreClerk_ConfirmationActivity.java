package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

public class StoreClerk_ConfirmationActivity extends MLussisActivity {


    static ArrayList<DisbursementDetail> disbursementConf = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk__confirmation);

        final ListView conf = (ListView)findViewById(R.id.listView_confirmation_storeclerk);
        Bundle b = getIntent().getBundleExtra("bundle");
        TextView confDeptName = (TextView)findViewById(R.id.confDeptName);
        confDeptName.setText(b.getString("deptName"));
        TextView confDeptRep = (TextView)findViewById(R.id.confDeptRep);
        confDeptRep.setText(b.getString("repName"));
        final String confdisbNo = b.getString("disbNo");

        new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... params) {
                disbursementConf = DisbursementDetailController.getCurrentDisbursementDetailsOf(params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                DisbursementItemArrayAdapter ddadapt = new DisbursementItemArrayAdapter(getApplicationContext(),disbursementConf);
                conf.setAdapter(ddadapt);
                View header = getLayoutInflater().inflate(R.layout.fragment_store_clerk_disburse_row_header,null);
                conf.addHeaderView(header, null, false);
            }
        }.execute(confdisbNo);
    }
}
