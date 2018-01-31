package com.logicuniv.mlussis.Backend;

import org.json.JSONObject;

/**
 * Created by xavie on 30-01-2018.
 */

public class AdjustmentVoucherController {
    public static boolean createAdjustmentVoucher(String itemNo, int newQuantity, String reason) {
        boolean result = false;
        String response = "";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("ItemNo", itemNo);
            jsonObject.put("Qty", newQuantity);
            jsonObject.put("Reason", reason);

            response = JSONParser.postStream(App.WCFServer + "CreateAdjustmentVoucher", jsonObject.toString());

            result = response.contains("true");
        } catch (Exception e) {
            result = false;
        }

        return result;
    }
}
