package com.logicuniv.mlussis.StoreClerk;

/**
 * Created by ET on 27/1/2018.
 */

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
import android.widget.SpinnerAdapter;

import com.logicuniv.mlussis.Backend.RetrievalController;
import com.logicuniv.mlussis.Backend.RetrievalDisplayStationeryController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.InvTableFragment;
import com.logicuniv.mlussis.Model.Retrieval;
import com.logicuniv.mlussis.Model.RetrievalDisplayStationery;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatRetMenuFragment extends Fragment {

    ArrayList<RetrievalDisplayStationery> disStat = RetrievalDisplayStationeryController.getRetrievalDisplayStationery();
    private Spinner dateSearch;

    public StatRetMenuFragment() {
        // Required empty public constructor
    }

    public ArrayList<RetrievalDisplayStationery> displayStationeryRetrievalViaDate(String date) {
        //Requires WCF Query

        ArrayList<RetrievalDisplayStationery> retds = new ArrayList<>();


        //Fake Data and wrong method
        if (date.equals("2017-01-12")) {
            RetrievalDisplayStationery rtds1 = new RetrievalDisplayStationery("A1", "Clips Double 1", "40", "0", "40");
            RetrievalDisplayStationery rtds2 = new RetrievalDisplayStationery("A2", "Clips Double 2", "30", "0", "30");
            RetrievalDisplayStationery rtds3 = new RetrievalDisplayStationery("A3", "Clips Double 3/4", "20", "0", "20");
            retds.add(rtds1);
            retds.add(rtds2);
            retds.add(rtds3);

        }

        if (date.equals("2017-01-19")) {
            RetrievalDisplayStationery rtds4 = new RetrievalDisplayStationery("A4", "Clips Paper Large", "90", "0", "90");
            RetrievalDisplayStationery rtds5 = new RetrievalDisplayStationery("A5", "Clips Paper Medium", "35", "0", "35");
            retds.add(rtds4);
            retds.add(rtds5);

        }
        return retds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store_clerk_retrieval_body, container, false);
        display(disStat);

        dateSearch = v.findViewById(R.id.retDateSpinner);
        dateSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dateSearched = dateSearch.getSelectedItem().toString();
                ArrayList<RetrievalDisplayStationery> statRetSearch = new ArrayList<>();
                if (dateSearched.toUpperCase().equals(("Select Date").toUpperCase())) {
                    statRetSearch = disStat;
                } else {
                    statRetSearch = displayStationeryRetrievalViaDate(dateSearched);
                }
                display(statRetSearch);


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

    void display(ArrayList<RetrievalDisplayStationery> details) {
        final String TAG = "RETTABLE_FRAG";
        FragmentManager fm = getFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        Fragment frag = new StatRetTableFragment();
        Bundle args = new Bundle();
        args.putSerializable("displaystatret", details);
        frag.setArguments(args);
        if (fm.findFragmentByTag(TAG) == null)
            trans.add(R.id.retDetailsFragment, frag, TAG);
        else
            trans.replace(R.id.retDetailsFragment, frag, TAG);
        trans.commit();
    }

}