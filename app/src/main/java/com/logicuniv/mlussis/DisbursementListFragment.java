package com.logicuniv.mlussis;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.Model.DisbursementDetail;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisbursementListFragment extends Fragment {

    ArrayList<DisbursementDetail> disburse=null;
    public DisbursementListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_disbursement_list, container, false);



        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                disburse = DisbursementDetailController.getCurrentDisbursementDetailsForDepartment();
                return null;
            }

        }.execute();

        TableLayout table = (TableLayout)v.findViewById(R.id.table_deptRep_disbursement_list);

        TableRow firstrow = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.row_disbursement_list, null);
        ((TextView)firstrow.findViewById(R.id.textView_deptRep_disbursement_list_item)).setText("ItemNo.");
        ((TextView)firstrow.findViewById(R.id.textView_deptRep_disbursement_list_qty)).setText("Rec. Qty");
        table.addView((firstrow));

        if (disburse !=null)
        {
            for(DisbursementDetail d : disburse)
            {
                // Inflate your row "template" and fill out the fields.
                TableRow row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.row_disbursement_list, null);
                ((TextView)row.findViewById(R.id.textView_deptRep_disbursement_list_item)).setText(d.get("ItemNo"));
                ((TextView)row.findViewById(R.id.textView_deptRep_disbursement_list_qty)).setText(d.get("Promised"));
                table.addView(row);
            }
            table.requestLayout();
        }

        return v;
    }

}
