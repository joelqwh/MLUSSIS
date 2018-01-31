package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.App;
import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Model.Department;

/**
 * Created by xavie on 31-01-2018.
 */

public class MLussisActivity extends Activity {

    private static boolean isFirstTime = true;

    @Override
    protected void onPause() {
        super.onPause();
        isFirstTime = true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Setting App Context for Backend
        // TODO : Find a better way to get App Context for Controllers
        App.setAppContext(getApplicationContext());

        // Check if We are authenticated when returning from onPause or starting for first time
        if(isFirstTime)
        {
            new AsyncTask<Void, Void, Void>() {
                boolean isAuthenticated = false;
                boolean isConnected = false;

                @Override
                protected Void doInBackground(Void... params) {
                    isConnected = LoginController.IsServerPresent();
                    isAuthenticated = isConnected
                            && LoginController.IsCurrentSessionValid(getApplicationContext());
                    return null;
                }

                @Override
                protected void onPostExecute(Void v) {
                    // If not connected, display toast
                    if(!isConnected)
                    {
                        Log.d("MLussisActivity", "Server Not Found, Need to login again");
                        Toast.makeText(getApplicationContext(), "Server not found", Toast.LENGTH_LONG).show();
                    }

                    // If not logged in, goto login
                    if(!isAuthenticated)
                    {
                        Log.d("MLussisActivity", "Session Invalid, Need to login again");
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }.execute();

            isFirstTime = false;
        }
    }
}
