package com.logicuniv.mlussis.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by e0231991 on 18/1/2018.
 */

public class StockTxnDetail extends HashMap<String, String> implements Serializable{

    public StockTxnDetail(String stockTxnNo, String itemNo, String date, String adjustQty, String recordedQty, String remarks) {
        put("StockTxnNo", stockTxnNo);
        put("ItemNo", itemNo);
        put("Date", date);
        put("AdjustQty", adjustQty);
        put("RecordedQty", recordedQty);
        put("Remarks", remarks);
    }
}
