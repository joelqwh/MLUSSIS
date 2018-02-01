package com.logicuniv.mlussis.StoreClerk;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.PendingDisbursement;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

/**
 * Created by e0231991 on 22/1/2018.
 */

public class DisbursementPendingArrayAdapter extends ArrayAdapter<DisbursementDetail> {
    public DisbursementPendingArrayAdapter(@NonNull Context context, ArrayList<DisbursementDetail> alDisDet) {
        super(context, 0,alDisDet);
    }

    StationeryCatalogue sc;
    DisbursementDetail disDet;

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        disDet = getItem(position);
        //StationeryCatalogue sc = StationeryCatalogueController.searchCatalogueById(disDet.get("ItemNo"));

        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_store_clerk_disburse_row, parent, false);
            final TextView conf_description = convertView.findViewById(R.id.scDisburseItemDesc);
            final EditText conf_qty = convertView.findViewById(R.id.scDisburseItemQty);
            //conf_description.setText(sc.get("Description"));
            //conf_qty.setText(disDet.get("Received"));

            new AsyncTask<String, Void, StationeryCatalogue>(){

                @Override
                protected StationeryCatalogue doInBackground(String... params){
                    return StationeryCatalogueController.searchCatalogueById(params[0]);
                }

                protected void onPostExecute(StationeryCatalogue result){
                    sc = result;
                    conf_description.setText(sc.get("Description"));
                    conf_qty.setText(disDet.get("Received"));
                }

            }.execute(disDet.get("ItemNo").toString());

        }
        return convertView;
    }

}
