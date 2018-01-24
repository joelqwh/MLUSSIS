package com.logicuniv.mlussis.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by e0231991 on 18/1/2018.
 */

public class StationeryCatalogue extends HashMap<String, String> implements Serializable{

    public StationeryCatalogue(String itemNo, String description, String cat, String uom) {
        put("ItemNo", itemNo);
        put("Description", description);
        put("Category", cat);
        put("Uom", uom);
    }

    public StationeryCatalogue(String itemNo, String description, String cat, String uom, String binNo, String sup1, String sup2, String sup3) {
        put("ItemNo", itemNo);
        put("Description", description);
        put("Category", cat);
        put("Uom", uom);
        put("BinNo", binNo);
        put("Supplier1", sup1);
        put("Supplier2", sup2);
        put("Supplier3", sup3);
    }

    public StationeryCatalogue(String itemNo, String description, String cat) {     //search function
        put("ItemNo", itemNo);
        put("Description", description);
        put("Category", cat);
    }

    public StationeryCatalogue(HashMap<String, Object> bundle) {     //search function
    }

}