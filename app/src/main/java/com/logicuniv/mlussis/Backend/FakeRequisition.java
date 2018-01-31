package com.logicuniv.mlussis.Backend;

import com.logicuniv.mlussis.Model.Requisition;
import com.logicuniv.mlussis.Model.RequisitionDetail;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by xavie on 30-01-2018.
 */

public class FakeRequisition {
    public static ArrayList<RequisitionDetail> details = new ArrayList<>();
    public static Requisition requisition = new Requisition();

    public static void startNewRequisition(String empWhoIssued)
    {
        details.clear();
        requisition = new Requisition(empWhoIssued, "");
    }

    public static void submitNewRequisition()
    {
        String reqNo = RequisitionController.CreateNewRequisition();
        for (RequisitionDetail detail: details) {
            detail.put("ReqNo",reqNo);
            RequisitionDetailController.addRequisitionDetail(detail);
        }
    }
}
