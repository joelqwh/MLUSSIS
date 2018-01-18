package com.logicuniv.mlussis;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by e0231991 on 18/1/2018.
 */

public class StationeryCatalogue extends HashMap<String, String> {

    public StationeryCatalogue(String itemNo, String description, String cat, String uom) {
        put("ItemNo", itemNo);
        put("Description", description);
        put("Category", cat);
        put("Uom", uom);
    }

    public StationeryCatalogue(String itemNo, String description, String cat) {     //search function
        put("ItemNo", itemNo);
        put("Description", description);
        put("Category", cat);
    }

    public static ArrayList<StationeryCatalogue> getCatalogue()
    {
        //getCatalogue() JSON Parser get as function
        ArrayList<StationeryCatalogue> alsc = new ArrayList<>();
        StationeryCatalogue scl1 = new StationeryCatalogue("I001","Clips 1", "Clip", "Dozen");
        StationeryCatalogue scl2 = new StationeryCatalogue("I002","Pilot", "Pen", "Each");
        alsc.add(scl1);
        alsc.add(scl2);
        return alsc;
    }

    public static ArrayList<StationeryCatalogue> searchCatalogue(StationeryCatalogue sc)
    {
        //searchJSONFromUrl url will settle
        ArrayList<StationeryCatalogue> alscc = getCatalogue(); //dummy values
        ArrayList<StationeryCatalogue> alsccfake = new ArrayList<>();

        for(StationeryCatalogue scdummy :alscc)
        {
            if(sc.get("Description")==scdummy.get("Description")) {
                alsccfake.add(scdummy);
            }
        }

        return alsccfake;
    }





}
