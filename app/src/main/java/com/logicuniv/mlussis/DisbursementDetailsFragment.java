package com.logicuniv.mlussis;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.CollectionPointController;
import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.DisbursementController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Model.Disbursement;
import com.logicuniv.mlussis.Model.Employee;


public class DisbursementDetailsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_disbursement_details, container, false);
        Disbursement d = DisbursementController.getCurrentDisbursementForDepartment("ENGL");     //get parameter from login details when it is set up
        if (d != null) {
            TextView tv_Dept = v.findViewById(R.id.textView_Dept);
            //String deptName = DepartmentController.getDepartmentName(d.get("DeptCode"));     //to initialise this method later
            tv_Dept.setText(d.get("DeptCode"));
            TextView tv_Emp = v.findViewById(R.id.textView_Employee);
            //String empName = EmployeeController.getEmployeeName(d.get("RepEmpNo"));
            tv_Emp.setText(d.get("RepEmpNo"));
            TextView tv_colPt = v.findViewById(R.id.textView_ColPt);
            String colPtName = new CollectionPointController().getCollectionPointDetails(d.get("CollectionPoint"));
            tv_colPt.setText(colPtName);
        }

//        TextView tv_colPt = v.findViewById(R.id.textView_ColPt);
//        String colPtName = new CollectionPointController().getCollectionPointDetails("1"); //d.get("CollectionPoint"
//        tv_colPt.setText(colPtName);
        return v;

    }

}
