package com.logicuniv.mlussis.StoreClerk;

/**
 * Created by ET on 27/1/2018.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.logicuniv.mlussis.Backend.RetrievalController;
import com.logicuniv.mlussis.Backend.RetrievalDetailsController;
import com.logicuniv.mlussis.Backend.RetrievalDisplayStationeryController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.InvTableFragment;
import com.logicuniv.mlussis.Model.Retrieval;
import com.logicuniv.mlussis.Model.RetrievalDetails;
import com.logicuniv.mlussis.Model.RetrievalDisplayStationery;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatRetMenuFragment extends Fragment {

    ArrayList<RetrievalDisplayStationery> disStat = RetrievalDisplayStationeryController.getRetrievalDisplayStationery();
    private Spinner bin_Spinner;
    ArrayList<RetrievalDetails> al_rd;

    public StatRetMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store_clerk_retrieval_body, container, false);

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);

        Retrieval ret = RetrievalController.getLatestRetrieval();
        al_rd= RetrievalDetailsController.getRetrievalDetails(ret.get("RetrievalNo"));
        display(al_rd);

        Set<String> al_rs_bin = new LinkedHashSet<>();

        for (RetrievalDetails rd : al_rd)
        {
            al_rs_bin.add(rd.get("Bin"));
        }

        ArrayList<String> al_rd_noDups = new ArrayList<>(al_rs_bin);

        bin_Spinner = v.findViewById(R.id.retBinSpinner);

        ArrayAdapter<String> binSpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,al_rd_noDups);

        bin_Spinner.setAdapter(binSpinnerAdapter);

        AdapterView.OnItemSelectedListener oisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String bin_Spinner_BinChosen = bin_Spinner.getSelectedItem().toString();
                ArrayList<RetrievalDetails> al_rd_new = new ArrayList<>();

                for (RetrievalDetails rd: al_rd)
                {
                    if (rd.get("Bin").equals(bin_Spinner_BinChosen))
                    {
                    al_rd_new.add(rd);
                    }
                }
                display (al_rd_new);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        bin_Spinner.setOnItemSelectedListener(oisl);

//        display(disStat);
//
//        dateSearch = v.findViewById(R.id.retDateSpinner);
//        dateSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String dateSearched = dateSearch.getSelectedItem().toString();
//                ArrayList<RetrievalDisplayStationery> statRetSearch = new ArrayList<>();
//                if (dateSearched.toUpperCase().equals(("Select Date").toUpperCase())) {
//                    statRetSearch = disStat;
//                } else {
//                    statRetSearch = displayStationeryRetrievalViaDate(dateSearched);
//                }
//                display(statRetSearch);
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        return v;
    }

    void display(ArrayList<RetrievalDetails> details) {
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

//    public ArrayList<RetrievalDisplayStationery> displayStationeryRetrievalViaDate(String date) {
//        //Requires WCF Query
//
//        ArrayList<RetrievalDisplayStationery> retds = new ArrayList<>();
//
//
//        //Fake Data and wrong method
//        if (date.equals("2017-01-12")) {
//            RetrievalDisplayStationery rtds1 = new RetrievalDisplayStationery("A1", "Clips Double 1", "40", "0", "40");
//            RetrievalDisplayStationery rtds2 = new RetrievalDisplayStationery("A2", "Clips Double 2", "30", "0", "30");
//            RetrievalDisplayStationery rtds3 = new RetrievalDisplayStationery("A3", "Clips Double 3/4", "20", "0", "20");
//            retds.add(rtds1);
//            retds.add(rtds2);
//            retds.add(rtds3);
//
//        }
//
//        if (date.equals("2017-01-19")) {
//            RetrievalDisplayStationery rtds4 = new RetrievalDisplayStationery("A4", "Clips Paper Large", "90", "0", "90");
//            RetrievalDisplayStationery rtds5 = new RetrievalDisplayStationery("A5", "Clips Paper Medium", "35", "0", "35");
//            retds.add(rtds4);
//            retds.add(rtds5);
//
//        }
//        return retds;
//    }
}