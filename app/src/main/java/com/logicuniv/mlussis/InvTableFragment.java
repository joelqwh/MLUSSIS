package com.logicuniv.mlussis;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.logicuniv.mlussis.StoreClerk.StoreClerk_StockCard_Activity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvTableFragment extends ListFragment {

    public InvTableFragment() {
        // Required empty public constructor
    }

    View header;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        header = inflater.inflate(R.layout.fragment_inv_row_header,null);
        View v = inflater.inflate(R.layout.inv_list, container, false);

        Bundle args = getArguments();
        ArrayList<StationeryCatalogue> alscc = (ArrayList<StationeryCatalogue>)args.getSerializable("stationerycatalogue");

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),alscc,
                R.layout.fragment_inv_row,
                new String[] {"Description", "Category", "ItemNo"},
                new int[]{R.id.itemDesc, R.id.itemBin, R.id.itemQty});

        setListAdapter(adapter);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getListView().addHeaderView(header);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        StationeryCatalogue sc = (StationeryCatalogue) getListAdapter().getItem(position-1);
        Intent intent = new Intent(getActivity(), StoreClerk_StockCard_Activity.class);
        intent.putExtra("invdetails", sc);
        startActivity(intent);
    }
}
