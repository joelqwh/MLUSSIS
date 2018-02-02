package com.logicuniv.mlussis.DepartmentRep;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.logicuniv.mlussis.R;

public class DisbursementActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement);

        setTitle("Disbursement");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_disbursement_deptrep, menu);
        if(menu!=null) {
            menu.findItem(R.id.disbDeptRepIteme1).setVisible(true);
            menu.findItem(R.id.disbDeptRepIteme1).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.disbDeptRepIteme1).setIcon(android.R.drawable.ic_popup_sync);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.disbDeptRepIteme1:
                DisbursementListFragment dlf = (DisbursementListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
                dlf.printDisbursementTable();
                DisbursementDetailsFragment ddf = (DisbursementDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
                ddf.printDisbDetails();
                PinDisbursementFragment pdf = (PinDisbursementFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
                pdf.printPinDisbDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;

    public interface FragmentRefreshListener{
        void onRefresh();
    }



}
