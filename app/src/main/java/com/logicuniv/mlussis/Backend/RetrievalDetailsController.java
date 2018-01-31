package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Retrieval;
import com.logicuniv.mlussis.Model.RetrievalDetails;
import com.logicuniv.mlussis.StationeryCatalogue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ET on 26/1/2018.
 */

public class RetrievalDetailsController {

    public static ArrayList<RetrievalDetails> getRetrievalDetails(String retrievalNo) {
        ArrayList<RetrievalDetails> result = new ArrayList<RetrievalDetails>();

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("RetrievalNo", retrievalNo);

            jsonResult = new JSONArray(JSONParser.postStream(App.WCFServer + "RetrievalDetails", jsonObject.toString()));

            for (int i = 0; i < jsonResult.length(); i++) {
                result.add(new RetrievalDetails(
                        jsonResult.getJSONObject(i).getString("RetrievalNo"),
                        jsonResult.getJSONObject(i).getString("ItemNo"),
                        jsonResult.getJSONObject(i).getString("Description"),
                        jsonResult.getJSONObject(i).getString("Bin"),
                        jsonResult.getJSONObject(i).getString("Needed"),
                        jsonResult.getJSONObject(i).getString("BackLogQty"),
                        jsonResult.getJSONObject(i).getString("Actual")));
            }
        } catch (Exception e) {
            Log.e("RetrievalDetails", e.getMessage());
        }
        return result;
    }
}
