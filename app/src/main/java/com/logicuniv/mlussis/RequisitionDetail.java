package com.logicuniv.mlussis;

import java.util.HashMap;

/**
 * Created by e0231991 on 19/1/2018.
 */

public class RequisitionDetail extends HashMap {

    public RequisitionDetail(String reqNo, String itemNo, String qty) {
        put("ReqNo", reqNo);
        put("ItemNo", itemNo);
        put("Qty", qty);
    }
}
