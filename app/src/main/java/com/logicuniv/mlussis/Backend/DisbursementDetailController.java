package com.logicuniv.mlussis.Backend;

import com.logicuniv.mlussis.Model.DisbursementDetail;

import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class DisbursementDetailController {

    public static ArrayList<DisbursementDetail> getCurrentDisbursementDetailsForDepartment()
    {
        //JSONWAY
        //        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "disbursement/" + eid);
//        try {
//            return new Disbursement(b.getString("Id"), b.getString("Title"), b.getString("Category"), b.getString("ISBN"), b.getString("Author"), b.getString("Price"));
//        } catch (Exception e) {
//            Log.e("Book.getBook()", "JSONArray error");
//        }
//        return (null);


        //ACTUAL FUNCTION WALKTHROUGH
        // Disbursement dis = Disbursement.getCurrentDisbursementForDepartment("1");       //"1" is actually the employee department
        //String disburseNo = dis.get("disbNo");
        //ArrayList<DisbursementDetail> allAldd = getAllDisbursementDetails(disburseNo);     //to add as function*************



        ArrayList<DisbursementDetail> aldd= new ArrayList<DisbursementDetail>();   //dummy values
        DisbursementDetail d1 = new DisbursementDetail("1", "I001", "10", "9", "9"); //dummy values
        DisbursementDetail d2 = new DisbursementDetail("1","I002","2","1","1"); //dummy values
        aldd.add(d1); //dummy values
        aldd.add(d2); //dummy values
        return aldd;
    }

    public void changeDisbursementDetails(String disbursementNo)
    {

    }

}
