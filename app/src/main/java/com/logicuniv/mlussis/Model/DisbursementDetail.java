package com.logicuniv.mlussis.Model;

/**
 * Created by e0231991 on 18/1/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;


public class DisbursementDetail extends HashMap<String, String> {

    //final static String baseURL = "http://172.17.252.67/Bookshop13/Service.svc/"; //to change if not will fail
    static ArrayList<ArrayList<DisbursementDetail>> disbursementdetailsalal = new ArrayList<>();

    public DisbursementDetail(String disbNo, String itemNo , String description, String need, String promise, String received) {
        put("DisbursementNo", disbNo);
        put("ItemNo", itemNo);             //getItemName(itemNo)
        put("Description", description);
        put("Needed", need);
        put("Promised", promise);
        put("Received", received);
    }



    //ALL AFTER THIS TO BE DELETED
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



        ArrayList<DisbursementDetail> aldd= new ArrayList<>();   //dummy values
        //DisbursementDetail d1 = new DisbursementDetail("1", "I001", "10", "9", "9"); //dummy values
        //DisbursementDetail d2 = new DisbursementDetail("1","I002","2","1","1"); //dummy values
        //aldd.add(d1); //dummy values
        //aldd.add(d2); //dummy values
        return aldd;
    }

    public static void addDisbursement(ArrayList<DisbursementDetail> ddd){
        disbursementdetailsalal.add(ddd);
    }

    public static ArrayList<DisbursementDetail> getDisbursementDetails(String rnum)
    {
        ArrayList<DisbursementDetail> aldddd = getCurrentDisbursementDetailsForDepartment();
        addDisbursement(aldddd);

        ArrayList<DisbursementDetail> ddpack = new ArrayList<>();
        for (ArrayList<DisbursementDetail> x:disbursementdetailsalal)
        {
            for (DisbursementDetail det:x)
            {
                if (det.get("DisbursementNo").equals(rnum))
                {
                    ddpack.add(det);
                }
            }
        }
        return ddpack;
    }
}


