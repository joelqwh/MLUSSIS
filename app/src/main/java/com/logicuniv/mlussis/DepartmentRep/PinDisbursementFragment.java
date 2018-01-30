package com.logicuniv.mlussis.DepartmentRep;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.DisbursementController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Model.Disbursement;
import com.logicuniv.mlussis.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PinDisbursementFragment extends Fragment {

    Disbursement d = null;

    public PinDisbursementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pin_disbursement, container, false);
        final TextView tv_Dept = v.findViewById(R.id.textView_deptRep_PinNumber);


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                String empNoLoggedIn = LoginController.GetLoggedInEmployeeNumber(getContext());
                String deptCode = EmployeeController.getEmployeeById(empNoLoggedIn).get("DeptCode").toString();
                d = DisbursementController.getCurrentDisbursementForDepartment(deptCode);     //get parameter from login details when it is set up

                return null;


                }
            @Override
            protected void onPostExecute(Void result)
            {
                    if (d != null) {

                tv_Dept.setText(d.get("Pin"));
                }

            }
        }.execute();

        return v;
    }

}
