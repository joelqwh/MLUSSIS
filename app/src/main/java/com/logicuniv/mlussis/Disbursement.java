package com.logicuniv.mlussis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by e0231991 on 17/1/2018.
 */

public class Disbursement extends HashMap<String, String> {

    //final static String baseURL = "http://172.17.252.67/Bookshop13/Service.svc/"; //to change if not will fail

    public Disbursement(String disbNo, String deptCode,String disbDate, String empNo, String colPtNo, String pin,String status) {
        put("DisbursementNo", disbNo);
        put("DeptCode", deptCode);             //getDeptName()
        put("DisbursementDate", disbDate);
        put("RepEmpNo", empNo);             //getEmployee(empNo)
        put("CollectionPointNo", colPtNo);  //getCollectionPoint(colPtNo)
        put("Pin", pin);
        put("Status", status);
    }

    public Disbursement() {


    }



    public static Disbursement getCurrentDisbursementForDepartment(String deptCode) {
//        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "disbursement/" + eid);
//        try {
//            return new Disbursement(b.getString("Id"), b.getString("Title"), b.getString("Category"), b.getString("ISBN"), b.getString("Author"), b.getString("Price"));
//        } catch (Exception e) {
//            Log.e("Book.getBook()", "JSONArray error");
//        }
//        return (null);

        //use and edit above when JSON is up , inform WCF that the parameters are status "unfulfilled" or date is later than start date. what happens when disbursement is 2?

       return new Disbursement("1", "D1","28 January 2018","E1", "CP1", "12345", "Unfulfilled");

    }
//
//
//    public static List<Book> searchBook(Book book) {
//
//        List<Book> list = new ArrayList<Book>();
//        JSONArray a = JSONParser.searchJSONArrayFromUrl(baseURL + "search", book);
//        try {
//            for (int i = 0; i < a.length(); i++) {
//                JSONObject b = a.getJSONObject(i);
//                list.add(new Book(b.getString("Id"), b.getString("Title"), b.getString("Category"), b.getString("ISBN"), b.getString("Author"), b.getString("Price")));
//            }
//        } catch (Exception e) {
//            Log.e("Book.list()", "JSONArray error");
//        }
//        return (list);
//    }

//        public static boolean updateBook(Book book) {
//            JSONObject jBook = new JSONObject();
//            try {
//                jBook.put("Id", book.get("Id"));
//                jBook.put("Title", book.get("Title"));
//                jBook.put("Category",book.get("Category"));
//                jBook.put("ISBN", book.get("ISBN"));
//                jBook.put("Author", book.get("Author"));
//                jBook.put("Price", book.get("Price"));
//            } catch (Exception e) {
//            }
//            String result = JSONParser.postStream(baseURL+"/Update", jBook.toString());
//            Log.d("EUGENE",jBook.toString());
//            Log.d("EUGENE",result);
//            if(result == "true"){
//                return true;
//            }
//            else{
//                Log.e("update", result);
//                return false;
//            }
//        }
//
//        public static boolean addBook(Book book) {
//            JSONObject jBook = new JSONObject();
//            try {
//                jBook.put("Id", book.get("Id"));
//                jBook.put("Title", book.get("Title"));
//                jBook.put("Category",book.get("Category"));
//                jBook.put("ISBN", book.get("ISBN"));
//                jBook.put("Author", book.get("Author"));
//                jBook.put("Price", book.get("Price"));
//            } catch (Exception e) {
//            }
//            String result = JSONParser.postStream(baseURL+"/Add", jBook.toString());
//
//            if(result == "true"){
//                return true;
//            }
//            else{
//                Log.e("Add", result);
//                return false;
//            }
//        }
    }
