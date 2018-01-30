package com.logicuniv.mlussis.Backend;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by xavie on 30-01-2018.
 */

public class EmailController {
    public static boolean sendEmail(String email, String subject, String message)
    {
        boolean result = false;
        JSONObject jsonObject = new JSONObject();
        String response;

        try {
            jsonObject.put("sessionID", LoginController.getSessionID(App.getAppContext()));
            jsonObject.put("email", email);
            jsonObject.put("subject", subject);
            jsonObject.put("message", message);

            response = JSONParser.postStream(App.WCFServer + "UpdateRequisition", jsonObject.toString());

            result = response.equals("true");
        } catch (Exception e) {
            Log.e("sendEmail", e.getMessage());
            result = false;
        }

        return result;
    }
}
