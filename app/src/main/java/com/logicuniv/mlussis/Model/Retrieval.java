package com.logicuniv.mlussis.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ET on 25/1/2018.
 */

public class Retrieval extends HashMap<String,String> implements Serializable {



    public Retrieval(String retrievalNo, String date) {
        put("RetrievalNo", retrievalNo);
        put("Date", date);
    }


    public Retrieval(HashMap<String, Object> bundle) {     //search function
    }



}
