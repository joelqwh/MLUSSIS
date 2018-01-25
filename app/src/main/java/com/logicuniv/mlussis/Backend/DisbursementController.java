package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Department;
import com.logicuniv.mlussis.Model.Disbursement;

import org.json.JSONObject;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class DisbursementController {
    public static Disbursement getCurrentDisbursementForDepartment(String deptCode) {
        Disbursement result = null;

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("collectionPointNo", deptCode);

            jsonResult = new JSONObject(JSONParser.postStream(App.WCFServer + "LatestDisbursement", jsonObject.toString()));

            result = new Disbursement(
                    jsonResult.getString("DisbursementNo"),
                    jsonResult.getString("DeptCode"),
                    jsonResult.getString("DisbursementDate"),
                    jsonResult.getString("RepEmpNo"),
                    jsonResult.getString("CollectionPointNo"),
                    jsonResult.getString("Pin"),
                    jsonResult.getString("Status"));
        } catch (Exception e) {
            Log.e("DisbursementController", e.getMessage());
        }

        return result;
    }
}
