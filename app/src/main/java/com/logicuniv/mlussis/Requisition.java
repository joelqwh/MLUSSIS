package com.logicuniv.mlussis;

import java.util.HashMap;

/**
 * Created by e0231991 on 19/1/2018.
 */

public class Requisition extends HashMap{

    public Requisition(String reqNo, String issuedBy, String dateIssued, String approvedBy,String dateReviewed, String status, String remarks) {
        put("ReqNo", reqNo);
        put("IssuedBy", issuedBy);
        put("DateIssued", dateIssued);
        put("ApprovedBy", approvedBy);
        put("DateReviewed", dateReviewed);
        put("Status", status);
        put("Remarks", remarks);
    }

    public Requisition(String reqNo, String issuedBy, String dateIssued) {
        put("ReqNo", reqNo);
        put("IssuedBy", issuedBy);
        put("DateIssued", dateIssued);
    }
}
