package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Retrieval;
import com.logicuniv.mlussis.Model.RetrievalDetails;
import com.logicuniv.mlussis.StationeryCatalogue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ET on 26/1/2018.
 */

public class RetrievalDetailsController {

    public static ArrayList<RetrievalDetails> getRetrievalDetails(String retrievalNo) {
        ArrayList<RetrievalDetails> result = new ArrayList<RetrievalDetails>();

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("RetrievalNo", retrievalNo);

            jsonResult = new JSONArray(JSONParser.postStream(App.WCFServer + "RetrievalDetails", jsonObject.toString()));

            for (int i = 0; i < jsonResult.length(); i++) {
                result.add(new RetrievalDetails(
                        jsonResult.getJSONObject(i).getString("RetrievalNo"),
                        jsonResult.getJSONObject(i).getString("DeptCode"),
                        jsonResult.getJSONObject(i).getString("ItemNo"),
                        jsonResult.getJSONObject(i).getString("Needed"),
                        jsonResult.getJSONObject(i).getString("BackLogQty"),
                        jsonResult.getJSONObject(i).getString("Actual")));
            }
        } catch (Exception e) {
            Log.e("RetrievalDetails", e.getMessage());
        }
        return result;
    }

}




    /*public static ArrayList<RetrievalDetails> getRetrievalDetails1() {
        //getRetrieval() JSON Parser get as function
        ArrayList<RetrievalDetails> retda = new ArrayList<>();
        RetrievalDetails rtd1 = new RetrievalDetails("1", "COMM","C001", "10","0","10");
        RetrievalDetails rtd2 = new RetrievalDetails("1", "CPSC", "C001", "20","0","20");
        RetrievalDetails rtd3 = new RetrievalDetails("1", "REGR", "C001","15","0","15");
        RetrievalDetails rtd4 = new RetrievalDetails("1", "ENGL", "C001", "5","0","5");
        RetrievalDetails rtd5 = new RetrievalDetails("1", "COMM", "C002", "30","0","30");
        RetrievalDetails rtd6 = new RetrievalDetails("1", "REGR", "C002", "20","0","20");
        RetrievalDetails rtd7 = new RetrievalDetails("1", "ENGL", "C002", "10","0","10");
        RetrievalDetails rtd8 = new RetrievalDetails("1", "REGR", "C003", "15","0","15");
        RetrievalDetails rtd9 = new RetrievalDetails("1", "ZOOL", "C003", "5","0","5");
        RetrievalDetails rtd10 = new RetrievalDetails("2", "COMM", "C004", "40","0","40");
        RetrievalDetails rtd11 = new RetrievalDetails("2", "REGR", "C004", "30","0","30");
        RetrievalDetails rtd12 = new RetrievalDetails("2", "ENGL", "C004", "20","0","20");
        RetrievalDetails rtd13 = new RetrievalDetails("2", "REGR", "C005", "10","0","10");
        RetrievalDetails rtd14 = new RetrievalDetails("2", "ZOOL", "C005", "25","0","25");
        retda.add(rtd1);
        retda.add(rtd2);
        retda.add(rtd3);
        retda.add(rtd4);
        retda.add(rtd5);
        retda.add(rtd6);
        retda.add(rtd7);
        retda.add(rtd8);
        retda.add(rtd9);
        retda.add(rtd10);
        retda.add(rtd11);
        retda.add(rtd12);
        retda.add(rtd13);
        retda.add(rtd14);
        return retda;

    }*/


    /*
    Clips Double 1"
    Clips Double 2"
    Clips Double 3/4"
    Clips Paper Large
    Clips Paper Medium
    Clips Paper Small
    Envelope Brown (3"x6")
    Envelope Brown (3"x6") w/ Window
    Envelope Brown (5"x7")

    COMM
            CPSC
    ENGL
            REGR
    STOR
            ZOOL

    String binNo, String description, String needed, String uom, String backlog, String actual */

    /*        ArrayList<RetrievalDetails> retd1 = new ArrayList<>();
        RetrievalDetails rtdl = new RetrievalDetails("1", "Clips Double 1");
        RetrievalDetails rtd2 = new RetrievalDetails("1", "Pilot");
        RetrievalDetails rtd3 = new RetrievalDetails("1", "Cniballkhlasdjhkgkdslfahkgsadgkjlasdkfjaksdjlhfka");
        RetrievalDetails rtd4 = new RetrievalDetails("1", "Clips 2");
        RetrievalDetails rtd5 = new RetrievalDetails("1", "Clips 3");
        RetrievalDetails rtd6 = new RetrievalDetails("1", "Clips 4");
        RetrievalDetails rtd7 = new RetrievalDetails("1", "Clips 5");
        RetrievalDetails rtd8 = new RetrievalDetails("1", "Clips 6");
        RetrievalDetails rtd9 = new RetrievalDetails("1", "Clips 7");
        retd.add(rtd1);
        retd.add(rtd2);
        retd.add(rtd3);
d       retd.add(rtd4);
        retd.add(rtd5);
        retd.add(rtd6);
        retd.add(rtd7);
        retd.add(rtd8);
        retd.add(rtd9);
        return retd; */


   /*66 public static ArrayList<Retrieval> searchCatalogue(Retrieval sc) {
        //searchJSONFromUrl url will settle
        ArrayList<com.logicuniv.mlussis.StationeryCatalogue> alscc = getCatalogue(); //dummy values
        ArrayList<com.logicuniv.mlussis.StationeryCatalogue> alsccfake = new ArrayList<>();

        for (com.logicuniv.mlussis.StationeryCatalogue scdummy : alscc) {
            if (sc.get("Description").contains(scdummy.get("Description")) == true) {
                alsccfake.add(scdummy);
            }
        }

        return alsccfake;
    }

    public static com.logicuniv.mlussis.StationeryCatalogue searchCatalogueById(String itemNo) {
        //searchJSONFromUrl url will settle
        ArrayList<com.logicuniv.mlussis.StationeryCatalogue> alscc = getCatalogue(); //dummy values
        com.logicuniv.mlussis.StationeryCatalogue sc = null;

        for (com.logicuniv.mlussis.StationeryCatalogue scdummy : alscc) {
            if (scdummy.get("ItemNo").equals(itemNo)) {
                sc = scdummy;
            }
        }
        return sc;
    }*/
