package com.logicuniv.mlussis.Backend;

import com.logicuniv.mlussis.Model.RequisitionDetail;

import java.util.ArrayList;

/**
 * Created by e0231991 on 24/1/2018.
 */

public class RequisitionDetailController {

    static ArrayList<ArrayList<RequisitionDetail>> alalrd = new ArrayList<>();

    public static void addRequisitionDetail (ArrayList<RequisitionDetail> alrd)
    {
        alalrd.add(alrd);
//        Log.e("joel",alalrd.toString());;
    }

    public static ArrayList<RequisitionDetail> getRequisitionDetail(String rnum)
    {
//        RequisitionDetail reqA = new RequisitionDetail("R1","I001","2");
//        RequisitionDetail reqB = new RequisitionDetail("R1","I002","3");
//        ArrayList<RequisitionDetail> alrrrrd = new ArrayList<>();
//        alrrrrd.add(reqA);
//        alrrrrd.add(reqB);
//        addRequisitionDetail(alrrrrd);      //just for dummy purposes

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

    public void removeRequisitionDetail(RequisitionDetail rd){

    }

    public void updateRequisitionDetail(RequisitionDetail rd)
    {

    }

}
