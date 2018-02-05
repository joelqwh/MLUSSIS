package com.logicuniv.mlussis.Employee;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.DepartmentController;
import com.logicuniv.mlussis.Backend.EmailController;
import com.logicuniv.mlussis.Backend.EmployeeController;
import com.logicuniv.mlussis.Backend.TempRequisition;
import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.MLussisActivity;
import com.logicuniv.mlussis.Model.Department;
import com.logicuniv.mlussis.Model.EmailTemplate;
import com.logicuniv.mlussis.Model.Employee;
import com.logicuniv.mlussis.Model.RequisitionDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import static com.logicuniv.mlussis.Backend.TempRequisition.details;

public class RequisitionEmployeeActivity extends MLussisActivity {

    RequisitionEmployeeArrayAdapter adapt;
    ListView reqItemList;

    StationeryCatalogue sc;
    String qty;
    String itemNo;

    View header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        setContentView(R.layout.activity_requisition_employee);
        setTitle("Raise Requisition");

        Button button_addItem = (Button) findViewById(R.id.button_requisition_employee_addItem);
        Button button_submitReq = (Button) findViewById(R.id.button_requisition_employee_submit);
        button_submitReq.setEnabled(false);
        Button button_cancelReq = (Button) findViewById(R.id.button_requisition_employee_cancelReq);
        button_cancelReq.setEnabled(false);
        reqItemList = (ListView) findViewById(R.id.listView_requisition_employee_raised);
            header = getLayoutInflater().inflate(R.layout.header_row_list_requisition_employee, null);
            reqItemList.addHeaderView(header, null, false);

        registerForContextMenu(reqItemList);
        reqItemList.setLongClickable(true);


        View.OnClickListener ocl_addItem = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Catalogue_EmployeeActivity.class);
                startActivityForResult(intent,4); //check if Requisition gets lost when adding new item. need to ensure that it stays. StartActivityForResult?

            }
        };
        button_addItem.setOnClickListener(ocl_addItem);

        View.OnClickListener ocl_reject = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RequisitionEmployeeActivity.this, "Requisition removed", Toast.LENGTH_LONG).show();
                finish();

            }
        };
        button_cancelReq.setOnClickListener(ocl_reject);

        View.OnClickListener ocl_submit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        String empNo = LoginController.GetLoggedInEmployeeNumber(getApplicationContext());
                        try{

                            TempRequisition.submitNewRequisition();
                        }
                        catch (Exception e)
                        {
                            Log.e("errorAsync", e.getMessage());
                        }

                        Employee e = EmployeeController.getEmployeeById(empNo);
                        Department d = DepartmentController.getDepartmentById(e.get("DeptCode").toString());
                        Employee head = EmployeeController.getEmployeeById(d.get("DeputyEmpNo"));
                        String headEmail = head.get("Email").toString();

                        EmailController.sendEmail(headEmail, EmailTemplate.GenerateRequisitionSubmittedEmailToDeputySubject(),
                                EmailTemplate.GenerateRequisitionSubmittedEmailToDeputySubject());
                        return null;
                    }

                    protected void onPostExecute(Void result)
                    {
                        setResult(RESULT_OK);
                        finish();
                    }
                }.execute();

            }
        };
        button_submitReq.setOnClickListener(ocl_submit);

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_req_employee_context, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final RequisitionDetail reqDet = (RequisitionDetail) reqItemList.getItemAtPosition(info.position);  //need to check
        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.option1:

                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected Void doInBackground(String... params) {
                        sc = StationeryCatalogueController.searchCatalogueById(params[0]);
                        return null;
                    }

                    protected void onPostExecute(Void result)
                    {
                        final Dialog d = new Dialog(RequisitionEmployeeActivity.this);
                        d.setTitle("Change Quantity of Item");
                        d.setContentView(R.layout.dialog_catalogue_employee);
                        //d.setCancelable(false);
                        Button buttonCancel = d.findViewById(R.id.dialog_catalogue_employee_buttonCancel);
                        Button buttonAdd = d.findViewById(R.id.dialog_catalogue_employee_buttonAddItem);
                        TextView tv_itemNo = d.findViewById(R.id.textView_dialog_catalogue_employee_itemNo);
                        final EditText et_qty = d.findViewById(R.id.editText_dialog_catalogue_employee_qty);
                        tv_itemNo.setText(sc.get("ItemNo").toString());
                        et_qty.setText(reqDet.get("Qty").toString());
                        buttonAdd.setText("Edit Qty");
                        buttonCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                d.dismiss();
                            }
                        });

                        buttonAdd.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                String qty = et_qty.getText().toString();
                                reqDet.put("Qty",qty);
                                //RequisitionDetailController.updateRequisitionDetail(reqDet);
                                Log.e("joel",reqDet.toString());
                                Log.e("joel",details.toString());
                                Toast.makeText(RequisitionEmployeeActivity.this, "Quantity Updated",Toast.LENGTH_SHORT).show();
                                reqItemList.setAdapter(reqItemList.getAdapter());
                                d.dismiss();
                            }
                        });
                        d.show();
                    }
                }.execute((String)reqDet.get("ItemNo"));
                return true;
            case R.id.option2:

                details.remove(reqDet);

                Button button_submitReq = (Button) findViewById(R.id.button_requisition_employee_submit);
                Button button_cancelReq = (Button) findViewById(R.id.button_requisition_employee_cancelReq);

                if (details.size() > 0) {
                    button_submitReq.setEnabled(true);
                    button_cancelReq.setEnabled(true);
                } else {
                    button_submitReq.setEnabled(false);
                    button_cancelReq.setEnabled(false);
                }
                Toast.makeText(RequisitionEmployeeActivity.this, "Item Deleted",Toast.LENGTH_SHORT).show();

                reqItemList.setAdapter(reqItemList.getAdapter());

                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(RESULT_OK==resultCode&& requestCode==4) {
            Bundle b = data.getBundleExtra("bundle");
            qty = b.getString("qty");

            itemNo = b.getString("ItemNo");

            new AsyncTask<String, Void, Void>() {
                @Override
                protected Void doInBackground(String... params) {
                    sc = StationeryCatalogueController.searchCatalogueById(params[0]);
                    return null;
                }

                protected void onPostExecute(Void result) {
                    boolean isAlreadyAdded = false;
                    for(int i = 0; i < details.size(); i++)
                    {
                        if(details.get(i).get("ItemNo").equals(sc.get("ItemNo"))){
                            isAlreadyAdded = true;
                            int sum = Integer.parseInt(details.get(i).get("Qty").toString()) + Integer.parseInt(qty);
                            details.get(i).put("Qty",(String.valueOf(sum)));
                            break;
                        }
                    }

                    if(!isAlreadyAdded) {
                        details.add(new RequisitionDetail("", sc.get("ItemNo"), sc.get("Description"), qty));
                    }
                    //                   adapt = (RequisitionEmployeeArrayAdapter) reqItemList.getAdapter();
                    adapt = new RequisitionEmployeeArrayAdapter(RequisitionEmployeeActivity.this,details);
                    reqItemList.setAdapter(adapt);





                    Button button_submitReq = (Button) findViewById(R.id.button_requisition_employee_submit);
                    Button button_cancelReq = (Button) findViewById(R.id.button_requisition_employee_cancelReq);

                    if (details.size() > 0) {
                        button_submitReq.setEnabled(true);
                        button_cancelReq.setEnabled(true);
                    } else {
                        button_submitReq.setEnabled(false);
                        button_cancelReq.setEnabled(false);
                    }
                }
            }.execute(itemNo);
        }
    }

}
