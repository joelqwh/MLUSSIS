package com.logicuniv.mlussis.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by ET on 26/1/2018.
 */

public class RetrievalDetails extends HashMap<String,String> implements Serializable{

    public RetrievalDetails(String retrievalNo, String deptCode, String itemNo, String needed, String backlogQty, String actual) {
        put("RetrievalNo", retrievalNo);
        put("DeptCode", deptCode);
        put("ItemNo", itemNo);
        put("Needed", needed);
        put("BackLogQty", backlogQty);
        put("Actual", actual);
    }

}
