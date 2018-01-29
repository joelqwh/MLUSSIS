package com.logicuniv.mlussis.StoreClerk;


import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreClerk_DisburseListFragment extends ListFragment {


    public StoreClerk_DisburseListFragment() {
        // Required empty public constructor
    }

    View header;
    String disbNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        header = inflater.inflate(R.layout.fragment_store_clerk_disburse_row_header, null);
        View v = inflater.inflate(R.layout.inv_list, container, false);

        //ArrayList<DisbursementDetail> scdd = DisbursementDetailController.getCurrentDisbursementDetailsForDepartment();

        new AsyncTask<Void, Void, ArrayList<DisbursementDetail>>(){
            @Override
            protected ArrayList<DisbursementDetail> doInBackground(Void... params){
                return DisbursementDetailController.getCurrentDisbursementDetailsForDepartment();
            }

            @Override
            protected void onPostExecute(ArrayList<DisbursementDetail> result) {
                DisbursementItemArrayAdapter ddadapt = new DisbursementItemArrayAdapter(getActivity(),result);
                setListAdapter(ddadapt);

            }
        }.execute();


        /*Bundle args = getArguments();
        ArrayList<DisbursementDetail> scdd = (ArrayList<DisbursementDetail>)args.getSerializable("disbursementdetails");

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), scdd,
                    R.layout.fragment_store_clerk_disburse_row,
                    new String[]{"ItemNo", "Received"},
                    new int[]{R.id.scDisburseItemDesc, R.id.scDisburseItemQty});

            disbNo = scdd.get(0).toString();

            setListAdapter(adapter);*/
            return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().addHeaderView(header);
    }


}
