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

    public static ArrayList<String> getCategoryList() {
        ArrayList<String> listSC = new ArrayList<String>();
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            JSONArray sc = new JSONArray (JSONParser.postStream(App.WCFServer+"GetCatalogueCatList",jsonObject.toString()));
            Log.d("getCatalogueCat", App.WCFServer+"CatalogueCatList");
            Log.d("getCatalogueCat", jsonObject.toString());

            for (int i=0; i<sc.length();i++){
                String item = sc.getString(i);
                listSC.add(item);
            }
        }
        catch (Exception e) {
            Log.e("StatCat.getCategory()","JSONArray error");
        }
        return listSC;
    }

    public static ArrayList<String> getBinList() {
        ArrayList<String> listSC = new ArrayList<String>();
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            JSONArray sc = new JSONArray (JSONParser.postStream(App.WCFServer+"GetCatalogueBinList",jsonObject.toString()));
            Log.d("getCatalogueBin", App.WCFServer+"CatalogueBinList");
            Log.d("getCatalogueBin", jsonObject.toString());

            for (int i=0; i<sc.length();i++){
                String item = sc.getString(i);
                listSC.add(item);
            }
        }
        catch (Exception e) {
            Log.e("StatCat.getBin()","JSONArray error");
        }
        return listSC;
    }

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
