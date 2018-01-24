package com.logicuniv.mlussis;

/**
 * Created by e0231991 on 18/1/2018.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class DisbursementDetails extends HashMap<String, String> implements Serializable {

    //final static String baseURL = "http://172.17.252.67/Bookshop13/Service.svc/"; //to change if not will fail
    static ArrayList<ArrayList<DisbursementDetails>> disbursementdetailsalal = new ArrayList<>();

    public DisbursementDetails (String disbNo, String itemNo ,String need, String promise, String received) {
        put("DisbursementNo", disbNo);
        put("ItemNo", itemNo);             //getItemName(itemNo)
        put("Needed", need);
        put("Promised", promise);
        put("Received", received);
    }

    public static ArrayList<DisbursementDetails> getCurrentDisbursementDetailsForDepartment()
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
       //ArrayList<DisbursementDetails> allAldd = getAllDisbursementDetails(disburseNo);     //to add as function*************



        ArrayList<DisbursementDetails> aldd= new ArrayList<DisbursementDetails>();   //dummy values
           DisbursementDetails d1 = new DisbursementDetails("1", "I001", "10", "9", "9"); //dummy values
            DisbursementDetails d2 = new DisbursementDetails("1","I002","2","1","1"); //dummy values
            aldd.add(d1); //dummy values
            aldd.add(d2); //dummy values
        return aldd;
    }

    public static void addDisbursement(ArrayList<DisbursementDetails> ddd){
        disbursementdetailsalal.add(ddd);
    }

    public static ArrayList<DisbursementDetails> getDisbursementDetails(String rnum)
    {
        ArrayList<DisbursementDetails> aldddd = getCurrentDisbursementDetailsForDepartment();
        addDisbursement(aldddd);

        ArrayList<DisbursementDetails> ddpack = new ArrayList<>();
        for (ArrayList<DisbursementDetails> x:disbursementdetailsalal)
        {
            for (DisbursementDetails det:x)
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


