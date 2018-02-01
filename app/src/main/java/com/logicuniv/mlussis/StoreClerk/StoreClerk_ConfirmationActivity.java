package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.DisbursementController;
import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.Model.Disbursement;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

public class StoreClerk_ConfirmationActivity extends MLussisActivity {


    static ArrayList<DisbursementDetail> disbursementConf = new ArrayList<>();
    Disbursement currentDisbursement = new Disbursement();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk__confirmation);

        Button button_conf = (Button)findViewById(R.id.submitPinButton);
        final EditText enterPin = (EditText)findViewById(R.id.enterPineditText);
       //String pin = enterPin.getText().toString();
        final ListView conf = (ListView)findViewById(R.id.listView_confirmation_storeclerk);
        Bundle b = getIntent().getBundleExtra("bundle");
        TextView confDeptName = (TextView)findViewById(R.id.confDeptName);
        confDeptName.setText(b.getString("deptName"));
        TextView confDeptRep = (TextView)findViewById(R.id.confDeptRep);
        confDeptRep.setText(b.getString("repName"));
        final String confdisbNo = b.getString("disbNo");
        final String deptCode = b.getString("deptCode");

                new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... params) {

                disbursementConf = DisbursementDetailController.getCurrentDisbursementDetailsOf(params[0]);
                currentDisbursement = DisbursementController.getCurrentDisbursementForDepartment(params[1]);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                DisbursementItemArrayAdapter ddadapt = new DisbursementItemArrayAdapter(getApplicationContext(),disbursementConf);
                conf.setAdapter(ddadapt);
                View header = getLayoutInflater().inflate(R.layout.fragment_store_clerk_disburse_row_header,null);
                conf.addHeaderView(header, null, false);

            }
        }.execute(confdisbNo,deptCode);

        button_conf.setOnClickListener(new View.OnClickListener() {
            String disbursePin = currentDisbursement.get("Pin");
            @Override
            public void onClick(View v) {


                if (!enterPin.getText().toString().equals(currentDisbursement.get("Pin"))){
                    Toast.makeText(getApplicationContext(),"Please enter correct PIN", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    new AsyncTask <String, Void, Void>(){
                        @Override
                        protected Void doInBackground(String... params){
                            DisbursementController.MarkDisbursementAsCollected(params[0], params[1]);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result)
                        {
                            Toast.makeText(getApplicationContext(),"Disbursement completed", Toast.LENGTH_LONG).show();
                            finish();
                        }
                }.execute(confdisbNo, enterPin.getText().toString());
                }

            }
        });
    }
}
