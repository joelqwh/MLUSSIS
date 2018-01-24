package com.logicuniv.mlussis.Model;

import java.util.HashMap;

/**
 * Created by e0231991 on 17/1/2018.
 */

public class Disbursement extends HashMap<String, String> {

    //final static String baseURL = "http://172.17.252.67/Bookshop13/Service.svc/"; //to change if not will fail

    public Disbursement(String disbNo, String deptCode,String disbDate, String empNo, String colPtNo, String pin,String status) {
        put("DisbursementNo", disbNo);
        put("DeptCode", deptCode);             //getDeptName()
        put("DisbursementDate", disbDate);
        put("RepEmpNo", empNo);             //getEmployee(empNo)
        put("CollectionPointNo", colPtNo);  //getCollectionPoint(colPtNo)
        put("Pin", pin);
        put("Status", status);
    }

    public Disbursement() {


    }

    }
