package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.InventoryActivity;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.R;

public class StoreClerk_MainActivity extends MLussisActivity {

    GridLayout storeClerkMain;

    static boolean isBackTwice = false;



    @Override
    public void onBackPressed() {
        if (isBackTwice) {
            LoginController.Logout(getApplicationContext());
        } else {
            isBackTwice = true;
            Toast.makeText(getApplicationContext(),"Press Back Again To Logout", Toast.LENGTH_SHORT).show();
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try{
                        Thread.sleep(2500);
                    }catch (Exception e){
                        Log.e("LogoutDelay", e.getMessage());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    isBackTwice = false;
                }
            }.execute();
        }
    }


    // The Main Screen probably the login screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_storeclerk);

        storeClerkMain = (GridLayout) findViewById(R.id.storeClerkMainGrid);
        setSingleClickEvent(storeClerkMain);
    }

    private void setSingleClickEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {
                        startActivity(new Intent(getApplicationContext(), InventoryActivity.class));
                        return;
                    }
                    if (finalI == 1) {
                        startActivity(new Intent(getApplicationContext(), StatRetActivity.class));
                        return;
                    }
                    if (finalI == 2) {
                        startActivityForResult(new Intent(getApplicationContext(), StoreClerk_DisbursementActivity.class), 29);
                        return;
                    }
                }
            });
        }
    }
}
