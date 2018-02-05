package com.logicuniv.mlussis.Model;

/**
 * Created by e0231991 on 18/1/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;


public class DisbursementDetail extends HashMap<String, String> {

    public DisbursementDetail(String disbNo, String itemNo , String description, String need, String promise, String received) {
        put("DisbursementNo", disbNo);
        put("ItemNo", itemNo);             //getItemName(itemNo)
        put("Description", description);
        put("Needed", need);
        put("Promised", promise);
        put("Received", received);
    }

}


