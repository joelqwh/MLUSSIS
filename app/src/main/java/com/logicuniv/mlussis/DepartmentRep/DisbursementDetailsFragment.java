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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_disbursement_details, container, false);

        final TextView tv_Dept = v.findViewById(R.id.textView_Dept);
        final TextView tv_Emp = v.findViewById(R.id.textView_Employee);
        final TextView tv_colPt = v.findViewById(R.id.textView_ColPt);

        new AsyncTask<String, Void, Void>() {
            boolean t = false;
            String deptName;
            String empName;
            String colPtName;

            @Override
            protected Void doInBackground(String...params) {
                String empNoLoggedIn = LoginController.GetLoggedInEmployeeNumber(getContext());

                String deptCode = EmployeeController.getEmployeeById(empNoLoggedIn).get("DeptCode").toString();

                Disbursement d = DisbursementController.getCurrentDisbursementForDepartment(deptCode);     //get parameter from login details when it is set up


                if (d != null) {

                    deptName = DepartmentController.getDepartmentName(d.get("DeptCode"));     //to initialise this method later


                    empName = EmployeeController.getEmployeeName(d.get("RepEmpNo"));


                    colPtName = new CollectionPointController().getCollectionPointDetails(d.get("CollectionPointNo"));
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result)
            {
                tv_Dept.setText(deptName);
                tv_Emp.setText(empName);
                tv_colPt.setText(colPtName);
            }

        }.execute();

        return v;



    }

}
