package com.logicuniv.mlussis.Employee;


import android.app.Activity;
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
import android.widget.TextView;

import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Catalogue_Employee_ListFragment extends ListFragment {


    String itemNo;
    StationeryCatalogue b;
    EditText et_qty;
    View header;

    public Catalogue_Employee_ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        header = inflater.inflate(R.layout.header_row_list_catalogue_employee, null);
        View v = inflater.inflate(R.layout.list_catalogue_employee, container, false);
        Bundle arg = getArguments();

        if (arg != null) {
            ArrayList<StationeryCatalogue> alsc = (ArrayList<StationeryCatalogue>) arg.getSerializable("catalogue");
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), alsc,
                    R.layout.row_list_confirmation_storeclerk,
                    new String[]{"Description", "Uom"},
                    new int[]{R.id.scConfirmItemDesc, R.id.scConfirmItemQty});
            setListAdapter(adapter);
        }

        return v;

    }

    @Override
    public void onListItemClick(ListView av, View v, int position, long id) {
        super.onListItemClick(av,v,position,id);

        b = (StationeryCatalogue) av.getItemAtPosition(position);

        final Dialog d = new Dialog(getActivity());
        d.setTitle("Add to Requisition");
        d.setContentView(R.layout.dialog_catalogue_employee);
        //d.setCancelable(false);
        Button buttonCancel = d.findViewById(R.id.dialog_catalogue_employee_buttonCancel);
        Button buttonAdd = d.findViewById(R.id.dialog_catalogue_employee_buttonAddItem);
        TextView tv_itemNo = d.findViewById(R.id.textView_dialog_catalogue_employee_itemNo);
        et_qty = d.findViewById(R.id.editText_dialog_catalogue_employee_qty);
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
                        Bundle argus = new Bundle();
                        argus.putSerializable("qty",qty.toString());
                        argus.putSerializable("ItemNo", b.get("ItemNo"));
                        i.putExtra("bundle",argus);

                        getActivity().setResult(Activity.RESULT_OK,i);
                        getActivity().finish();
                        d.dismiss();
                    }
                });
        d.show();

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().addHeaderView(header,null,false);
    }

}
