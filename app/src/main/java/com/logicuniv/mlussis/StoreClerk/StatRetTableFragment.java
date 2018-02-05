package com.logicuniv.mlussis.StoreClerk;

/**
 * Created by ET on 29/1/2018.
 */

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.RetrievalDetails;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatRetTableFragment extends ListFragment {

    public StatRetTableFragment() {
        // Required empty public constructor
    }

    View header;
    StationeryCatalogue scSelected;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        header = inflater.inflate(R.layout.fragment_store_clerk_ret_header,null);

        View contactLayout = inflater.inflate(R.layout.ret_list,
                container, false);


        Bundle args = getArguments();
        final ArrayList<RetrievalDetails> rds = (ArrayList<RetrievalDetails>)args.getSerializable("displaystatret");

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),rds,
                R.layout.fragment_store_clerk_ret_row,
                new String[] {"Bin", "ItemNo", "Description","Actual"},
                new int[]{R.id.itemRetBin, R.id.itemRetItemCode, R.id.itemRetItemDesc,R.id.itemRetActual});

        setListAdapter(adapter);
        return contactLayout;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().addHeaderView(header, null, false);
        getListView().setOnItemLongClickListener(listener);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        RetrievalDetails rdSelected = (RetrievalDetails) getListAdapter().getItem(position-1);
        String rdItemNo = rdSelected.get("ItemNo");

        new AsyncTask<String, Void, Void>(){

            @Override
            protected Void doInBackground(String... params){
                scSelected = StationeryCatalogueController.searchCatalogueById(params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void result)
            {
                Intent intent = new Intent(getActivity(), StoreClerk_StockCardActivity.class);
                intent.putExtra("invdetails1", scSelected);
                startActivity(intent);
            }

        }.execute(rdItemNo);
    }

    AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            RetrievalDetails rdSelected = (RetrievalDetails) getListAdapter().getItem(position-1);
            String rdItemNo = rdSelected.get("ItemNo");

            new AsyncTask<String, Void, Void>(){

                @Override
                protected Void doInBackground(String... params){
                    scSelected = StationeryCatalogueController.searchCatalogueById(params[0]);
                    return null;
                }

                @Override
                protected void onPostExecute(Void result)
                {
                    Intent intent = new Intent(getActivity(), StoreClerk_EditStockQtyActivity.class);
                    intent.putExtra("invdetails", scSelected);
                    startActivity(intent);
                }
            }.execute(rdItemNo);
            return true;
        }
    };
}