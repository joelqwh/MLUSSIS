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
        String disbNo = DisbursementController.getCurrentDisbursementForDepartment(deptCode).get("DisbursementNo");
        pendingDisDets = DisbursementDetailController.getCurrentDisbursementDetailsOf(disbNo);
        return pendingDisDets;
    }

    public static void updatePendingDisDetsforDepartment() {
        //pass adjusted values into real DisbursementDetails database
        //DisbursementDetailController.UpdateDisbursementDetail()

    }

    public static boolean confirmPendingDisDetsforDepartment(String PinNo){
        //compare Pin and change status to Collected

        return false;
    }
}
