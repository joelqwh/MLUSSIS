package com.logicuniv.mlussis;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Catalogue_Employee_SearchFragment extends Fragment {


    public Catalogue_Employee_SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_catalogue__employee__search, container, false);

        Button clearButton = v.findViewById(R.id.button_employee_clear);
        final EditText searchtext = v.findViewById(R.id.editText_employee_search);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                searchtext.setText(null);
            }
        });

        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                ArrayList<StationeryCatalogue> original = StationeryCatalogue.getCatalogue();
                display(original);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //do code here

                String searchInfo = searchtext.getText().toString();
                StationeryCatalogue sc = new StationeryCatalogue(searchInfo,searchInfo,searchInfo);
                ArrayList<StationeryCatalogue> als = StationeryCatalogue.searchCatalogue(sc);
                display(als);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;

    }

    void display(ArrayList<StationeryCatalogue> details) {
        final String TAG = "DETAILS_FRAG";
        FragmentManager fm = getFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        Fragment frag = new Catalogue_Employee_ListFragment();
        Bundle args = new Bundle();
        args.putSerializable("catalogue", details);
        frag.setArguments(args);
        if (fm.findFragmentByTag(TAG) == null)        //to be amended with framelayout within the list fragment is out
            // fragment not found -- to be added
            trans.add(R.id.listframe_employee_catalogue, frag, TAG);
        else
            // fragment found -- to be replaced
            trans.replace(R.id.listframe_employee_catalogue, frag, TAG);
        trans.commit();
    }

}
