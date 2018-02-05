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

    TextView tv_Dept;
    TextView tv_pinText;

    public PinDisbursementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pin_disbursement, container, false);
        tv_Dept = v.findViewById(R.id.textView_deptRep_PinNumber);
        tv_pinText = v.findViewById(R.id.textView_deptRep_PinText);

        printPinDisbDetails();

        return v;
    }

    public void printPinDisbDetails()
    {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                String empNoLoggedIn = LoginController.GetLoggedInEmployeeNumber(getContext());
                String deptCode = EmployeeController.getEmployeeById(empNoLoggedIn).get("DeptCode").toString();
                d = DisbursementController.getCurrentDisbursementForDepartment(deptCode);
                return null;


            }
            @Override
            protected void onPostExecute(Void result)
            {
                if (d != null) {
                    tv_pinText.setText("Pin: ");    //set the pin
                    tv_Dept.setText(d.get("Pin"));
                }
                else
                {
                    tv_pinText.setText("No Current Disbursement");      //textviews cant be seen if there is no disbursements
                    tv_Dept.setText(null);
                }

            }
        }.execute();

    }

}
