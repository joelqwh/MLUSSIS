package com.logicuniv.mlussis.Model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by ET on 26/1/2018.
 */

public class RetrievalDetails extends HashMap<String,String> implements Serializable{

    public RetrievalDetails(String retrievalNo, String itemNo, String description, String bin, String needed, String backlogQty, String actual) {
        put("RetrievalNo", retrievalNo);
        put("ItemNo", itemNo);
        put("Description", description);
        put("Bin", bin);
        put("Needed", needed);
        put("BackLogQty", backlogQty);
        put("Actual", actual);
    }

}
