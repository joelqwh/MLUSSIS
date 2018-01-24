package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.Requisition;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class RequisitionController {

    static ArrayList<Requisition> alr = new ArrayList<Requisition>();

    public static void addRequisition(Requisition r)
    {
        alr.add(r);
        Log.e("joel",alr.toString());;
    }

    public static ArrayList<Requisition> getPendingRequisitions()
    {
        Requisition r = new Requisition("R1"," E001", Calendar.getInstance().getTime(),null,null,"Pending",null);
        alr.add(r);     //dummy for getAllReqs

        ArrayList<Requisition> alr_i = new ArrayList<Requisition>();

        for(Requisition req :alr)
        {
            if (req.get("Status").equals("Pending")||req.get("Status").equals(null))
            {
                alr_i.add(r);
            }
        }

        return alr_i;
    }

    public static Requisition getRequisitionById(String reqNo)
    {
        Requisition r = new Requisition("R1"," E001",new Date(),null,null,"Pending",null);
        alr.add(r);     //dummy for getAllReqs
        ArrayList<Requisition> alr_i = new ArrayList<Requisition>();

        for(Requisition req :alr)
        {
            if (req.get("ReqNo").equals(reqNo))
            {
                return req;
            }
        }

        return null;
    }

    public void updateRequisition(Requisition r){

    }

    public void removeRequisition(){

    }


}
