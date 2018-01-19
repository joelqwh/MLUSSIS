package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.logicuniv.mlussis.Catalogue_EmployeeActivity;
import com.logicuniv.mlussis.DisbursementActivity;
import com.logicuniv.mlussis.InventoryActivity;
import com.logicuniv.mlussis.R;

public class StoreClerk_MainActivity extends Activity {

    // The Main Screen probably the login screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_storeclerk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scMenuItem1:
                startActivity(new Intent(this, InventoryActivity.class));
                return true;
            case R.id.scMenuItem2:
                startActivity(new Intent(this, DisbursementActivity.class));
                return true;
            case R.id.scMenuItem3:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
