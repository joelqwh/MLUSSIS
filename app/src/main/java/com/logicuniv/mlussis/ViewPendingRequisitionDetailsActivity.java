package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.RequisitionController;
import com.logicuniv.mlussis.Backend.RequisitionDetailController;
import com.logicuniv.mlussis.Model.Requisition;
import com.logicuniv.mlussis.Model.RequisitionDetail;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewPendingRequisitionDetailsActivity extends Activity{

    RequisitionEmployeeArrayAdapter adapt;
    Requisition req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending_requisition_details);
        setTitle("Requisition Details");

        Intent i = getIntent();
        req = RequisitionController.getRequisitionById((String)i.getExtras().get("Req"));
        ArrayList<RequisitionDetail> al_rd = RequisitionDetailController.getRequisitionDetail((String)req.get("ReqNo"));

        TextView tv_empname = findViewById(R.id.textView_pending_req_empname);
        TextView tv_reqNo = findViewById(R.id.textView_pending_req_no);
        ListView lv_reqdetails = findViewById(R.id.listView_pending_req_details);
        Button button_approve = findViewById(R.id.button_pending_req_approve);
        Button button_reject = findViewById(R.id.button_pending_req_reject);
        final EditText editText_remarks = findViewById(R.id.editText_pending_req_remarks);

        tv_empname.setText(getString(R.string.text_pendingrep_empname,req.get("IssuedBy")));      //when employee table is up, use the appropraite method
        tv_reqNo.setText(getString(R.string.text_pendingrep_reqno,req.get("ReqNo")));
        adapt = new RequisitionEmployeeArrayAdapter(this,al_rd);
        lv_reqdetails.setAdapter(adapt);

        View.OnClickListener ocl_approve = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.put("ApprovedBy","E002");   //get HeadID from login
                req.put("DateReviewed", Calendar.getInstance().getTime());
                req.put("Status","Approved");

                if(editText_remarks!=null)
                {
                    req.put("Remarks",editText_remarks.getText().toString());
                }

                //RequisitionController.updateRequisition(req);
                Toast.makeText(ViewPendingRequisitionDetailsActivity.this,"Requisition Approved",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ViewPendingRequisitionDetailsActivity.this, HeadManageRequisitionActivity.class);
                startActivity(i);

            }
        };
        button_approve.setOnClickListener(ocl_approve);

        View.OnClickListener ocl_reject = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.put("ApprovedBy","E002");   //get HeadID from login
                req.put("DateReviewed", Calendar.getInstance().getTime());
                req.put("Status","Rejected");


                if(editText_remarks!=null)
                {
                    req.put("Remarks",editText_remarks.getText().toString());
                }

                //RequisitionController.updateRequisition(req);

                Toast.makeText(ViewPendingRequisitionDetailsActivity.this,"Requisition Rejected",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ViewPendingRequisitionDetailsActivity.this, HeadManageRequisitionActivity.class);
                startActivity(i);
            }
        };
        button_reject.setOnClickListener(ocl_reject);




    }

}
