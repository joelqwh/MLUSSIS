package com.logicuniv.mlussis.StoreClerk;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.AdjustmentVoucherController;
import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.EmailController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.Model.Department;
import com.logicuniv.mlussis.Model.EmailTemplate;
import com.logicuniv.mlussis.Model.Employee;
import com.logicuniv.mlussis.R;

import java.util.HashMap;

public class StoreClerk_EditStockQtyActivity extends MLussisActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk__edit_stock_qty);
        Bundle b = getIntent().getExtras();
        HashMap<String, String> sc = (HashMap<String, String>) b.get("invdetails");


        final TextView textEditStockItemCode = (TextView) findViewById(R.id.textStockCardItemCode);
        final TextView textEditStockItemDesc = (TextView) findViewById(R.id.textStockCardItemDesc);
        final TextView textEditStockItemCurrentQty = (TextView) findViewById(R.id.textStockCardItemCurrentQty);
        final EditText EditStockItemActualQty = (EditText) findViewById(R.id.textStockCardItemActualQty);
        final EditText adjReason = (EditText) findViewById(R.id.editTextAdjReason);

        textEditStockItemCode.setText(sc.get("ItemNo"));
        textEditStockItemDesc.setText(sc.get("Description"));
        textEditStockItemCurrentQty.setText(sc.get("CurrentQty"));

        Button itemAdjSubmitBtn = (Button) findViewById(R.id.itemAdjSubmitbtn);
        itemAdjSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (adjReason.getText().toString().matches("")) {
                    Toast.makeText(StoreClerk_EditStockQtyActivity.this, "Please enter adjustment reason", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    new AsyncTask<Void, Void, Void>(){
                        @Override
                        protected Void doInBackground(Void... params) {
                            String adjItemCode = textEditStockItemCode.getText().toString();
                            int adjItemActualQty = Integer.parseInt(EditStockItemActualQty.getText().toString());
                            String adjGivenReason = adjReason.getText().toString();

                            try
                            {
                                AdjustmentVoucherController.createAdjustmentVoucher
                                        (
                                                adjItemCode,
                                                adjItemActualQty,
                                                adjGivenReason
                                        );
                            }
                            catch (Exception e)
                            {
                                Log.e("errorAdjAsync", e.getMessage());
                            }

                            String empNo = LoginController.GetLoggedInEmployeeNumber(getApplicationContext());
                            Employee e = EmployeeController.getEmployeeById(empNo);
                            Department d = DepartmentController.getDepartmentById(e.get("DeptCode").toString());
                            Employee supervisor = EmployeeController.getEmployeeById(d.get("DeputyEmpNo"));
                            String supervisorEmail = supervisor.get("Email").toString();

                            EmailController.sendEmail(supervisorEmail, EmailTemplate.GenerateAdjVoucherSubmittedEmailToSupervisorSubject(),
                                    EmailTemplate.GenerateAdjVoucherSubmittedEmailToSupervisor());
                            return null;
                        }

                        protected void onPostExecute(Void result)
                        {
                            setResult(RESULT_OK);
                            finish();

                        }
                    }.execute();

                }
            }
        });
    }
}
