package com.logicuniv.mlussis.Model;

import java.util.HashMap;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class Department extends HashMap<String,String> {

    public Department(String deptCode, String deptName,String contactName, String phoneNo, String faxNo, String headEmpNo,String collectionPointNo, String repEmpNo, String deputyEmpNo) {
        put("DeptCode", deptCode);
        put("DeptName", deptName);
        put("ContactName", contactName);
        put("PhoneNo", phoneNo);
        put("FaxNo", faxNo);
        put("HeadEmpNo", headEmpNo);
        put("CollectionPointNo", collectionPointNo);
        put("RepEmpNo", repEmpNo);
        put("DeputyEmpNo", deputyEmpNo);
    }
}
