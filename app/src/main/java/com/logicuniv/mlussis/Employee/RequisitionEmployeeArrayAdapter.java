package com.logicuniv.mlussis.Employee;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.RequisitionDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

/**
 * Created by e0231991 on 22/1/2018.
 */

public class RequisitionEmployeeArrayAdapter extends ArrayAdapter<RequisitionDetail> {

    RequisitionDetail reqDet;
    StationeryCatalogue sc;
    public RequisitionEmployeeArrayAdapter(@NonNull Context context, ArrayList<RequisitionDetail> alReqDet) {
        super(context, 0,alReqDet);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
       reqDet = getItem(position);

       sc=StationeryCatalogueController.searchCatalogueById(reqDet.get("ItemNo").toString());



        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_requisition_employee, parent, false);

            TextView tv_description = convertView.findViewById(R.id.textView_req_desc);
            TextView tv_qty = convertView.findViewById(R.id.textView_req_qty);
            TextView tv_uom = convertView.findViewById(R.id.textView_req_uom);
            tv_description.setText(sc.get("Description"));
            tv_qty.setText((String)reqDet.get("Qty"));
            tv_uom.setText(sc.get("Uom"));
        }
        return convertView;
    }

}
