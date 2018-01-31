package com.logicuniv.mlussis.Employee;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.logicuniv.mlussis.Backend.FakeRequisition;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Catalogue_Employee_SearchFragment extends Fragment {

    private EditText searchtext;

    private ArrayList<StationeryCatalogue> als;

    public Catalogue_Employee_SearchFragment() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_catalogue__employee__search, container, false);

        Button clearButton = v.findViewById(R.id.button_employee_clear);
        searchtext = v.findViewById(R.id.editText_employee_search);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                searchtext.setText(null);
            }
        });

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                als = StationeryCatalogueController.getCatalogue();
                return null;
            }

            protected void onPostExecute(Void result)
            {
                display(als);
            }
        }.execute();


        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //do code here

                   String searchInfo = searchtext.getText().toString();
                ArrayList<StationeryCatalogue> alsearch = new ArrayList<>();
                   for(StationeryCatalogue sc:als)
                   {
                       if(sc.get("Description").toUpperCase().contains(searchInfo.toUpperCase()))
                       {
                           alsearch.add(sc);
                       }
                   }

                   display(alsearch);
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
        if (fm.findFragmentByTag(TAG) == null)        //to be amended with framelayout when the list fragment is out
            // fragment not found -- to be added
            trans.add(R.id.detailsframe, frag, TAG);
        else
            // fragment found -- to be replaced
            trans.replace(R.id.detailsframe, frag, TAG);
        trans.commit();
    }

}
