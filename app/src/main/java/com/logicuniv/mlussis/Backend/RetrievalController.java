package com.logicuniv.mlussis.Backend;

import com.logicuniv.mlussis.Model.Retrieval;
import com.logicuniv.mlussis.Model.RetrievalDetails;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ET on 26/1/2018.
 */

public class RetrievalController {
    public static ArrayList<Retrieval> getRetrieval() {
        //getCatalogue() JSON Parser get as function
        ArrayList<Retrieval> ret = new ArrayList<>();
        Retrieval rtl = new Retrieval("1", "2017-01-12");
        Retrieval rt2 = new Retrieval("2", "2017-01-19");

        return ret;
    }

    public static ArrayList<String> getRetrievalDate(){
        ArrayList<Retrieval> x = getRetrieval();
        ArrayList<String> datelist = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            String date = x.get(i).get("Date");
            datelist.add(date);
        }

        return  datelist;
    }

}
