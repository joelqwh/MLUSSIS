package com.logicuniv.mlussis.Model;

/**
 * Created by e0231991 on 31/1/2018.
 */

public class EmailTemplate {

    public static String GenerateRequisitionStatusChangedEmailSubject(String requestNumber, String result)
    {
        return "[Requisition] " + requestNumber + " has been " + result;
    }

    public static String GenerateRequisitionStatusChangedEmail(String applicant, String requestNumber, String approver, String result, String remark)
    {
        return
                "Dear " + applicant + "<br/>"
                        + "Your Stationery Request numbered " + requestNumber + " has been " + result + " by " + approver + "." + "<br/>"
                        + ((remark.trim().length() > 0) ? "The following Remark has been left for you: " + remark : "") + "<br/>"
                        + "[This is an automated Message, do no reply]";
    }

    public static String GenerateRequisitionSubmittedEmailToDeputySubject()
    {
        return "A requisition is pending your approval";
    }

    public static String GenerateRequisitionSubmittedEmailToDeputy()
    {
        return "A new requisition has been submitted for your approval. Please log in to your app or your account on LUSSIS to view it. Thank you.";
    }
}
