package com.logicuniv.mlussis.Backend;

import com.logicuniv.mlussis.Model.Disbursement;
import com.logicuniv.mlussis.Model.DisbursementDetail;

import java.util.ArrayList;

/**
 * Created by Ying Hui on 31-Jan-18.
 */

public class PendingDisbursement {
    public static ArrayList<DisbursementDetail> pendingDisDets = new ArrayList<>();
    public static Disbursement pendingDisbursement = null;

    public static ArrayList<DisbursementDetail> getPendingDisDetsforDepartment(String deptCode) {
        pendingDisbursement = DisbursementController.getCurrentDisbursementForDepartment(deptCode);
        pendingDisDets = DisbursementDetailController.getCurrentDisbursementDetailsOf(pendingDisbursement.get("DisbursementNo"));
        return pendingDisDets;
    }

    public static void updatePendingDisDetsforDepartment() {
        //pass adjusted values into real DisbursementDetails database
        for(int i = 0; i < pendingDisDets.size(); i++)
        {
            DisbursementDetailController.UpdateDisbursementDetail(pendingDisDets.get(i));
        }
    }

    public static boolean confirmPendingDisDetsforDepartment(String PinNo){
        //compare Pin and change status to Collected
        boolean result = false;

        if(PinNo.equals(pendingDisbursement.get("Pin").toString()))
        {
            updatePendingDisDetsforDepartment();
            result = DisbursementController.MarkDisbursementAsCollected(pendingDisbursement.get("DisbursementNo"), PinNo);
        }

        return result;
    }
}
