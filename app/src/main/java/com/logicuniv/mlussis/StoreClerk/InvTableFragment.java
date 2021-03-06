package com.logicuniv.mlussis.StoreClerk;


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

import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;
import com.logicuniv.mlussis.StoreClerk.StoreClerk_EditStockQtyActivity;
import com.logicuniv.mlussis.StoreClerk.StoreClerk_StockCardActivity;

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
        final View v = inflater.inflate(R.layout.inv_list, container, false);

        //get Serializable data from InvMenuFragment
        Bundle args = getArguments();
        ArrayList<StationeryCatalogue> alscc = (ArrayList<StationeryCatalogue>)args.getSerializable("stationerycatalogue");

        //Putting the data into ListView
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),alscc,
                R.layout.fragment_inv_row,
                new String[] {"Description", "Bin", "CurrentQty"},
                new int[]{R.id.itemDesc, R.id.itemBin, R.id.itemQty});

        setListAdapter(adapter);
        return v;
    }

    //Adding the header into ListView
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getListView().addHeaderView(header,null,false);

        //Long click on item will bring user to EditStockQtyActivity to adjust Current Quantity
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> l, View v, int position, long id) {
            StationeryCatalogue sc = (StationeryCatalogue) getListAdapter().getItem(position-1);
            Intent intent = new Intent(getActivity(), StoreClerk_EditStockQtyActivity.class);
            intent.putExtra("invdetails", sc);
            startActivity(intent);

            return true;
        }
        };
        getListView().setOnItemLongClickListener(listener);
    }

    //Click the item will bring user to StockCardActivity to view stock details
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        StationeryCatalogue sc = (StationeryCatalogue) getListAdapter().getItem(position-1);
        Intent intent = new Intent(getActivity(), StoreClerk_StockCardActivity.class);
        intent.putExtra("invdetails1", sc);
        startActivity(intent);
    }
}
