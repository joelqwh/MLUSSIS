package com.logicuniv.mlussis;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.TempRequisition;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.DepartmentRep.DisbursementActivity;
import com.logicuniv.mlussis.DeputyHead.HeadManageRequisitionActivity;
import com.logicuniv.mlussis.Employee.RequisitionEmployeeActivity;
import com.logicuniv.mlussis.StoreClerk.StatRetActivity;
import com.logicuniv.mlussis.StoreClerk.StoreClerk_DisbursementActivity;

import java.util.ArrayList;

public class MainActivity extends MLussisActivity {

    GridLayout storeClerkMain;
    CardView storeclerk_inventory, storeclerk_retrieval, storeclerk_disbursement, deptemp_newrequisition, deptemp_mydisbursements, deptemp_requisitionreview;
    String[] cardAssignedRole = new String[]{"StoreClerk", "StoreClerk", "StoreClerk","DepartmentEmployee", "DepartmentRepresentative", "DepartmentDeputy"};

    static boolean isBackTwice = false;


    @Override
    public void onBackPressed() {
        if (isBackTwice) {
            LoginController.Logout(getApplicationContext(), this);
        } else {
            isBackTwice = true;
            Toast.makeText(getApplicationContext(), "Press Back Again To Logout", Toast.LENGTH_SHORT).show();
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(2500);
                    } catch (Exception e) {
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

        // Find all Cards & Gridview
        storeClerkMain = findViewById(R.id.storeClerkMainGrid);
        storeclerk_inventory = findViewById(R.id.storeclerk_inventory);
        storeclerk_retrieval = findViewById(R.id.storeclerk_retrieval);
        storeclerk_disbursement = findViewById(R.id.storeclerk_disbursement);
        deptemp_newrequisition = findViewById(R.id.deptemp_newrequisition);
        deptemp_mydisbursements = findViewById(R.id.deptemp_mydisbursements);
        deptemp_requisitionreview = findViewById(R.id.deptemp_requisitionreview);

        // Rearrange the Cards
        new AsyncTask<Void, Void, Void>() {
            ArrayList<String> roles;

            @Override
            protected Void doInBackground(Void... params) {
                roles = LoginController.GetRolesFromCurrentSessionId(getApplicationContext());
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                CardView[] cards = new CardView[]{storeclerk_inventory, storeclerk_retrieval, storeclerk_disbursement, deptemp_newrequisition, deptemp_mydisbursements, deptemp_requisitionreview};
                int buttonsRemoved = 0;
                for (int i = 0; i < cards.length; i++) {
                    if (!roles.contains(cardAssignedRole[i])) {
                        storeClerkMain.removeView(cards[i]);
                        buttonsRemoved++;
                    }
                }

                if(buttonsRemoved % 2 != 0)
                {
                    CardView temp = new CardView(getApplicationContext());
                    temp.setVisibility(View.INVISIBLE);
                    storeClerkMain.addView(temp);
                }
            }
        }.execute();

        // Set Listeners
        storeclerk_inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), InventoryActivity.class), 21);
            }
        });

        storeclerk_retrieval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), StatRetActivity.class), 22);
            }
        });

        storeclerk_disbursement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), StoreClerk_DisbursementActivity.class), 23);
            }
        });

        deptemp_newrequisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TempRequisition.startNewRequisition(LoginController.GetLoggedInEmployeeNumber(getApplicationContext()));
                startActivityForResult(new Intent(getApplicationContext(), RequisitionEmployeeActivity.class), 0);
            }
        });

        deptemp_mydisbursements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), DisbursementActivity.class), 1);
            }
        });

        deptemp_requisitionreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), HeadManageRequisitionActivity.class), 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Requisition submitted", Toast.LENGTH_LONG).show();
        }
    }
}
