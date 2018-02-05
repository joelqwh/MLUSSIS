package com.logicuniv.mlussis.DeputyHead;

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
    ViewHolder holder;

    public HeadManageRequisitionArrayAdapter(@NonNull Context context, ArrayList<Requisition> alReq) {
        super(context, 0,alReq);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        req = getItem(position);
        View v = convertView;


        if(v==null)
        {
            v = LayoutInflater.from(getContext()).inflate(R.layout.row_list_managereq_deputy, parent, false);
                holder = new ViewHolder();
          holder.empname = v.findViewById(R.id.textView__managereq_empname);
          holder.reqno = v.findViewById(R.id.textView_managereq_reqno);
          holder.reqdate = v.findViewById(R.id.textView_managereq_reqdate);
            v.setTag(holder);

            //getReqEmpDetails(req.get("IssuedBy").toString());

            e= EmployeeController.getEmployeeById(req.get("IssuedBy").toString());

            holder.empname.setText(e.get("EmpName").toString());

            holder.reqno.setText(req.get("ReqNo").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date d = null;
            try {
                d = sdf.parse(req.get("DateIssued").toString());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            holder.reqdate.setText(sdf.format(d));

        }
        else
        {
            holder = (ViewHolder) v.getTag();

            e= EmployeeController.getEmployeeById(req.get("IssuedBy").toString());

            holder.empname.setText(e.get("EmpName").toString());
            holder.reqno.setText(req.get("ReqNo").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date d = null;
            try {
                d = sdf.parse(req.get("DateIssued").toString());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            holder.reqdate.setText(sdf.format(d));

            //getReqEmpDetails(req.get("IssuedBy").toString());
        }
        return v;
    }

    private static class ViewHolder {
        public TextView empname, reqno, reqdate;
    }

    public void getReqEmpDetails(String empNo)
    {
        new AsyncTask<String, Void, Employee>() {

            @Override
            protected Employee doInBackground(String... params) {

                Employee ee = EmployeeController.getEmployeeById(params[0]);
                return ee;
          }

            protected void onPostExecute(Employee result) {
//                e = result;

                holder.empname.setText(result.get("EmpName").toString());

            }
        }.execute(empNo);
    }
}
