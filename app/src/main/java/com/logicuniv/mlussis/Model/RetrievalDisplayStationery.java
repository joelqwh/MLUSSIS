package com.logicuniv.mlussis.Model;

import java.util.HashMap;

/**
 * Created by ET on 28/1/2018.
 */

public class RetrievalDisplayStationery extends HashMap<String, String> {

    public RetrievalDisplayStationery(String binNo, String statDesc, String needed, String backlogQty, String retrieved){
        put("BinNo", binNo);
        put("StatDesc", statDesc);
        put("Needed", needed);
        put("BacklogQty", backlogQty);
        put("Retrieved", retrieved);
    }
}
