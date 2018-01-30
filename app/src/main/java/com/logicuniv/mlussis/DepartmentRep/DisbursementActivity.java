package com.logicuniv.mlussis.DepartmentRep;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.logicuniv.mlussis.R;

public class DisbursementActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement);

        setTitle("Disbursement");
    }
}
