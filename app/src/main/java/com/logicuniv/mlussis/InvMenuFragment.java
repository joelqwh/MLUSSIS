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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvMenuFragment extends Fragment {

    ArrayList<StationeryCatalogue> arraystationery = StationeryCatalogue.getCatalogue();
    private EditText invSearch;
    private Spinner catSearch;

    public InvMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inventory_skeleton,container,false);
        display (arraystationery);

        catSearch = v.findViewById(R.id.catspinner);
        catSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String catSearched = catSearch.getSelectedItem().toString();
                ArrayList<StationeryCatalogue> stationerySearch = new ArrayList<>();
                for (StationeryCatalogue stationeryCatalogue : arraystationery) {
                    if (stationeryCatalogue.get("Category").toUpperCase().equals(catSearched.toUpperCase())) {
                        stationerySearch.add(stationeryCatalogue);
                    }
                    else if (catSearched.toUpperCase().equals(("All items").toUpperCase())){
                        stationerySearch = arraystationery;
                    }
                }
                display(stationerySearch);
                //Toast.makeText(getActivity(), catSearched, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Search Function
        invSearch = v.findViewById(R.id.invSearch);
        invSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                display(arraystationery);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //do code here
                String searchInfo = invSearch.getText().toString();
                ArrayList<StationeryCatalogue> stationerySearch = new ArrayList<>();
                for (StationeryCatalogue sc : arraystationery) {
                    if (sc.get("Description").toUpperCase().contains(searchInfo.toUpperCase())) {
                        stationerySearch.add(sc);
                    }
                }
                display(stationerySearch);
                //Toast.makeText(getActivity(), arraystationery.toString(), Toast.LENGTH_SHORT).show();
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

}
