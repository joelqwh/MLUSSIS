package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.logicuniv.mlussis.Backend.App;
import com.logicuniv.mlussis.Backend.FakeRequisition;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.DepartmentRep.DisbursementActivity;
import com.logicuniv.mlussis.DeputyHead.HeadManageRequisitionActivity;
import com.logicuniv.mlussis.Employee.Catalogue_EmployeeActivity;
import com.logicuniv.mlussis.Employee.RequisitionEmployeeActivity;

public class MainActivity extends MLussisActivity {

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
                FakeRequisition.startNewRequisition(LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));
                startActivityForResult(new Intent(this, RequisitionEmployeeActivity.class),0);
                return true;
            case R.id.empMenuItem2:
                startActivityForResult(new Intent(this, DisbursementActivity.class),1);
                return true;
            case R.id.empMenuItem3:
                startActivityForResult(new Intent(this,HeadManageRequisitionActivity.class),3);        //temporary
                return true;
            case R.id.LogoutMenuItem:
                LoginController.Logout(getApplicationContext());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
