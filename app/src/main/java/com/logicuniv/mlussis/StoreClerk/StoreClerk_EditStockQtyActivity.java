package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                Toast.makeText(StoreClerk_EditStockQtyActivity.this,"Submitted for Adjustment",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
