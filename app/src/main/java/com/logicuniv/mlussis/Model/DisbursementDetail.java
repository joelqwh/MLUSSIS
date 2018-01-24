package com.logicuniv.mlussis.Model;

/**
 * Created by e0231991 on 18/1/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;


public class DisbursementDetail extends HashMap<String, String> {

    //final static String baseURL = "http://172.17.252.67/Bookshop13/Service.svc/"; //to change if not will fail

    public DisbursementDetail(String disbNo, String itemNo , String need, String promise, String received) {
        put("DisbursementNo", disbNo);
        put("ItemNo", itemNo);             //getItemName(itemNo)
        put("Needed", need);
        put("Promised", promise);
        put("Received", received);
    }

}


