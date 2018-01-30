package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.DisbursementController;
import com.logicuniv.mlussis.Backend.DisbursementDetailController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Model.Department;
import com.logicuniv.mlussis.Model.DisbursementDetail;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;


public class StoreClerk_DisbursementActivity extends Activity {
    Spinner spinner_dept;
    TextView tv_deptRep;
    ListView lv_disList;
    String repName;
    String repEmpNo;
    static ArrayList<Department> alDept = new ArrayList<>();
    static ArrayList<DisbursementDetail> deptDisDet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_clerk_disbursement);

        spinner_dept = (Spinner) findViewById(R.id.scDisburseDeptspinner);
        tv_deptRep = (TextView) findViewById(R.id.scDisburseRepText);
        lv_disList = (ListView) findViewById(R.id.scDisburseList);
        final Button button_confirmDisburse = (Button) findViewById(R.id.confirmDisburseQtybtn);

        pop_spinner();

        spinner_dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String deptCode = alDept.get(position).get("DeptCode");
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
                Intent intent = new Intent(getApplicationContext(), StoreClerk_ConfirmationActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("deptName", spinner_dept.getSelectedItem().toString());
                b.putSerializable("repName", tv_deptRep.getText().toString());
                String disbNo = DisbursementDetail.getCurrentDisbursementDetailsForDepartment().get(0).get("DisbursementNo");
                b.putSerializable("disbNo", disbNo);
                intent.putExtra("bundle", b);
                startActivity(intent);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, deptName);
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
        new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... params){
                String disbursementNo = DisbursementController.getCurrentDisbursementForDepartment(params[0]).get("DisbursementNo");
                deptDisDet = DisbursementDetailController.getCurrentDisbursementDetailsOf(disbursementNo);
                return null;
            }

            @Override
            protected void onPostExecute(Void result){
                DisbursementPendingArrayAdapter ddadapt = new DisbursementPendingArrayAdapter(StoreClerk_DisbursementActivity.this,deptDisDet);
                lv_disList.setAdapter(ddadapt);
            }
        }.execute(deptCode);
    }
}
