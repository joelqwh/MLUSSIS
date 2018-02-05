package com.logicuniv.mlussis.StoreClerk;

/**
 * Created by ET on 27/1/2018.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.RetrievalController;
import com.logicuniv.mlussis.Backend.RetrievalDetailsController;
import com.logicuniv.mlussis.Model.Retrieval;
import com.logicuniv.mlussis.Model.RetrievalDetails;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatRetMenuFragment extends Fragment {

    private Spinner bin_Spinner;
    ArrayList<RetrievalDetails> al_rd;
    Retrieval ret;

    public StatRetMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store_clerk_retrieval_body, container, false);
        bin_Spinner = v.findViewById(R.id.retBinSpinner);
        final TextView tv_retDate = v.findViewById(R.id.textView_retDate_storeClerk);

        new AsyncTask<Void, Void, ArrayList<RetrievalDetails>>() {
            @Override
            protected ArrayList<RetrievalDetails> doInBackground(Void... params) {
                ret = RetrievalController.getLatestRetrieval();
                return RetrievalDetailsController.getRetrievalDetails(ret.get("RetrievalNo"));
            }

            protected void onPostExecute(ArrayList<RetrievalDetails> result)
            {
               al_rd = result;

                display(al_rd);

                Set<String> al_rs_bin = new LinkedHashSet<>();

                for (RetrievalDetails rd : al_rd)
                {
                    al_rs_bin.add(rd.get("Bin"));
                }

                ArrayList<String> al_rd_noDups = new ArrayList<>(al_rs_bin);

                ArrayAdapter<String> binSpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,al_rd_noDups);

                bin_Spinner.setAdapter(binSpinnerAdapter);

                tv_retDate.setText(getString(R.string.retrievalDate) + " " + ret.get("Date").toString());
            }
        }.execute();


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

}