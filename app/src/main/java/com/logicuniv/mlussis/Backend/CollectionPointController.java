package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.R;

import org.json.JSONObject;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class CollectionPointController {

    public String getCollectionPointDetails(String collectionPointNo) {
        String result = null;
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("collectionPointNo", collectionPointNo);
            Log.e("joel",jsonObject.toString());
            Log.e("joel",App.WCFServer + "CollectionPoint");
            result = JSONParser.postStream(App.WCFServer + "CollectionPoint", jsonObject.toString());
        } catch (Exception e) {
            Log.e("CollectionPointControl", e.getMessage());
        }

        return result;
    }
}
