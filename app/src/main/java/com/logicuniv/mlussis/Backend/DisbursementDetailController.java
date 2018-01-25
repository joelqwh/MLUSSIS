package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Disbursement;
import com.logicuniv.mlussis.Model.DisbursementDetail;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class DisbursementDetailController {

    public static ArrayList<DisbursementDetail> getCurrentDisbursementDetailsForDepartment(){
        return null;
    }

    public void changeDisbursementDetails(String disbursementNo)
    {

    }

    public static ArrayList<DisbursementDetail> getCurrentDisbursementDetailsOf(String DisbursementNo)
    {
        ArrayList<DisbursementDetail> result = new ArrayList<>();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("collectionPointNo", DisbursementNo);

            jsonResult = new JSONObject(JSONParser.postStream(App.WCFServer + "LatestDisbursement", jsonObject.toString()));

            result.add(new DisbursementDetail(
                    jsonResult.getString("DisbursementNo"),
                    jsonResult.getString("ItemNo"),
                    jsonResult.getString("Needed"),
                    jsonResult.getString("Promised"),
                    jsonResult.getString("Received")));
        } catch (Exception e) {
            Log.e("DisbursementDetailCont.", e.getMessage());
        }

        return result;
    }
}
