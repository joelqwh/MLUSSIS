package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.StockTxnDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class StockTxnDetailController {

    public static ArrayList<StockTxnDetail> getStockTxnDetails(String itemNo)
    {
        ArrayList<StockTxnDetail> result = new ArrayList<>();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("ItemNo", itemNo);
            Log.d("StockTxnDetail",App.WCFServer+"StockTxnDetails");
            Log.d("StockTxnDetail",jsonObject.toString());

            JSONArray jsonArray = new JSONArray (JSONParser.postStream(App.WCFServer + "StockTxnDetails", jsonObject.toString()));

            for (int i=0; i<jsonArray.length(); i++){
                JSONObject stockTxnDetail = jsonArray.getJSONObject(i);
                result.add(new StockTxnDetail(
                        stockTxnDetail.getString("StockTxnNo"),
                        stockTxnDetail.getString("ItemNo"),
                        stockTxnDetail.getString("Date"),
                        stockTxnDetail.getString("AdjustQty"),
                        stockTxnDetail.getString("RecordedQty"),
                        stockTxnDetail.getString("Remarks")
                ));
            }
        }
        catch (Exception e)
        {
            Log.e("StockTxnDetail", e.getMessage());
        }

        return result;
    }
}
