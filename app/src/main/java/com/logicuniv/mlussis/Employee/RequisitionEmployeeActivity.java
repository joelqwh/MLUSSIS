package com.logicuniv.mlussis.Employee;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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

import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.Backend.RequisitionController;
import com.logicuniv.mlussis.Backend.RequisitionDetailController;
import com.logicuniv.mlussis.Backend.StationeryCatalogueController;
import com.logicuniv.mlussis.Model.Requisition;
import com.logicuniv.mlussis.Model.RequisitionDetail;
import com.logicuniv.mlussis.Model.StationeryCatalogue;
import com.logicuniv.mlussis.R;

import java.util.ArrayList;
import java.util.Calendar;

public class RequisitionEmployeeActivity extends Activity {



    Requisition req = new Requisition();
    ArrayList<RequisitionDetail> reqDetList=new ArrayList<>();
    SharedPreferences pref;
    RequisitionEmployeeArrayAdapter adapt;
    String saveReq;
    SharedPreferences.Editor edit;
    ListView reqItemList;

    StationeryCatalogue sc;
    String qty=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        setContentView(R.layout.activity_requisition_employee);
        setTitle("Raise Requisition");
        //restoreInstance(savedInstanceState);
        pref = getSharedPreferences("requisition",MODE_PRIVATE); //PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String requisitionNo = pref.getString("ReqNo",""); //use this to get Requisition and RequsitionDetail
        //Log.e("joel",requisitionNo.toString()); y

        Button button_addItem = (Button) findViewById(R.id.button_requisition_employee_addItem);
        Button button_submitReq = (Button) findViewById(R.id.button_requisition_employee_submit);
        Button button_cancelReq = (Button) findViewById(R.id.button_requisition_employee_cancelReq);
        reqItemList = (ListView) findViewById(R.id.listView_requisition_employee_raised);
        View header = getLayoutInflater().inflate(R.layout.header_row_list_requisition_employee,null);
        reqItemList.addHeaderView(header,null,false);

        registerForContextMenu(reqItemList);
        reqItemList.setLongClickable(true);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("bundle");

        String itemNo = b.getString("ItemNo");

        sc = StationeryCatalogueController.searchCatalogueById(itemNo);

        qty =b.getString("qty");

        if(requisitionNo!=null)
        {
            req= RequisitionController.getRequisitionById(requisitionNo);
            reqDetList= RequisitionDetailController.getRequisitionDetail(requisitionNo);
            RequisitionDetail rd = new RequisitionDetail(requisitionNo, sc.get("ItemNo"), sc.get("Description"),qty);
            RequisitionDetailController.addRequisitionDetail(rd);
        }
        else
        {
            saveReq = RequisitionController.CreateNewRequisition();
            req = RequisitionController.getRequisitionById(saveReq);
            RequisitionDetail rd = new RequisitionDetail(saveReq, sc.get("ItemNo"), sc.get("Description"),qty);
            RequisitionDetailController.addRequisitionDetail(rd);
            reqDetList = RequisitionDetailController.getRequisitionDetail(saveReq);

            pref.edit().putString("ReqNo",saveReq);
            pref.edit().apply();

        }
        adapt = new RequisitionEmployeeArrayAdapter(RequisitionEmployeeActivity.this,reqDetList);
        reqItemList.setAdapter(adapt);

        View.OnClickListener ocl_addItem = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                edit = pref.edit();
//                saveReq = (String)req.get("ReqNo");
//                edit.putString("ReqNo",saveReq);
//                edit.apply();
                //will change to add new Requisition and RequisitionDetails when it is up, SharedPref doesnt really work well here.
                Intent intent = new Intent(RequisitionEmployeeActivity.this,Catalogue_EmployeeActivity.class);
                startActivity(intent); //check if Requisition gets lost when adding new item. need to ensure that it stays. StartActivityForResult?

            }
        };
        button_addItem.setOnClickListener(ocl_addItem);

        View.OnClickListener ocl_reject = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequisitionController.removeRequisition(req);
                RequisitionDetailController.removeRequisitionDetails((RequisitionDetail[])reqDetList.toArray());

                adapt.clear();              //removeRequisition
                edit.remove("ReqNo");       //removeRequisitionDetail
                edit.apply();
                saveReq = null;
                Toast.makeText(RequisitionEmployeeActivity.this, "Requisition removed", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RequisitionEmployeeActivity.this,Catalogue_EmployeeActivity.class);
                startActivity(intent);


            }
        };
        button_cancelReq.setOnClickListener(ocl_reject);

        View.OnClickListener ocl_submit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequisitionController.addRequisition(req);
                RequisitionDetailController.addRequisitionDetails((RequisitionDetail[])reqDetList.toArray());

                edit.remove("ReqNo");
                edit.apply();
                saveReq = null;
                adapt.clear();
                Toast.makeText(RequisitionEmployeeActivity.this, "Requisition submitted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RequisitionEmployeeActivity.this,Catalogue_EmployeeActivity.class);
                startActivity(intent);

//                RequisitionController.addRequisition(req);
//                RequisitionDetailController.addRequisitionDetails(reqDetList);

//                req.clear();
//                reqDetList.clear(); //or set it to null?
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

                sc = StationeryCatalogueController.searchCatalogueById((String)reqDet.get("ItemNo"));

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
                                Log.e("joel",reqDet.toString());
                        Toast.makeText(RequisitionEmployeeActivity.this, "Quantity Updated",Toast.LENGTH_SHORT).show();
                       reqItemList.setAdapter(reqItemList.getAdapter());
                        d.dismiss();
                    }
                });
                d.show();
                return true;
            case R.id.option2:

                RequisitionDetailController.removeRequisitionDetail(reqDet);

                Toast.makeText(RequisitionEmployeeActivity.this, "Item Deleted",Toast.LENGTH_SHORT).show();

                reqItemList.setAdapter(reqItemList.getAdapter());

               // RequisitionDetailController.removeRequisitionDetail(reqDet);
                //reqDetList.remove(reqDet);      //removeRequisitionDetail(RequisitionDetail);
                return true;
            default:
                return false;
        }
    }
}
