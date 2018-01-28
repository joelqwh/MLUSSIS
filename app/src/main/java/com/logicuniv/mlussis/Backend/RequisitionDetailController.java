package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.Model.RequisitionDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class RequisitionDetailController {

    public static ArrayList<RequisitionDetail> getRequisitionDetail(String rnum)
    {
        ArrayList<RequisitionDetail> result = new ArrayList<>();

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResult;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("ReqNo", rnum);

            JSONArray jsonArray = new JSONArray (JSONParser.postStream(App.WCFServer + "RequisitionDetails", jsonObject.toString()));

            for (int i=0; i<jsonArray.length(); i++){
                JSONObject requisitionDetail = jsonArray.getJSONObject(i);
                result.add(new RequisitionDetail(
                        requisitionDetail.getString("ReqNo"),
                        requisitionDetail.getString("ItemNo"),
                        requisitionDetail.getString("Qty")
                ));
            }
        }
        catch (Exception e)
        {
            Log.e("RequisitionDetail", e.getMessage());
        }

        return result;
    }

    public static boolean addRequisitionDetails (RequisitionDetail[] rdItems)
    {
        boolean result = false;

        for(int i = 0; i < rdItems.length; i++)
        {
            if(!addRequisitionDetail(rdItems[i]))
            {
                result = false;
                break;
            }
            else
            {
                result = true;
            }
        }
        return result;
    }

    private static boolean addRequisitionDetail (RequisitionDetail rdItem)
    {
        Boolean result = false;

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonRequisitionDetail = new JSONObject();
        String response;

        try {
            //put requisition details into a JSONObject "jsonRequisitionDetail"
            jsonRequisitionDetail.put("ReqNo", rdItem.get("ReqNo"));
            jsonRequisitionDetail.put("ItemNo", rdItem.get("ItemNo"));
            jsonRequisitionDetail.put("Qty", rdItem.get("Qty"));

            //put JSONObject "jsonRequisitionDetail" and sessionID into a JSONObject "jsonObject"
            jsonObject.put("sessionID", LoginController.getSessionID((App.getAppContext())));
            jsonObject.put("addRequisitionDetail", jsonRequisitionDetail.toString());

            //result of passing the "jsonObject".toString() into the WCF Server
            response = JSONParser.postStream(App.WCFServer + "AddRequisitionDetail", jsonObject.toString());

            result = response.trim().equals("true");
        }
        catch (Exception e)
        {
            Log.e("AddRequisitionDetail", e.getMessage());
            result = false;
        }

        return result;
    }

    public static boolean removeRequisitionDetail(RequisitionDetail rdItem){

        Boolean result = false;

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonRequisitionDetail = new JSONObject();
        String response;

        try {
            //put requisition details into a JSONObject "jsonRequisitionDetail"
            jsonRequisitionDetail.put("ReqNo", rdItem.get("ReqNo"));
            jsonRequisitionDetail.put("ItemNo", rdItem.get("ItemNo"));
            jsonRequisitionDetail.put("Qty", rdItem.get("Qty"));

            //put JSONObject "jsonRequisitionDetail" and sessionID into a JSONObject "jsonObject"
            jsonObject.put("sessionID", LoginController.getSessionID((App.getAppContext())));
            jsonObject.put("removeRequisitionDetail", jsonRequisitionDetail.toString());

            //result of passing the "jsonObject".toString() into the WCF Server
            response = JSONParser.postStream(App.WCFServer + "RemoveRequisitionDetail", jsonObject.toString());

            result = response.trim().equals("true");
        }
        catch (Exception e)
        {
            Log.e("RemoveRequisitionDetail", e.getMessage());
            result = false;
        }

        return result;

    }

    public boolean updateRequisitionDetail(RequisitionDetail rdItem)
    {
        Boolean result = false;

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonRequisitionDetail = new JSONObject();
        String response;

        try {
            //put requisition details into a JSONObject "jsonRequisitionDetail"
            jsonRequisitionDetail.put("ReqNo", rdItem.get("ReqNo"));
            jsonRequisitionDetail.put("ItemNo", rdItem.get("ItemNo"));
            jsonRequisitionDetail.put("Qty", rdItem.get("Qty"));

            //put JSONObject "jsonRequisitionDetail" and sessionID into a JSONObject "jsonObject"
            jsonObject.put("sessionID", LoginController.getSessionID((App.getAppContext())));
            jsonObject.put("updateRequisitionDetail", jsonRequisitionDetail.toString());

            //result of passing the "jsonObject".toString() into the WCF Server
            response = JSONParser.postStream(App.WCFServer + "UpdateRequisitionDetail", jsonObject.toString());

            result = response.trim().equals("true");
        }
        catch (Exception e)
        {
            Log.e("UpdateRequisitionDetail", e.getMessage());
            result = false;
        }

        return result;
    }

}


//        RequisitionDetail reqA = new RequisitionDetail("R1","I001","2");
//        RequisitionDetail reqB = new RequisitionDetail("R1","I002","3");
//        ArrayList<RequisitionDetail> alrrrrd = new ArrayList<>();
//        alrrrrd.add(reqA);
//        alrrrrd.add(reqB);
//        addRequisitionDetail(alrrrrd);      //just for dummy purposes