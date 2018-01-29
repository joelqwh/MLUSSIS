package com.logicuniv.mlussis.StoreClerk;

/**
 * Created by ET on 29/1/2018.
 */

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.logicuniv.mlussis.Model.RetrievalDisplayStationery;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;
import com.logicuniv.mlussis.StoreClerk.StoreClerk_EditStockQtyActivity;
import com.logicuniv.mlussis.StoreClerk.StoreClerk_StockCardActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatRetTableFragment extends ListFragment {

    public StatRetTableFragment() {
        // Required empty public constructor
    }

    View header;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        header = inflater.inflate(R.layout.fragment_store_clerk_ret_header,null);
/*
        View v = inflater.inflate(R.layout.ret_list, container, false);
*/
        View contactLayout = inflater.inflate(R.layout.ret_list,
                container, false);
/*
        ListView lv = (ListView)contactLayout.findViewById(android.R.id.list);
*/

        Bundle args = getArguments();
        final ArrayList<RetrievalDisplayStationery> rds = (ArrayList<RetrievalDisplayStationery>)args.getSerializable("displaystatret");

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),rds,
                R.layout.fragment_store_clerk_ret_row,
                new String[] {"BinNo", "StatDesc", "Needed","BacklogQty","Retrieved"},
                new int[]{R.id.itemRetBin, R.id.itemRetDesc, R.id.itemRetNeeded,R.id.itemRetBacklog,R.id.itemRetActual});

        setListAdapter(adapter);
        return contactLayout;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().addHeaderView(header, null, false);
    }
}