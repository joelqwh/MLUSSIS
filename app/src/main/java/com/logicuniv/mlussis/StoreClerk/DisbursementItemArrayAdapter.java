package com.logicuniv.mlussis.StoreClerk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.logicuniv.mlussis.DisbursementDetails;
import com.logicuniv.mlussis.R;
import com.logicuniv.mlussis.RequisitionDetail;
import com.logicuniv.mlussis.StationeryCatalogue;

import java.util.ArrayList;

/**
 * Created by e0231991 on 22/1/2018.
 */

public class DisbursementItemArrayAdapter extends ArrayAdapter<DisbursementDetails> {
    public DisbursementItemArrayAdapter(@NonNull Context context, ArrayList<DisbursementDetails> alReqDet) {
        super(context, 0,alReqDet);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        DisbursementDetails disDet = getItem(position);
        StationeryCatalogue sc = StationeryCatalogue.searchCatalogueById((String)disDet.get("ItemNo"));

        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_confirmation_storeclerk, parent, false);


            TextView conf_description = convertView.findViewById(R.id.scConfirmItemDesc);
            TextView conf_qty = convertView.findViewById(R.id.scConfirmItemQty);
            conf_description.setText(sc.get("Description"));
            conf_qty.setText((String)disDet.get("Received"));
        }
        return convertView;
    }

}
