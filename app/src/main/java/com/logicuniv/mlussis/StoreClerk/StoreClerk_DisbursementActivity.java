package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.DisbursementDetails;
import com.logicuniv.mlussis.R;
import com.logicuniv.mlussis.StationeryCatalogue;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StoreClerk_DisbursementActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk_disbursement);

        Button b = (Button) findViewById(R.id.confirmDisburseQtybtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ArrayList<StationeryCatalogue> sc = StationeryCatalogue.getCatalogue();
                //EditText et_recqty = (EditText) findViewById(R.id.scDisburseItemQty);
                //String recqty = et_recqty.getText().toString();
                //TextView disburse_itemNo = (TextView) findViewById(R.id.scDisburseItemDesc);
                //String itemNo = disburse_itemNo.getText().toString();

                Intent intent = new Intent(getApplicationContext(),StoreClerk_ConfirmationActivity.class);
                Spinner scDeptName = (Spinner) findViewById(R.id.scDisburseDeptspinner);
                //intent.putExtra("deptName", scDeptName.getSelectedItem().toString());
                TextView scRepName = (TextView) findViewById(R.id.scDisburseRepText);
                //intent.putExtra("repName", scRepName.getText().toString());
                Bundle b = new Bundle();
                b.putSerializable("deptName", scDeptName.getSelectedItem().toString());
                b.putSerializable("repName", scRepName.getText().toString());
                String disbNo = DisbursementDetails.getCurrentDisbursementDetailsForDepartment().get(0).get("DisbursementNo").toString();
                b.putSerializable("disbNo", disbNo);
                intent.putExtra("bundle", b);
                //Toast.makeText(getApplicationContext(),disbNo,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }



}
