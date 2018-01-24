package com.logicuniv.mlussis.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class Employee  extends HashMap<String,Object>{

    public Employee(String empNo, String empName, String deptCode, String email, String sessionNo, Date sessionExpiry) {

        put("EmpNo", empNo);
        put("EmpName", empName);
        put("DeptCode", deptCode);
        put("Email", email);
        put("SessionNo", sessionNo);
        put("SessionExpiry", sessionExpiry);

    }
}
