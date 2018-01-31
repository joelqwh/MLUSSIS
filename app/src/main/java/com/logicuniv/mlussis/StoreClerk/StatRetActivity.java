package com.logicuniv.mlussis.StoreClerk;

/**
 * Created by ET on 25/1/2018.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.R;

import java.sql.Connection;

public class StatRetActivity extends MLussisActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval);

    }
}
