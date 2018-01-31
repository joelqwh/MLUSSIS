package com.logicuniv.mlussis;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.StationeryCatalogue;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvMenuFragment extends Fragment {

    private ArrayList<StationeryCatalogue> arraystationery;
    private EditText invSearch;
    private Spinner catSearch;
    private Spinner binSearch;
    static ArrayList<StationeryCatalogue> stationerySearch = new ArrayList<>();

    public InvMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inventory_skeleton,container,false);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                arraystationery = StationeryCatalogueController.getCatalogue();
                return null;
            }

            @Override
            protected void onPostExecute(Void result){
                display(arraystationery);
            }
        }.execute();

        catSpinner_inflate();
        binSpinner_inflate();

        catSearch = v.findViewById(R.id.catspinner);
        catSearch.setSelection(0,false);
        invSearch = v.findViewById(R.id.invSearch);
        binSearch = v.findViewById(R.id.binspinner);
        binSearch.setSelection(0,false);

        //Filter by Category
        catSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String catSearchInfo = catSearch.getSelectedItem().toString();
                String binSearchInfo = binSearch.getSelectedItem().toString();
                String invSearchInfo = invSearch.getText().toString();

                display(inventorySearch(catSearchInfo, binSearchInfo, invSearchInfo));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        binSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String binSearchInfo = binSearch.getSelectedItem().toString();
                String invSearchInfo = invSearch.getText().toString();
                String catSearchInfo = catSearch.getSelectedItem().toString();

                display(inventorySearch(catSearchInfo, binSearchInfo, invSearchInfo));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Search Function for EditText
        invSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String catSearchInfo = catSearch.getSelectedItem().toString();
                String binSearchInfo = binSearch.getSelectedItem().toString();
                String invSearchInfo = invSearch.getText().toString();

                display(inventorySearch(catSearchInfo, binSearchInfo, invSearchInfo));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return v;
    }

    void display(ArrayList<StationeryCatalogue> details) {
        final String TAG = "INVTABLE_FRAG";
        FragmentManager fm = getFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        Fragment frag = new InvTableFragment();
        Bundle args = new Bundle();
        args.putSerializable("stationerycatalogue", details);
        frag.setArguments(args);
        if (fm.findFragmentByTag(TAG)==null)
            trans.add(R.id.invDetailsFragment, frag, TAG);
        else
            trans.replace(R.id.invDetailsFragment, frag, TAG);
        trans.commit();
    }

    private void catSpinner_inflate(){
        new AsyncTask<Void, Void, Void>() {
            String[] catName;
            ArrayList<String> catList;

            @Override
            protected Void doInBackground(Void... params) {
                catList = StationeryCatalogueController.getCategoryList();
                String[] catArray = catList.toArray(new String[catList.size()]);
                catName = new String[catArray.length+1];
                for (int i = 0; i < catName.length; i++) {
                    if (i==0)
                    {
                        catName[i]="All Categories";
                    }
                    else {
                        catName[i] = catList.get(i-1);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, catName);
                catSearch.setAdapter(adapter);
            }
        }.execute();
    }

    private void binSpinner_inflate(){
        new AsyncTask<Void, Void, Void>() {
            ArrayList<String> binList;
            String[] binName;

            @Override
            protected Void doInBackground(Void... params) {
                binList = StationeryCatalogueController.getBinList();
                String[] binArray = binList.toArray(new String[binList.size()]);
                binName = new String[binArray.length+1];
                for (int i = 0; i < binName.length; i++) {
                    if (i==0)
                    {
                        binName[i] = "All Bins";
                    }
                    else {
                        binName[i] = binList.get(i - 1);
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, binName);
                binSearch.setAdapter(adapter);
            }
        }.execute();
    }

    private ArrayList<StationeryCatalogue> inventorySearch(String catSearchInfo, String binSearchInfo, String invSearchInfo) {

        ArrayList<StationeryCatalogue> stationerySearch = new ArrayList<>();
        for (StationeryCatalogue stationeryCatalogue : arraystationery) {
            if (stationeryCatalogue.get("Category").toUpperCase().equals(catSearchInfo.toUpperCase())
                    && stationeryCatalogue.get("Bin").toUpperCase().equals(binSearchInfo.toUpperCase())
                    && stationeryCatalogue.get("Description").toUpperCase().contains(invSearchInfo.toUpperCase())
                    ) {
                stationerySearch.add(stationeryCatalogue);
            } else if (catSearchInfo.equals("All Categories")
                    && stationeryCatalogue.get("Bin").toUpperCase().equals(binSearchInfo.toUpperCase())
                    && stationeryCatalogue.get("Description").toUpperCase().contains(invSearchInfo.toUpperCase())
                    ) {
                stationerySearch.add(stationeryCatalogue);
            } else if (catSearchInfo.equals("All Categories")
                    && binSearchInfo.equals("All Bins")
                    && stationeryCatalogue.get("Description").toUpperCase().contains(invSearchInfo.toUpperCase())
                    ) {
                stationerySearch.add(stationeryCatalogue);
            }
        }
        return stationerySearch;
    }
}
