package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.logicuniv.mlussis.R;

public class StoreClerk_EditStockQtyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk__edit_stock_qty);

        Button itemAdjSubmitBtn = (Button) findViewById(R.id.itemAdjSubmitbtn);
        itemAdjSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText adjReason = (EditText) findViewById(R.id.editTextAdjReason);
                if (adjReason.getText().toString().matches("")) {
                    Toast.makeText(StoreClerk_EditStockQtyActivity.this, "Please enter adjustment reason", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(StoreClerk_EditStockQtyActivity.this, "Submitted for Adjustment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
