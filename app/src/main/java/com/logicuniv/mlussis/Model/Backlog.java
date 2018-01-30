package com.logicuniv.mlussis.Model;

import java.util.HashMap;

/**
 * Created by Ying Hui on 30-Jan-18.
 */

public class Backlog extends HashMap<String, String> {

    public Backlog(String deptCode, String itemNo, String backlogQty){
        put("DeptCode", deptCode);
        put("ItemNo", itemNo);
        put("BackLogQty", backlogQty);
    }

}
