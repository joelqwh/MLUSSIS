package com.logicuniv.mlussis;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static com.logicuniv.mlussis.DisbursementDetails.getCurrentDisbursementDetailsForDepartment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisbursementListFragment extends Fragment {


    public DisbursementListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_disbursement_list, container, false);
//
        ArrayList<DisbursementDetails> disburse = getCurrentDisbursementDetailsForDepartment();
//        setListAdapter(new SimpleAdapter
//                (getActivity(),disburse, android.R.layout.simple_list_item_2,
//                        new String[]{"ItemNo", "Promised"},
//                        new int[]{ android.R.id.text1, android.R.id.text2}));
//
//        return v;

        TableLayout table = (TableLayout)v.findViewById(R.id.table_deptRep_disbursement_list);

        TableRow firstrow = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.row_disbursement_list, null);
        ((TextView)firstrow.findViewById(R.id.textView_deptRep_disbursement_list_item)).setText("ItemNo.");
        ((TextView)firstrow.findViewById(R.id.textView_deptRep_disbursement_list_qty)).setText("Rec. Qty");
        table.addView((firstrow));

        for(DisbursementDetails d : disburse)
        {
            // Inflate your row "template" and fill out the fields.
            TableRow row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.row_disbursement_list, null);
            ((TextView)row.findViewById(R.id.textView_deptRep_disbursement_list_item)).setText(d.get("ItemNo"));
            Log.e("Joel Qin",d.get("Promised"));
            ((TextView)row.findViewById(R.id.textView_deptRep_disbursement_list_qty)).setText(d.get("Promised"));
            Log.e("Joel Qin",((TextView) row.findViewById(R.id.textView_deptRep_disbursement_list_qty)).getText().toString());
            table.addView(row);
        }
        table.requestLayout();
        return v;
    }

}
