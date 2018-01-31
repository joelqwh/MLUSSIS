package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.R;
import com.logicuniv.mlussis.Model.StationeryCatalogue;

import java.util.HashMap;

public class StoreClerk_StockCardActivity extends MLussisActivity {
    HashMap<String, String> sc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk__stock_card_);
        Bundle b = getIntent().getExtras();
        sc = (HashMap<String, String>) b.get("invdetails1");
        final TextView itemCode = findViewById(R.id.textViewItemCode);
        TextView itemDesc = findViewById(R.id.textViewItemDesc);
        TextView itemBin = findViewById(R.id.textViewItemBin);
        TextView itemUOM = findViewById(R.id.textViewItemUOM);
        TextView itemSupp1 = findViewById(R.id.textViewItemSupp1);
        TextView itemSupp2 = findViewById(R.id.textViewItemSupp2);
        TextView itemSupp3 = findViewById(R.id.textViewItemSupp3);

        itemCode.setText(sc.get("ItemNo"));
        itemDesc.setText(sc.get("Description"));
        itemBin.setText(sc.get("Bin"));
        itemUOM.setText(sc.get("Uom"));
        itemSupp1.setText(sc.get("Supplier1"));
        itemSupp2.setText(sc.get("Supplier2"));
        itemSupp3.setText(sc.get("Supplier3"));

        //use adapter to pull in Txn History

        Button itemStockEditBtn = (Button) findViewById(R.id.itemStockEditbtn);
        itemStockEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String,Void,StationeryCatalogue>(){
                    @Override
                    protected StationeryCatalogue doInBackground(String... params){
                        return StationeryCatalogueController.searchCatalogueById(params[0]);
                    }
                    @Override
                    protected void onPostExecute(StationeryCatalogue result){
                        StationeryCatalogue sc1 = result;
                        Intent intent = new Intent(getApplicationContext(),StoreClerk_EditStockQtyActivity.class);
                        intent.putExtra("invdetails", sc1);
                        startActivity(intent);
                    }
                }.execute(sc.get("ItemNo"));
            }
        });
    }
}
