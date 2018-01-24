package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    // The Main Screen probably the login screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.empMenuItem1:
                startActivity(new Intent(this, Catalogue_EmployeeActivity.class));
                return true;
            case R.id.empMenuItem2:
                startActivity(new Intent(this, DisbursementActivity.class));
                return true;
            case R.id.empMenuItem3:
                startActivity(new Intent(this,HeadManageRequisitionActivity.class));        //temporary
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
