package com.logicuniv.mlussis.Backend;

import com.logicuniv.mlussis.Model.RetrievalDetails;
import com.logicuniv.mlussis.Model.RetrievalDisplayStationery;

import java.util.ArrayList;

/**
 * Created by ET on 28/1/2018.
 */

public class RetrievalDisplayStationeryController {
    public static ArrayList<RetrievalDisplayStationery> getRetrievalDisplayStationery() {
        //Wrong method
        ArrayList<RetrievalDisplayStationery> retds = new ArrayList<>();
        RetrievalDisplayStationery rtds1 = new RetrievalDisplayStationery("A1", "Clips Double 1", "40", "0", "40");
        RetrievalDisplayStationery rtds2 = new RetrievalDisplayStationery("A2", "Clips Double 2", "30", "0", "30");
        RetrievalDisplayStationery rtds3 = new RetrievalDisplayStationery("A3", "Clips Double 3/4", "20", "0", "20");
        RetrievalDisplayStationery rtds4 = new RetrievalDisplayStationery("A4", "Clips Paper Large", "90", "0", "90");
        RetrievalDisplayStationery rtds5 = new RetrievalDisplayStationery("A5", "Clips Paper Medium", "35", "0", "35");
        retds.add(rtds1);
        retds.add(rtds2);
        retds.add(rtds3);
        retds.add(rtds4);
        retds.add(rtds5);
        return retds;

    }
}
