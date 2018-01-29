package com.logicuniv.mlussis.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by e0231991 on 19/1/2018.
 */

public class RequisitionDetail extends HashMap {

    static ArrayList<ArrayList<RequisitionDetail>> alalrd = new ArrayList<>();


    public RequisitionDetail(String reqNo, String itemNo, String description, String qty) {
        put("ReqNo", reqNo);
        put("ItemNo", itemNo);
        put("Description", description);
        put("Qty", qty);
    }

}
