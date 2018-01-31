package com.logicuniv.mlussis.DeputyHead;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.Employee;
import com.logicuniv.mlussis.Model.Requisition;
import com.logicuniv.mlussis.Model.RequisitionDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

/**
 * Created by e0231991 on 30/1/2018.
 */

public class HeadManageRequisitionArrayAdapter extends ArrayAdapter<Requisition> {

    Requisition req;
    Employee e;

    public HeadManageRequisitionArrayAdapter(@NonNull Context context, ArrayList<Requisition> alReq) {
        super(context, 0,alReq);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {

        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        req = getItem(position);

        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_managereq_deputy, parent, false);

            final TextView tv_empName = convertView.findViewById(R.id.textView__managereq_empname);
            final TextView tv_reqNo = convertView.findViewById(R.id.textView_managereq_reqno);
            final TextView tv_reqDate = convertView.findViewById(R.id.textView_managereq_reqdate);

        new AsyncTask<String, Void, Employee>() {

            @Override
            protected Employee doInBackground(String... params) {

                return e = EmployeeController.getEmployeeById((params[0]));
            }

            protected void onPostExecute(Employee result)
            {
                e=result;
                tv_empName.setText(e.get("EmpName").toString());
                tv_reqNo.setText(req.get("ReqNo").toString());
                tv_reqDate.setText(req.get("DateIssued").toString());
            }
        }.execute(req.get("IssuedBy").toString());


        }
        return convertView;
    }
}
