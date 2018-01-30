package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.logicuniv.mlussis.Backend.App;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.DepartmentRep.DisbursementActivity;
import com.logicuniv.mlussis.DeputyHead.HeadManageRequisitionActivity;
import com.logicuniv.mlussis.Employee.Catalogue_EmployeeActivity;

public class MainActivity extends Activity {

    // The Main Screen probably the login screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO : Remove This
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);

        // Setting App Context for Backend
        // TODO : Find a better way
        App.setAppContext(getApplicationContext());

        //If not logged in, goto login
        if(!LoginController.IsCurrentSessionValid(getApplicationContext()))
        {
            Log.d("MainActivity", "Session Invalid, Need to login again");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
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
            case R.id.LogoutMenuItem:
                LoginController.Logout(getApplicationContext());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
