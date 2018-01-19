package com.logicuniv.mlussis;


import android.app.Dialog;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Catalogue_Employee_ListFragment extends ListFragment {


    String itemNo;

    public Catalogue_Employee_ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.list_catalogue_employee, container, false);
        Bundle arg = getArguments();

        if (arg != null) {
            ArrayList<StationeryCatalogue> alsc = (ArrayList<StationeryCatalogue>) arg.getSerializable("catalogue");
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), alsc,
                    R.layout.row_list_catalogue_employee,
                    new String[]{"Category", "Description", "Uom"},
                    new int[]{R.id.textView_cat_emp, R.id.textView_cat_desc, R.id.textView_cat_uom});
            setListAdapter(adapter);
        }
        return v;

        //*******************************************TABLELAYOUT CODE(HAVE ERRORS)******************************************************
//        View v = inflater.inflate(R.layout.fragment_catalogue__employee__list, container, false);
//        Bundle arg = getArguments();
//            TableLayout table = (TableLayout) v.findViewById(R.id.table_catalogue_employee);
//
//            TableRow firstrow = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.row_catalogue_employee, null);
//            ((TextView) firstrow.findViewById(R.id.textView_catalogue_employee_list_category)).setText("Cat.");
//            ((TextView) firstrow.findViewById(R.id.textView_catalogue_employee_list_description)).setText("Description");
//            ((TextView) firstrow.findViewById(R.id.textView_catalogue_employee_list_uom)).setText("U.O.M.");
//            table.addView((firstrow));
//
//
//            if(arg!=null) {
//            ArrayList<StationeryCatalogue> alsc = (ArrayList<StationeryCatalogue>) arg.getSerializable("catalogue");
//            for (StationeryCatalogue d : alsc) {
//                // Inflate your row "template" and fill out the fields.
//                TableRow row = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.row_catalogue_employee, null);
//                ((TextView) firstrow.findViewById(R.id.textView_catalogue_employee_list_category)).setText(d.get("Category"));
//                ((TextView) firstrow.findViewById(R.id.textView_catalogue_employee_list_description)).setText(d.get("Description"));
//                ((TextView) firstrow.findViewById(R.id.textView_catalogue_employee_list_uom)).setText(d.get("Uom"));
//                row.setTag(d.get("ItemNo"));
//                row.setClickable(true);
//                row.setOnClickListener(tablerowOnClickListener);
//                table.addView(row);
//            }
//            table.requestLayout();
//        }
//        return v;
//    }
//
//    private View.OnClickListener tablerowOnClickListener = new View.OnClickListener() {
//        public void onClick(View v) {
//            String itemNo = v.getTag().toString();
//            Intent intent = new Intent(getActivity(), CatalogueEmployeeDetailsActivity.class);        //not done yet
//            intent.putExtra("ItemNo", itemNo);
//            startActivity(intent);
//        }
//    };
//        Fragment for above
//            <fragment
//        android:id="@+id/fragment4"
//        android:name="com.logicuniv.mlussis.Catalogue_Employee_ListFragment"
//        android:layout_width="wrap_content"
//        android:layout_height="20dp"
//        android:layout_marginBottom="336dp"
//        android:layout_marginEnd="8dp"
//        android:layout_marginStart="8dp"
//        android:layout_marginTop="8dp"
//        app:layout_constraintBottom_toBottomOf="parent"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toBottomOf="@+id/fragment"
//        app:layout_constraintVertical_bias="0.0" />


    }

    @Override
    public void onListItemClick(ListView av, View v, int position, long id) {
        final StationeryCatalogue b = (StationeryCatalogue) getListAdapter().getItem(position);

        final Dialog d = new Dialog(getActivity());
        d.setTitle("Add to Requisition");
        d.setContentView(R.layout.dialog_catalogue_employee);
        //d.setCancelable(false);
        Button buttonCancel = v.findViewById(R.id.dialog_catalogue_employee_buttonCancel);
        Button buttonAdd = v.findViewById(R.id.dialog_catalogue_employee_buttonAddItem);
        TextView tv_itemNo = v.findViewById(R.id.textView_dialog_catalogue_employee_itemNo);
        final EditText et_qty = v.findViewById(R.id.editText_dialog_catalogue_employee_qty);
        tv_itemNo.setText(b.get("ItemNo").toString());
        et_qty.setText("1");

                buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

                buttonAdd.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        String qty = et_qty.getText().toString();
                        Intent i = new Intent(getActivity(),RequisitionEmployeeActivity.class);
                        i.putExtra("addItem",b);
                        i.putExtra("qty",qty);
                        startActivity(i);
                    }
                });
        d.show();
    }
}
