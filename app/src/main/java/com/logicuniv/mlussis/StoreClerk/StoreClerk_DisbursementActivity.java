package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.R;


public class StoreClerk_DisbursementActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk_disbursement);

        Button b = (Button) findViewById(R.id.confirmDisburseQtybtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StoreClerk_ConfirmationActivity.class);
                Spinner scDeptName = (Spinner) findViewById(R.id.scDisburseDeptspinner);
                TextView scRepName = (TextView) findViewById(R.id.scDisburseRepText);
                Bundle b = new Bundle();
                b.putSerializable("deptName", scDeptName.getSelectedItem().toString());
                b.putSerializable("repName", scRepName.getText().toString());
                String disbNo = DisbursementDetail.getCurrentDisbursementDetailsForDepartment().get(0).get("DisbursementNo");
                b.putSerializable("disbNo", disbNo);
                intent.putExtra("bundle", b);
                startActivity(intent);
            }
        });
    }



}
