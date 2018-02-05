package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.R;

import java.sql.Connection;

public class InventoryActivity extends MLussisActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventory");

    }
}
