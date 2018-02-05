package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.DisbursementController;
import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.PendingDisbursement;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.Model.Department;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;


public class StoreClerk_DisbursementActivity extends MLussisActivity {
    Spinner spinner_dept;
    TextView tv_deptRep;
    ListView lv_disList;
    String repName;
    String repEmpNo;
    Button button_confirmDisburse;
    static String deptCode;
    static ArrayList<Department> alDept = new ArrayList<>();
    static ArrayList<DisbursementDetail> deptDisDet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk_disbursement);

        spinner_dept = (Spinner) findViewById(R.id.scDisburseDeptspinner);
        tv_deptRep = (TextView) findViewById(R.id.scDisburseRepText);
        lv_disList = (ListView) findViewById(R.id.scDisburseList);
        button_confirmDisburse = (Button) findViewById(R.id.confirmDisburseQtybtn);
        View header = getLayoutInflater().inflate(R.layout.fragment_store_clerk_disburse_row_header, null);
        lv_disList.addHeaderView(header, null, false);

        pop_spinner();

        spinner_dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deptCode = alDept.get(position).get("DeptCode");
                pop_rep(deptCode);
                pop_disList(deptCode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        button_confirmDisburse.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                int size = deptDisDet.size();
                for (int i=1, j = 0;j<deptDisDet.size();i++, j++)
                {
                    View view = lv_disList.getChildAt(i);
                    view = lv_disList.getAdapter().getView(i, null, lv_disList);
                    EditText et = view.findViewById(R.id.scDisburseItemQty);
                    TextView tv = view.findViewById(R.id.scDisburseItemDesc);
                        DisbursementDetail ddd = deptDisDet.get(j);
                        ddd.put("Received", et.getText().toString());
                        Log.e("disburse", ddd.get("ItemNo"));
                        Log.e("disburse", ddd.get("Received"));
                }

                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected Void doInBackground(Void... params){
                        PendingDisbursement.updatePendingDisDetsforDepartment();
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result){

                        Intent intent = new Intent(getApplicationContext(), StoreClerk_ConfirmationActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("deptName", spinner_dept.getSelectedItem().toString());
                        b.putSerializable("repName", tv_deptRep.getText().toString());
                        b.putSerializable("deptCode", deptCode);
                        String disbNo = deptDisDet.get(0).get("DisbursementNo");
                        //String disbNo = DisbursementController.getCurrentDisbursementForDepartment(deptCode).get("DisbursementNo");
                        b.putSerializable("disbNo", disbNo);
                        intent.putExtra("bundle", b);
                        startActivityForResult(intent,0);
                    }
                }.execute();


            }
        });
    }


    private void pop_spinner() {
        new AsyncTask<Void, Void, Void>() {

            String[] deptName;
            String repName;

            @Override
            protected Void doInBackground(Void... params) {
                alDept = DepartmentController.getAllDepartments();
                Department[] alldeptarray = alDept.toArray(new Department[alDept.size()]);
                deptName = new String[alldeptarray.length];
                for (int i = 0; i < deptName.length; i++) {
                    deptName[i] = alDept.get(i).get("DeptName");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, deptName);
                spinner_dept.setAdapter(adapter);
            }
        }.execute();
    }

    private void pop_rep(String deptCode){
        new AsyncTask<String, Void, Void>(){

            @Override
            protected Void doInBackground(String... params) {
            repEmpNo = DepartmentController.getDepartmentById(params[0]).get("RepEmpNo").toString();
            repName = EmployeeController.getEmployeeById(repEmpNo).get("EmpName").toString();
            return null;
            }

            @Override
            protected void onPostExecute(Void result){
                tv_deptRep.setText(repName);
            }
        }.execute(deptCode);
    }

    private void pop_disList(String deptCode){
        new AsyncTask<String, Void, Boolean>(){
            boolean isDisbursementPresent = false;
            @Override
            protected Boolean doInBackground(String... params){
                try {
                    deptDisDet = PendingDisbursement.getPendingDisDetsforDepartment(params[0]);
                    isDisbursementPresent = true;
                }
                catch (Exception e)
                {
                    isDisbursementPresent = false;
                }
                return isDisbursementPresent;
            }

            @Override
            protected void onPostExecute(Boolean result){
                if (isDisbursementPresent) {
                    SimpleAdapter ddadapt = new SimpleAdapter(getApplicationContext(),deptDisDet,R.layout.fragment_store_clerk_disburse_row,
                            new String[]{"Description", "Received"},
                            new int[]{R.id.scDisburseItemDesc, R.id.scDisburseItemQty});
                    lv_disList.setAdapter(ddadapt);
                }
                else if (!isDisbursementPresent)
                {
                    lv_disList.setAdapter(null);
                }
            }
        }.execute(deptCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0 && resultCode == RESULT_OK)
        {
            finish();
        }
    }
}
