package com.logicuniv.mlussis.StoreClerk;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.logicuniv.mlussis.Backend.DisbursementDetailController;
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

    ArrayList<DisbursementDetail> disbursementdetails = DisbursementDetailController.getCurrentDisbursementDetailsForDepartment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_store_clerk_disburse_spin, container, false);
        display(disbursementdetails);
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
