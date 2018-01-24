package com.logicuniv.mlussis.Model;

import java.util.HashMap;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class CollectionPoint extends HashMap<String,String> {

    public CollectionPoint(String collectionPointNo, String collectionPointDetails) {
        put("CollectionPointNo", collectionPointNo);
        put("CollectionPointDetails", collectionPointDetails);
    }
}
