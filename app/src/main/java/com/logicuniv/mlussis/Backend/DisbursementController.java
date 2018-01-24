package com.logicuniv.mlussis.Backend;

import com.logicuniv.mlussis.Model.Disbursement;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class DisbursementController {
    public static Disbursement getCurrentDisbursementForDepartment(String deptCode) {
//        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "disbursement/" + eid);
//        try {
//            return new Disbursement(b.getString("Id"), b.getString("Title"), b.getString("Category"), b.getString("ISBN"), b.getString("Author"), b.getString("Price"));
//        } catch (Exception e) {
//            Log.e("Book.getBook()", "JSONArray error");
//        }
//        return (null);

        //use and edit above when JSON is up , inform WCF that the parameters are status "unfulfilled" or date is later than start date. what happens when disbursement is 2?

        return new Disbursement("1", "D1", "28 January 2018", "E1", "CP1", "12345", "Unfulfilled");
    }
}
