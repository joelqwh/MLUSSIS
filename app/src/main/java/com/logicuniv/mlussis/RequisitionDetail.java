package com.logicuniv.mlussis;

import android.os.Parcelable;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by e0231991 on 19/1/2018.
 */

public class RequisitionDetail extends HashMap {

    static ArrayList<ArrayList<RequisitionDetail>> alalrd = new ArrayList<>();

    public RequisitionDetail(String reqNo, String itemNo, String qty) {
        put("ReqNo", reqNo);
        put("ItemNo", itemNo);
        put("Qty", qty);
    }

    public static void addRequisitionDetail (ArrayList<RequisitionDetail> alrd)
    {
        alalrd.add(alrd);
        Log.e("joel",alalrd.toString());;
    }

    public static ArrayList<RequisitionDetail> getRequisitionDetail(String rnum)
    {
        RequisitionDetail reqA = new RequisitionDetail("R1","I001","2");
        RequisitionDetail reqB = new RequisitionDetail("R1","I002","3");
        ArrayList<RequisitionDetail> alrrrrd = new ArrayList<>();
        alrrrrd.add(reqA);
        alrrrrd.add(reqB);
        addRequisitionDetail(alrrrrd);      //just for dummy purposes

        ArrayList<RequisitionDetail> pack = new ArrayList<>();
        for (ArrayList<RequisitionDetail> alrd:alalrd)
        {
            for (RequisitionDetail det :alrd)
            {
                if (det.get("ReqNo").equals(rnum))
                {
                    pack.add(det);
                }
            }
        }
        return pack;
    }

}
