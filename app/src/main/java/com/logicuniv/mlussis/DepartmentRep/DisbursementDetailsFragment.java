package com.logicuniv.mlussis.DepartmentRep;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.CollectionPointController;
import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.DisbursementController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Model.Disbursement;
import com.logicuniv.mlussis.R;


public class DisbursementDetailsFragment extends Fragment {

    String deptName;
    String empName;
    String colPtName;

    TextView tv_Dept;
    TextView tv_Emp;
    TextView tv_colPt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_disbursement_details, container, false);

        tv_Dept = v.findViewById(R.id.textView_Dept);
        tv_Emp = v.findViewById(R.id.textView_Employee);
        tv_colPt = v.findViewById(R.id.textView_ColPt);

        printDisbDetails();

//        new AsyncTask<String, Void, Void>() {
//
//            @Override
//            protected Void doInBackground(String...params) {
//                String empNoLoggedIn = LoginController.GetLoggedInEmployeeNumber(getContext());
//
//                String deptCode = EmployeeController.getEmployeeById(empNoLoggedIn).get("DeptCode").toString();
//
//                d = DisbursementController.getCurrentDisbursementForDepartment(deptCode);     //get parameter from login details when it is set up
//
//
//                if (d != null) {
//
//                    deptName = DepartmentController.getDepartmentName(d.get("DeptCode"));     //to initialise this method later
//
//
//                    empName = EmployeeController.getEmployeeName(d.get("RepEmpNo"));
//
//
//                    colPtName = new CollectionPointController().getCollectionPointDetails(d.get("CollectionPointNo"));
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void result)
//            {
//                if(deptName!=null && empName!=null && colPtName!=null) {
//                    tv_Dept.setText(deptName);
//                    tv_Emp.setText(empName);
//                    tv_colPt.setText("Collection Point: " + colPtName);
//                }
//                else
//                {
//                    tv_Dept.setText(null);
//                    tv_Emp.setText(null);
//                    tv_colPt.setText(null);
//                }
//            }
//
//        }.execute();

        return v;



    }

    public void printDisbDetails()
    {
        new AsyncTask<String, Void, Disbursement>() {

            @Override
            protected Disbursement doInBackground(String...params) {
                String empNoLoggedIn = LoginController.GetLoggedInEmployeeNumber(getContext());

                String deptCode = EmployeeController.getEmployeeById(empNoLoggedIn).get("DeptCode").toString();

                Disbursement d = DisbursementController.getCurrentDisbursementForDepartment(deptCode);     //get parameter from login details when it is set up


                if (d != null) {

                    deptName = DepartmentController.getDepartmentName(d.get("DeptCode"));     //to initialise this method later


                    empName = EmployeeController.getEmployeeName(d.get("RepEmpNo"));


                    colPtName = new CollectionPointController().getCollectionPointDetails(d.get("CollectionPointNo"));
                }

                return d;
            }

            @Override
            protected void onPostExecute(Disbursement result)
            {
                if(result!=null) {
                    tv_Dept.setText(deptName);
                    tv_Emp.setText(empName);
                    tv_colPt.setText("Collection Point: " + colPtName);
                }
                else
                {
                    tv_Dept.setText(null);
                    tv_Emp.setText(null);
                    tv_colPt.setText(null);
                }
            }

        }.execute();
    }

}
