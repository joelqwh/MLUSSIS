package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.StationeryCatalogue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class StationeryCatalogueController {


    public static ArrayList<StationeryCatalogue> getCatalogue() {
        ArrayList<StationeryCatalogue> listSC = new ArrayList<StationeryCatalogue>();
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            JSONArray sc = new JSONArray (JSONParser.postStream(App.WCFServer+"Catalogue",jsonObject.toString()));
            Log.d("getCatalogue", App.WCFServer+"Catalogue");
            Log.d("getCatalogue", jsonObject.toString());

            for (int i=0; i<sc.length();i++){
                JSONObject scItem = sc.getJSONObject(i);
                listSC.add(new StationeryCatalogue(
                        scItem.getString("ItemNo"),
                        scItem.getString("Description"),
                        scItem.getString("Category"),
                        scItem.getString("Uom"),
                        scItem.getString("ReorderQty"),
                        scItem.getString("ReorderLevel"),
                        scItem.getString("CurrentQty"),
                        scItem.getString("Supplier1"),
                        scItem.getString("Supplier2"),
                        scItem.getString("Supplier3"),
                        scItem.getString("Bin")));
            }
        }
        catch (Exception e) {
            Log.e("StatCat.getCatalogue()","JSONArray error");
        }
        return listSC;

    }

    /*public static ArrayList<StationeryCatalogue> getCatalogue1()
        {
            //getCatalogue() JSON Parser get as function
            ArrayList<StationeryCatalogue> alsc = new ArrayList<>();
            StationeryCatalogue scl1 = new StationeryCatalogue("I001","Clips 1", "Clip", "Dozen", "A7", "BANES", "CHEP", "ALPA");
            StationeryCatalogue scl2 = new StationeryCatalogue("I002","Pilot", "Pen", "Each", "B8", "CHEP", "OMEG", "BANES");
            StationeryCatalogue scl3 = new StationeryCatalogue("I003","Cniballkhlasdjhkgkdslfahkgsadgkjlasdkfjaksdjlhfka", "Pen", "Each", "C4","ALPA","BANES","CHEP");
            StationeryCatalogue scl4 = new StationeryCatalogue("I004","Clips 2", "Clip", "Dozen", "A7", "BANES", "CHEP", "ALPA");
            StationeryCatalogue scl5 = new StationeryCatalogue("I005","Clips 3", "Clip", "Dozen", "A7", "BANES", "CHEP", "ALPA");
            StationeryCatalogue scl6 = new StationeryCatalogue("I006","Clips 4", "Clip", "Dozen", "A7", "BANES", "CHEP", "ALPA");
            StationeryCatalogue scl7 = new StationeryCatalogue("I007","Clips 5", "Clip", "Dozen", "A7", "BANES", "CHEP", "ALPA");
            StationeryCatalogue scl8 = new StationeryCatalogue("I008","Clips 6", "Clip", "Dozen", "A7", "BANES", "CHEP", "ALPA");
            StationeryCatalogue scl9 = new StationeryCatalogue("I009","Clips 7", "Clip", "Dozen", "A7", "BANES", "CHEP", "ALPA");
            alsc.add(scl1);
            alsc.add(scl2);
            alsc.add(scl3);
            alsc.add(scl4);
            alsc.add(scl5);
            alsc.add(scl6);
            alsc.add(scl7);
            alsc.add(scl8);
            alsc.add(scl9);
            return alsc;
        }*/

    public static ArrayList<StationeryCatalogue> getCatalogueByCategory(String itemNo, String category, String desc, String bin)
    {
        ArrayList<StationeryCatalogue> listSC = new ArrayList<StationeryCatalogue>();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("ItemNo", itemNo);
            jsonObject.put("Category", category);
            jsonObject.put("Description", desc);
            jsonObject.put("Bin", bin);

            JSONArray sc = new JSONArray (JSONParser.postStream(App.WCFServer+"CatalogueSearch",jsonObject.toString()));

            for (int i=0; i<sc.length(); i++){
                JSONObject scItem = sc.getJSONObject(i);
                listSC.add(new StationeryCatalogue(
                        scItem.getString("ItemNo"),
                        scItem.getString("Description"),
                        scItem.getString("Category"),
                        scItem.getString("Uom"),
                        scItem.getString("ReorderQty"),
                        scItem.getString("ReorderLevel"),
                        scItem.getString("CurrentQty"),
                        scItem.getString("Supplier1"),
                        scItem.getString("Supplier2"),
                        scItem.getString("Supplier3"),
                        scItem.getString("Bin")));
            }
        }
        catch (Exception e)
        {
            Log.e("catalogueSearch", e.getMessage());
        }
        return listSC;

    }

    /*public static ArrayList<StationeryCatalogue> searchCatalogueByText(StationeryCatalogue sc) {
        ArrayList<StationeryCatalogue> scList = new ArrayList<StationeryCatalogue>();
        JSONArray scCat = JSONParser.searchJSONArrayFromUrl(App.WCFServer+"search", sc);
        try {
            for (int i=0; i<scCat.length(); i++) {
                JSONObject scItem = scCat.getJSONObject(i);
                scList.add(new StationeryCatalogue(
                        scItem.getString("ItemNo"),
                        scItem.getString("Description"),
                        scItem.getString("Category"),
                        scItem.getString("Uom"),
                        scItem.getString("ReorderQty"),
                        scItem.getString("ReorderLevel"),
                        scItem.getString("CurrentQty"),
                        scItem.getString("Supplier1"),
                        scItem.getString("Supplier2"),
                        scItem.getString("Supplier3"),
                        scItem.getString("Bin")));
            }
        }
        catch (Exception e){
            Log.e("sc.search()", "JSONArray error"+e.toString());
        }
        return scList;
    }*/

        /*public static ArrayList<StationeryCatalogue> searchCatalogue(StationeryCatalogue sc)
        {
            //searchJSONFromUrl url will settle
            ArrayList<StationeryCatalogue> alscc = getCatalogue(); //dummy values
            ArrayList<StationeryCatalogue> alsccfake = new ArrayList<>();

            for(StationeryCatalogue scdummy :alscc)
            {
                if(sc.get("Description").contains(scdummy.get("Description"))==true) {
                    alsccfake.add(scdummy);
                }
            }

            return alsccfake;
        }*/

        public static StationeryCatalogue searchCatalogueById(String itemNo)
        {
            //searchJSONFromUrl url will settle
            ArrayList<StationeryCatalogue> alscc = getCatalogueByCategory(itemNo,"", "", ""); //dummy values

            if (alscc.size()==0)
            {
                return null;
            }

            return alscc.get(0);
        }
    }
