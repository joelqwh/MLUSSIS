package com.logicuniv.mlussis.StoreClerk;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Model.Department;
import com.logicuniv.mlussis.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreClerk_DisburseRepFragment extends Fragment {


    public StoreClerk_DisburseRepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_store_clerk_disburse_rep, container, false);

        /*new AsyncTask<String,Void,Void>(){

            @Override
            protected Void doInBackground(String...params) {
                Spinner scdeptSpin = v.findViewById(R.id.scDisburseDeptspinner);
                String deptName = scdeptSpin.getSelectedItem().toString();
                Department dept = DepartmentController.getDepartmentById(deptName);
            }*/
        return v;
        }


    }
