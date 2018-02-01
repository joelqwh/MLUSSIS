package com.logicuniv.mlussis.DepartmentRep;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.*;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisbursementListFragment extends Fragment {

    ArrayList<DisbursementDetail> disburse=null;

    TableLayout table;
    public DisbursementListFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_disbursement_deptrep, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.disbDeptRepIteme1:
                printDisbursementTable();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_disbursement_list, container, false);
       setHasOptionsMenu(true);

        table = (TableLayout)v.findViewById(R.id.table_deptRep_disbursement_list);

        printDisbursementTable();



        return v;
    }

    public void printDisbursementTable()
    {
        if(disburse!=null) {
            disburse.clear();

            int count = table.getChildCount();
            for (int i = 1; i < count; i++) {
                View child = table.getChildAt(i);
                if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
            }
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                disburse = DisbursementDetailController.getCurrentDisbursementDetailsForDepartment();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (!disburse.isEmpty()) {
                    TableRow firstrow = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.row_disbursement_list, null);
                    ((TextView) firstrow.findViewById(R.id.textView_deptRep_disbursement_list_item)).setText("Item Description.");
                    ((TextView) firstrow.findViewById(R.id.textView_deptRep_disbursement_list_qty)).setText("Rec. Qty");
                    table.addView((firstrow));
                }
                if (disburse != null) {
                    for (DisbursementDetail d : disburse) {
                        // Inflate your row "template" and fill out the fields.
                        TableRow row = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.row_disbursement_list, null);
                        ((TextView) row.findViewById(R.id.textView_deptRep_disbursement_list_item)).setText(d.get("Description"));
                        ((TextView) row.findViewById(R.id.textView_deptRep_disbursement_list_qty)).setText(d.get("Promised"));
                        table.addView(row);
                    }
                    table.requestLayout();
                }
            }

        }.execute();

    }

}
