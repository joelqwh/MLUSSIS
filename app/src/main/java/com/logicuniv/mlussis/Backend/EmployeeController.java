package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.Model.Employee;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class EmployeeController {

    public static Employee getEmployeeById (String employeeId)
    {
        Employee result = null;

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("empNo", employeeId);

            jsonResult = new JSONObject(JSONParser.postStream(App.WCFServer + "Employee", jsonObject.toString()));

            result = new Employee(
                    jsonResult.getString("EmpNo"),
                    jsonResult.getString("EmpName"),
                    jsonResult.getString("DeptCode"),
                    jsonResult.getString("Email"),
                    jsonResult.getString("SessionNo"),
                    new SimpleDateFormat("d/MM/yyyy").parse(jsonResult.getString("SessionExpiry")));
        } catch (Exception e) {
            Log.e("Employee.", e.getMessage());
        }

        return result;
    }

    public static String getEmployeeName(String employeeId)
    {
        return getEmployeeById(employeeId).get("EmpName").toString();
    }

}
