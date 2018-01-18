package com.logicuniv.mlussis;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Catalogue_Employee_ListFragment extends Fragment {


    String itemNo;

    public Catalogue_Employee_ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_catalogue__employee__list, container, false);

        Bundle arg = getArguments();

        ArrayList<StationeryCatalogue> alsc = (ArrayList<StationeryCatalogue>)arg.getSerializable("catalogue");

        TableLayout table = (TableLayout)v.findViewById(R.id.table_catalogue_employee);

        TableRow firstrow = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.row_catalogue_employee, null);
        ((TextView)firstrow.findViewById(R.id.textView_catalogue_employee_list_category)).setText("Cat.");
        ((TextView)firstrow.findViewById(R.id.textView_catalogue_description)).setText("Description");
        ((TextView)firstrow.findViewById(R.id.textView_catalogue_employee_list_uom)).setText("U.O.M.");
        table.addView((firstrow));

        for(StationeryCatalogue d : alsc)
        {
            // Inflate your row "template" and fill out the fields.
            TableRow row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.row_catalogue_employee, null);
            ((TextView)firstrow.findViewById(R.id.textView_catalogue_employee_list_category)).setText(d.get("Category"));
            ((TextView)firstrow.findViewById(R.id.textView_catalogue_description)).setText(d.get("Description"));
            ((TextView)firstrow.findViewById(R.id.textView_catalogue_employee_list_uom)).setText(d.get("Uom"));
            row.setTag(d.get("ItemNo"));
            row.setClickable(true);
            row.setOnClickListener(tablerowOnClickListener);
            table.addView(row);
        }
        table.requestLayout();
        return v;
    }

    private View.OnClickListener tablerowOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            String itemNo = v.getTag().toString();
//            Intent intent = new Intent(getActivity(), CatalogueDetailsActivity.class);        //not done yet
//            intent.putExtra("ItemNo", itemNo);
//            startActivity(intent);
        }
    };



}
