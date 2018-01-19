package com.logicuniv.mlussis;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvMenuFragment extends Fragment {

    ArrayList<StationeryCatalogue> arraystationery = StationeryCatalogue.getCatalogue();

    public InvMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inventory_skeleton,container,false);
        display (arraystationery);

        return v;
    }

    void display(ArrayList<StationeryCatalogue> details) {
        final String TAG = "INVTABLE_FRAG";
        FragmentManager fm = getFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        Fragment frag = new InvTableFragment();
        Bundle args = new Bundle();
        args.putSerializable("stationerycatalogue", details);
        frag.setArguments(args);
        if (fm.findFragmentByTag(TAG)==null)
            trans.add(R.id.invDetailsFragment, frag, TAG);
        else
            trans.replace(R.id.invDetailsFragment, frag, TAG);
        trans.commit();
    }

}
