package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Department;

import org.json.JSONObject;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class DepartmentController {

    public Department getDepartmentById(String deptCode) {
        Department result = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("collectionPointNo", deptCode);

            jsonResult = new JSONObject(JSONParser.postStream(App.WCFServer + "Department", jsonObject.toString()));

            result = new Department(
                    jsonResult.getString("DeptCode"),
                    jsonResult.getString("DeptName"),
                    jsonResult.getString("ContactName"),
                    jsonResult.getString("PhoneNo"),
                    jsonResult.getString("FaxNo"),
                    jsonResult.getString("HeadEmpNo"),
                    jsonResult.getString("CollectionPointNo"),
                    jsonResult.getString("RepEmpNo"),
                    jsonResult.getString("DeputyEmpNo"));
        } catch (Exception e) {
            Log.e("CollectionPointControl", e.getMessage());
        }

        return result;
    }

    public String getDepartmentName(String deptCode) {

        return getDepartmentById(deptCode).get("DeptName");
    }
}
