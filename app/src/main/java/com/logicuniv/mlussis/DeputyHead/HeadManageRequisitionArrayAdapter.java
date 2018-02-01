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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        req = getItem(position);

        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_managereq_deputy, parent, false);

          final  TextView tv_empName = convertView.findViewById(R.id.textView__managereq_empname);
          final TextView tv_reqNo = convertView.findViewById(R.id.textView_managereq_reqno);
          final TextView tv_reqDate = convertView.findViewById(R.id.textView_managereq_reqdate);

           e= EmployeeController.getEmployeeById(req.get("IssuedBy").toString());

            tv_empName.setText(e.get("EmpName").toString());
            tv_reqNo.setText(req.get("ReqNo").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date d = null;
            try {
                d = sdf.parse(req.get("DateIssued").toString());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            tv_reqDate.setText(sdf.format(d));

//        new AsyncTask<String, Void, Employee>() {
//
//            @Override
//            protected Employee doInBackground(String... params) {
//
//                Employee ee = EmployeeController.getEmployeeById(params[0]);
//                return ee;
//            }
//
//            protected void onPostExecute(Employee result) {
//                e = result;
//                tv_empName.setText(e.get("EmpName").toString());
//                tv_reqNo.setText(req.get("ReqNo").toString());
//                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
//                Date d = null;
//                try {
//                    d = sdf.parse(req.get("DateIssued").toString());
//                } catch (ParseException e1) {
//                    e1.printStackTrace();
//                }
//                tv_reqDate.setText(sdf.format(d));
//            }
//        }.execute(req.get("IssuedBy").toString());


        }
        return convertView;
    }
}
