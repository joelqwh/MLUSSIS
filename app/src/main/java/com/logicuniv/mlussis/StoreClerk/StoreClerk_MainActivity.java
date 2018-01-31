package com.logicuniv.mlussis.StoreClerk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

import com.logicuniv.mlussis.InventoryActivity;
import com.logicuniv.mlussis.R;

public class StoreClerk_MainActivity extends Activity {

    GridLayout storeClerkMain;

    // The Main Screen probably the login screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_storeclerk);

        storeClerkMain = (GridLayout)findViewById(R.id.storeClerkMainGrid);
        setSingleClickEvent(storeClerkMain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_storeclerk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scMenuItem1:
                startActivity(new Intent(this, InventoryActivity.class));
                return true;
            case R.id.scMenuItem2:
                startActivity(new Intent(this, StatRetActivity.class));
                return true;
            case R.id.scMenuItem3:
                startActivity(new Intent(this, StoreClerk_DisbursementActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setSingleClickEvent (GridLayout mainGrid) {
        for(int i=0; i<mainGrid.getChildCount();i++)
        {
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view){
                    if (finalI==0)
                    {
                        startActivity(new Intent(getApplicationContext(), InventoryActivity.class));
                        return;
                    }
                    if (finalI==1)
                    {
                        startActivity(new Intent(getApplicationContext(), StatRetActivity.class));
                        return;
                    }
                    if (finalI==2)
                    {
                        startActivity(new Intent(getApplicationContext(), StoreClerk_DisbursementActivity.class));
                        return;
                    }
                }
            });
        }
    }
}
