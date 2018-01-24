package com.logicuniv.mlussis;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static java.text.DateFormat.getDateInstance;

/**
 * Created by e0231991 on 19/1/2018.
 */

public class Requisition extends HashMap<String,Object>{

    static ArrayList<Requisition> alr = new ArrayList<Requisition>();



    public Requisition(String reqNo, String issuedBy, Date dateIssued, String approvedBy, Date dateReviewed, String status, String remarks) {
        put("ReqNo", reqNo);
        put("IssuedBy", issuedBy);
        put("DateIssued", dateIssued);
        put("ApprovedBy", approvedBy);
        put("DateReviewed", dateReviewed);
        put("Status", status);
        put("Remarks", remarks);
    }

    public Requisition(String reqNo, String issuedBy, Date dateIssued) {
        put("ReqNo", reqNo);
        put("IssuedBy", issuedBy);
        put("DateIssued", dateIssued);
    }

    public Requisition() {
    }

    public Requisition(HashMap map) {
        super(map);
    }

    public static void addRequisition(Requisition r)
    {
        alr.add(r);
        Log.e("joel",alr.toString());;
    }

    public static ArrayList<Requisition> getPendingRequisitions()
    {
        Requisition r = new Requisition("R1"," E001",Calendar.getInstance().getTime(),null,null,"Pending",null);
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




}
