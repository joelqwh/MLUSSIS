package com.logicuniv.mlussis.StoreClerk;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.Model.Department;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreClerk_DisburseSpinFragment extends Fragment {


    public StoreClerk_DisburseSpinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_store_clerk_disburse_spin, container, false);
        final Spinner deptSpinner = (Spinner) v.findViewById(R.id.scDisburseDeptspinner);

        new AsyncTask<Void, Void, Void>(){
            ArrayList<Department> alDept;

            @Override
            protected Void doInBackground(Void...params){
                alDept = DepartmentController.getAllDepartments();
                return null;
            }

            @Override
            protected void onPostExecute(Void v)
            {
                Department[] alldeptarray = alDept.toArray(new Department[alDept.size()]);
                String[] deptName = new String[alldeptarray[0].size()];
                for (int i=1; i<deptName.length; i++){
                    deptName[i] = alDept.get(i).get("DeptName");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.fragment_store_clerk_disburse_spin,deptName);
                adapter.setDropDownViewResource(R.layout.fragment_store_clerk_disburse_spin);
                deptSpinner.setAdapter(adapter);
            }}.execute();

        /*new AsyncTask<Void, Void, Void>>(){
            @Override
            protected Void doInBackground(Void...params){
                ArrayList<Department> alDept = DepartmentController.getAllDepartments();
                return null;
            }

            @Override
            protected void OnPostExecute(Void v){
                Department[] alldeptarray = alDept.toArray(new Department[alDept.size()]);
                String[] column = new String[alldeptarray[0].size()];
                for (int i=1; i<column.length; i++){
                    column[i] = result.get(i).get("DeptName");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.fragment_store_clerk_disburse_spin,column);
                adapter.setDropDownViewResource(R.layout.fragment_store_clerk_disburse_spin);
                deptSpinner.setAdapter(adapter);
            }
        };*/

        return v;
    }

    void display(ArrayList<DisbursementDetail> disbursementdetails) {
        final String TAG = "DETAILS_FRAG";
        FragmentManager fm = getFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        Fragment frag = new StoreClerk_DisburseListFragment();
        Bundle args = new Bundle();
        args.putSerializable("disbursementdetails",disbursementdetails);
        frag.setArguments(args);
        if(fm.findFragmentByTag(TAG)==null)
            trans.add(R.id.scDisburseListFragment, frag, TAG);
        else
            trans.replace(R.id.scDisburseListFragment,frag,TAG);
        trans.commit();
    }
}
