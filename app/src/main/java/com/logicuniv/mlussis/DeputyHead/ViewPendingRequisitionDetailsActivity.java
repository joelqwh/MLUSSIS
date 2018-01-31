package com.logicuniv.mlussis.DeputyHead;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.EmailController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Backend.RequisitionController;
import com.logicuniv.mlussis.Backend.RequisitionDetailController;
import com.logicuniv.mlussis.Employee.RequisitionEmployeeArrayAdapter;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.Model.EmailTemplate;
import com.logicuniv.mlussis.Model.Employee;
import com.logicuniv.mlussis.Model.Requisition;
import com.logicuniv.mlussis.Model.RequisitionDetail;
import com.logicuniv.mlussis.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewPendingRequisitionDetailsActivity extends MLussisActivity{

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

        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);

        tv_empname = findViewById(R.id.textView_pending_req_empname);
        final TextView tv_reqNo = findViewById(R.id.textView_pending_req_no);
        final ListView lv_reqdetails = findViewById(R.id.listView_pending_req_details);

        View header = getLayoutInflater().inflate(R.layout.header_row_list_requisition_employee,null);
        lv_reqdetails.addHeaderView(header,null,false);

        Button button_approve = findViewById(R.id.button_pending_req_approve);
        Button button_reject = findViewById(R.id.button_pending_req_reject);
        final EditText editText_remarks = findViewById(R.id.editText_pending_req_remarks);


        Intent i = getIntent();
        reqNo = i.getExtras().getString("Req");

        new AsyncTask<String, Void, Void>() {

            ArrayList<Requisition> alr;
            @Override
            protected Void doInBackground(String... params) {
                req = RequisitionController.getRequisitionById(params[0]);
                al_rd = RequisitionDetailController.getRequisitionDetail(params[0]);
                return null;
            }
            protected void onPostExecute(Void result) {

                tv_empname.setText(getString(R.string.text_pendingrep_empname, EmployeeController.getEmployeeName(req.get("IssuedBy").toString())));

                //when employee table is up, use the appropraite method

                adapt = new RequisitionEmployeeArrayAdapter(ViewPendingRequisitionDetailsActivity.this,al_rd);
                tv_reqNo.setText(getString(R.string.text_pendingrep_reqno,req.get("ReqNo")));
                lv_reqdetails.setAdapter(adapt);

            }
        }.execute(reqNo);



        View.OnClickListener ocl_approve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Void>() {

                    ArrayList<Requisition> alr;
                    @Override
                    protected Void doInBackground(Void... params) {
                        req.put("ApprovedBy", LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));
                        //req.put("ApprovedBy", LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));   //get HeadID from login
//                req.put("DateReviewed", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                        req.put("Status","Approved");

                        if(editText_remarks.getText()!=null)
                        {
                            req.put("Remarks",editText_remarks.getText().toString());
                        }

                        RequisitionController.updateRequisition(req);

                        String empNo = req.get("IssuedBy").toString();
                        Employee employee = EmployeeController.getEmployeeById(empNo);
                        String empEmail = employee.get("Email").toString();
                        String empName = employee.get("EmpName").toString();

                        EmailController.sendEmail(empEmail,
                                EmailTemplate.GenerateRequisitionStatusChangedEmailSubject(reqNo,req.get("Status").toString()),
                                EmailTemplate.GenerateRequisitionStatusChangedEmail
                                        (empName,reqNo,EmployeeController.getEmployeeName(LoginController.GetLoggedInEmployeeNumber(getApplicationContext())),
                                                req.get("Status").toString(),req.get("Remarks").toString()));
                        return null;
                    }
                    protected void onPostExecute(Void result) {

                        Toast.makeText(ViewPendingRequisitionDetailsActivity.this,"Requisition Approved",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }.execute();


            }
        };
        button_approve.setOnClickListener(ocl_approve);

        View.OnClickListener ocl_reject = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Void>() {

                    ArrayList<Requisition> alr;
                    @Override
                    protected Void doInBackground(Void... params) {
                        req.put("ApprovedBy",LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));   //get HeadID from login
                        req.put("Status","Rejected");


                        if(editText_remarks!=null)
                        {
                            req.put("Remarks",editText_remarks.getText().toString());
                        }

                        RequisitionController.updateRequisition(req);

                        String empNo = req.get("IssuedBy").toString();
                        Employee employee = EmployeeController.getEmployeeById(empNo);
                        String empEmail = employee.get("Email").toString();
                        String empName = employee.get("EmpName").toString();

                        EmailController.sendEmail(empEmail,
                                EmailTemplate.GenerateRequisitionStatusChangedEmailSubject(reqNo,req.get("Status").toString()),
                                EmailTemplate.GenerateRequisitionStatusChangedEmail
                                        (empName,reqNo,EmployeeController.getEmployeeName(LoginController.GetLoggedInEmployeeNumber(getApplicationContext())),
                                                req.get("Status").toString(),req.get("Remarks").toString()));

                    return null;
                    }
                    protected void onPostExecute(Void result) {
                        Toast.makeText(ViewPendingRequisitionDetailsActivity.this,"Requisition Rejected",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }.execute();

            }
        };
        button_reject.setOnClickListener(ocl_reject);

    }

}
