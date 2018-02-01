package com.logicuniv.mlussis.Employee;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
        final ViewHolder holder;

        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_requisition_employee, parent, false);
            holder  = new ViewHolder();

            holder.description = (TextView) convertView.findViewById(R.id.textView_req_desc);
            holder.qty = (TextView) convertView.findViewById(R.id.textView_req_qty);
            holder.uom = (TextView) convertView.findViewById(R.id.textView_req_uom);

            //final TextView tv_description = convertView.findViewById(R.id.textView_req_desc);
            //final TextView tv_qty = convertView.findViewById(R.id.textView_req_qty);
            //final TextView tv_uom = convertView.findViewById(R.id.textView_req_uom);
//            retrieveSC(reqDet.get("ItemNo").toString());

            sc = StationeryCatalogueController.searchCatalogueById(reqDet.get("ItemNo").toString());
            holder.description.setText(sc.get("Description"));
            holder.qty.setText((String) reqDet.get("Qty"));
            holder.uom.setText(sc.get("Uom"));


//            new AsyncTask<String, Void, StationeryCatalogue>() {
//
//                @Override
//                protected StationeryCatalogue doInBackground(String... params) {
//
//                    return StationeryCatalogueController.searchCatalogueById(params[0]);
//                }
//
//                protected void onPostExecute(StationeryCatalogue result) {
//                    sc = result;
//                    holder.description.setText(sc.get("Description"));
//                    holder.qty.setText((String) reqDet.get("Qty"));
//                    holder.uom.setText(sc.get("Uom"));
//                }
//            }.execute(reqDet.get("ItemNo").toString());

        }
            else
            {
                holder= (ViewHolder)convertView.getTag();
            }

        return convertView;
    }

    private static class ViewHolder{
        public TextView description,qty,uom;
    }


    public void retrieveSC (String reqDetNo)
    {
        new AsyncTask<String, Void, StationeryCatalogue>() {

            @Override
            protected StationeryCatalogue doInBackground(String... params) {

                return StationeryCatalogueController.searchCatalogueById(params[0]);
            }

            protected void onPostExecute(StationeryCatalogue result) {
                sc=result;
            }
        }.execute(reqDetNo);
    }

}
