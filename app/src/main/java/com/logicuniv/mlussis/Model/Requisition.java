package com.logicuniv.mlussis.Model;

import android.util.Log;

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



    public Requisition(String reqNo, String issuedBy, String dateIssued, String approvedBy, String dateReviewed, String status, String remarks) {
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


}
