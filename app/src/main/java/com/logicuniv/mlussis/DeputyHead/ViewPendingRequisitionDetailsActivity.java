package com.logicuniv.mlussis.DeputyHead;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Backend.RequisitionController;
import com.logicuniv.mlussis.Backend.RequisitionDetailController;
import com.logicuniv.mlussis.Employee.RequisitionEmployeeArrayAdapter;
import com.logicuniv.mlussis.Model.Requisition;
import com.logicuniv.mlussis.Model.RequisitionDetail;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewPendingRequisitionDetailsActivity extends Activity{

    RequisitionEmployeeArrayAdapter adapt;
    Requisition req;
    String reqNo;
    ArrayList<RequisitionDetail> al_rd;

    TextView tv_empname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending_requisition_details);
        setTitle("Requisition Details");

        Intent i = getIntent();
        reqNo = i.getExtras().getString("Req");

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                req = RequisitionController.getRequisitionById(params[0]);
                al_rd = RequisitionDetailController.getRequisitionDetail(params[0]);

                return null;
            }
        }.execute(reqNo);



        tv_empname = findViewById(R.id.textView_pending_req_empname);
        TextView tv_reqNo = findViewById(R.id.textView_pending_req_no);
        ListView lv_reqdetails = findViewById(R.id.listView_pending_req_details);

        View header = getLayoutInflater().inflate(R.layout.header_row_list_requisition_employee,null);
        lv_reqdetails.addHeaderView(header,null,false);

        Button button_approve = findViewById(R.id.button_pending_req_approve);
        Button button_reject = findViewById(R.id.button_pending_req_reject);
        final EditText editText_remarks = findViewById(R.id.editText_pending_req_remarks);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                tv_empname.setText(getString(R.string.text_pendingrep_empname, EmployeeController.getEmployeeName(req.get("IssuedBy").toString())));

                return null;
            }
        }.execute();

     //when employee table is up, use the appropraite method
        tv_reqNo.setText(getString(R.string.text_pendingrep_reqno,req.get("ReqNo")));
        adapt = new RequisitionEmployeeArrayAdapter(this,al_rd);
        lv_reqdetails.setAdapter(adapt);

        View.OnClickListener ocl_approve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {

                        req.put("ApprovedBy", LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));

                        return null;
                    }
                }.execute();

                //req.put("ApprovedBy", LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));   //get HeadID from login
                req.put("DateReviewed", Calendar.getInstance().getTime().toString());
                req.put("Status","Approved");

                if(editText_remarks.getText()!=null)
                {
                    req.put("Remarks",editText_remarks.getText().toString());
                }

                new AsyncTask<Requisition, Void, Void>() {
                    boolean t = false;
                    @Override
                    protected Void doInBackground(Requisition... params) {

                        t=RequisitionController.updateRequisition(params[0]);

                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result)
                    {
                        if(t=true)
                        {
                            Toast.makeText(ViewPendingRequisitionDetailsActivity.this,"Requisition Approved",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ViewPendingRequisitionDetailsActivity.this, HeadManageRequisitionActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(ViewPendingRequisitionDetailsActivity.this,"Requisition Rejected",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ViewPendingRequisitionDetailsActivity.this, HeadManageRequisitionActivity.class);
                            startActivity(i);
                        }
                    }
                }.execute(req);

            }
        };
        button_approve.setOnClickListener(ocl_approve);

        View.OnClickListener ocl_reject = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.put("ApprovedBy",LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));   //get HeadID from login
                req.put("DateReviewed", Calendar.getInstance().getTime().toString());
                req.put("Status","Rejected");


                if(editText_remarks!=null)
                {
                    req.put("Remarks",editText_remarks.getText().toString());
                }

                new AsyncTask<Requisition, Void, Void>() {
                    boolean t = false;
                    @Override
                    protected Void doInBackground(Requisition... params) {

                        t=RequisitionController.updateRequisition(params[0]);

                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result)
                    {
                            Toast.makeText(ViewPendingRequisitionDetailsActivity.this,"Requisition Rejected",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ViewPendingRequisitionDetailsActivity.this, HeadManageRequisitionActivity.class);
                            startActivity(i);
                    }
                }.execute(req);

            }
        };
        button_reject.setOnClickListener(ocl_reject);

    }

}
