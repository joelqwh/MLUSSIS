package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Retrieval;
import com.logicuniv.mlussis.Model.RetrievalDetails;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ET on 26/1/2018.
 */

public class RetrievalController {

    public static Retrieval getLatestRetrieval() {
        Retrieval result = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));

            jsonResult = new JSONObject(JSONParser.postStream(App.WCFServer + "LatestRetrieval", jsonObject.toString()));

            result = new Retrieval(
                    jsonResult.getString("RetrievalNo"),
                    jsonResult.getString("Date"));
        } catch (Exception e) {
            Log.e("RetrievalController", e.getMessage());
        }

        return result;
    }

}