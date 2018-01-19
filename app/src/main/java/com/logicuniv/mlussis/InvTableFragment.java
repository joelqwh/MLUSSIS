package com.logicuniv.mlussis;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvTableFragment extends ListFragment {

    public InvTableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.inv_list, container, false);
        ListView lv1 = (ListView) v.findViewById(android.R.id.list);
        Bundle args = getArguments();
        ArrayList<StationeryCatalogue> alscc = (ArrayList<StationeryCatalogue>)args.getSerializable("stationerycatalogue");
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),alscc, R.layout.fragment_inv_row,
                new String[] {"Description", "Category", "ItemNo"},
                new int[]{R.id.itemDesc, R.id.itemBin, R.id.itemQty});

        View header = (View)inflater.inflate(R.layout.fragment_inv_row_header,null);
        lv1.addHeaderView(header);
        header.setEnabled(false);

        lv1.setAdapter(adapter);
        return (v);
    }
}
