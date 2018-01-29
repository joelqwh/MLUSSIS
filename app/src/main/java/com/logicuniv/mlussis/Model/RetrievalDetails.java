package com.logicuniv.mlussis.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by ET on 26/1/2018.
 */

public class RetrievalDetails extends HashMap<String,String> implements Serializable{

    public RetrievalDetails(String retNo, String deptCode, String itemNo, String needed, String backlogQty, String actual) {
        put("RetNo", retNo);
        put("DeptCode", deptCode);
        put("ItemNo", itemNo);
        put("Needed", needed);
        put("BacklogQty", backlogQty);
        put("Actual", actual);
    }

}
