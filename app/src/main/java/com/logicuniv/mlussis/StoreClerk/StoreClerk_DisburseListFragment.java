package com.logicuniv.mlussis.StoreClerk;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreClerk_DisburseListFragment extends ListFragment {


    public StoreClerk_DisburseListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.inv_list,container,false);

        Bundle args = getArguments();
        ArrayList<DisbursementDetail> scdd = (ArrayList<DisbursementDetail>)args.getSerializable("disbursementdetails");

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), scdd,
                R.layout.fragment_store_clerk_disburse_row,
                new String[] {"ItemNo", "Received"},
                new int[]{R.id.scDisburseItemDesc, R.id.scDisburseItemQty});

        setListAdapter(adapter);
        return v;
    }

}
