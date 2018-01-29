package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Department;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class DepartmentController {

    public static Department getDepartmentById(String deptCode) {
        Department result = null;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("deptCode", deptCode);

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
            Log.e("DepartmentController", e.getMessage());
        }

        return result;
    }

    public static String getDepartmentName(String deptCode) {

        return getDepartmentById(deptCode).get("DeptName");
    }

    public static ArrayList<Department> getAllDepartments() {
        ArrayList<Department> listDept = new ArrayList<Department>();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            JSONArray deptlist = new JSONArray(JSONParser.postStream(App.WCFServer+"DeptListAll", jsonObject.toString()));
            Log.d("getAllDept", App.WCFServer+"DeptList");
            Log.d("getAllDept", jsonObject.toString());

            for (int i=0; i<deptlist.length();i++){
                JSONObject deptlistItem = deptlist.getJSONObject(i);
                listDept.add(new Department(
                        deptlistItem.getString("DeptCode"),
                        deptlistItem.getString("DeptName"),
                        deptlistItem.getString("ContactName"),
                        deptlistItem.getString("PhoneNo"),
                        deptlistItem.getString("FaxNo"),
                        deptlistItem.getString("HeadEmpNo"),
                        deptlistItem.getString("CollectionPointNo"),
                        deptlistItem.getString("RepEmpNo"),
                        deptlistItem.getString("DeputyEmpNo")));
            }
        }
        catch (Exception e) {
            Log.e("Department.getAllDept()","JSONArray error");
        }
        return listDept;
    }
}
