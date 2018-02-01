package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Disbursement;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.Model.Employee;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class DisbursementDetailController {

    public static ArrayList<DisbursementDetail> getCurrentDisbursementDetailsForDepartment() {
        String DisbursementNo = getCurrentDisbursementNoForDepartment();
        return getCurrentDisbursementDetailsOf(DisbursementNo);
    }

    private static String getCurrentDisbursementNoForDepartment() {
        String result = null;

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            result = JSONParser.postStream(App.WCFServer + "CurrentDisbursementNo", jsonObject.toString()).trim();
            result = result.substring(1, result.length()-1).trim();
        } catch (Exception e) {
            Log.e("DisbursementDetailCont.", e.getMessage());
        }

        return result;
    }

    public static boolean UpdateDisbursementDetail(DisbursementDetail updatedDisbursementDetail){
        Boolean result = false;

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonDisbursementDetail = new JSONObject();
        String response;

        try {
            jsonDisbursementDetail.put("DisbursementNo", updatedDisbursementDetail.get("DisbursementNo"));
            jsonDisbursementDetail.put("ItemNo", updatedDisbursementDetail.get("ItemNo"));
            jsonDisbursementDetail.put("Needed", updatedDisbursementDetail.get("Needed"));
            jsonDisbursementDetail.put("Promised", updatedDisbursementDetail.get("Promised"));
            jsonDisbursementDetail.put("Received", updatedDisbursementDetail.get("Received"));


            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.putOpt("updatedDisbursementDetail", jsonDisbursementDetail);

            response = JSONParser.postStream(App.WCFServer + "UpdateDisbursementDetail", jsonObject.toString());

            result = response.equals("true");
        } catch (Exception e) {
            Log.e("DisbursementDetailCont.", e.getMessage());
            result = false;
        }

        return result;
    }


    public static ArrayList<DisbursementDetail> getCurrentDisbursementDetailsOf(String DisbursementNo) {
        ArrayList<DisbursementDetail> result = new ArrayList<>();

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("DisbursementNo", DisbursementNo);

            jsonResult = new JSONArray(JSONParser.postStream(App.WCFServer + "DisbursementDetails", jsonObject.toString()));

            for (int i = 0; i < jsonResult.length(); i++) {
                result.add(new DisbursementDetail(
                        jsonResult.getJSONObject(i).getString("DisbursementNo"),
                        jsonResult.getJSONObject(i).getString("ItemNo"),
                        jsonResult.getJSONObject(i).getString("Description"),
                        jsonResult.getJSONObject(i).getString("Needed"),
                        jsonResult.getJSONObject(i).getString("Promised"),
                        jsonResult.getJSONObject(i).getString("Received")));
            }
        } catch (Exception e) {
            Log.e("DisbursementDetailCont.", e.getMessage());
        }

        return result;
    }
}
