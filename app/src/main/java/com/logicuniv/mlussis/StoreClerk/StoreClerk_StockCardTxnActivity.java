package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.StockTxnDetailController;
import com.logicuniv.mlussis.Model.StockTxnDetail;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreClerk_StockCardTxnActivity extends Activity {
    HashMap<String, String> sc;
    static ArrayList<StockTxnDetail> stockTxnDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk__stock_card_txn);

        TextView txnItemCode = (TextView) findViewById(R.id.txnItemNo);
        TextView txnItemDesc = (TextView) findViewById(R.id.txnItemDesc);
        View header = getLayoutInflater().inflate(R.layout.header_row_list_itemtxn_storeclerk,null);
        final ListView txnList = (ListView) findViewById(R.id.listView_txn_storeclerk);
        Bundle b = getIntent().getExtras();
        sc = (HashMap<String, String>) b.get("txndetails");
        String selectedItemNo = sc.get("ItemNo");
        txnList.addHeaderView(header, null, false);

        txnItemCode.setText(sc.get("ItemNo"));
        txnItemDesc.setText(sc.get("Description"));

        new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... params){
                stockTxnDetails.clear();
                stockTxnDetails = StockTxnDetailController.getStockTxnDetails(params[0]);

                return null;
            }

            @Override
            protected void onPostExecute(Void result){
                    SimpleAdapter txnadapt = new SimpleAdapter(getApplicationContext(), stockTxnDetails, R.layout.row_list_itemtxn_storeclerk,
                            new String[]{"Date", "Remarks", "AdjustQty"},
                            new int[]{R.id.textView_itemtxn_date, R.id.textView_itemtxn_desc, R.id.textView_itemtxn_adj});
                    txnList.setAdapter(txnadapt);

            }
        }.execute(selectedItemNo);
    }
}
