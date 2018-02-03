package com.logicuniv.mlussis.Backend;

import android.util.Log;

import com.logicuniv.mlussis.Model.StationeryCatalogue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


//JSONParser not completed! - 17Jan2018
public class JSONParser {

    static int defaultTimeoutMilli = 10000;

    public static String getStream(String url) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(defaultTimeoutMilli);
            conn.setRequestMethod("GET");
            conn.connect();
            is = conn.getInputStream();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            is.close();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        return(sb.toString());
    }

    static String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            is.close();
        } catch (Exception e) {
            Log.e("readStream Exception", e.getMessage());
        }
        return(sb.toString());
    }

    public static String postStream(String url, String data) {
        InputStream is = null;
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(defaultTimeoutMilli);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setFixedLengthStreamingMode(data.getBytes().length);
            conn.connect();
            OutputStream os = new BufferedOutputStream(conn.getOutputStream());
            os.write(data.getBytes());
            os.flush();
            is = conn.getInputStream();
        } catch (UnsupportedEncodingException e) {
            Log.e("postStream Exception", e.getMessage());
        } catch (Exception e) {
            Log.e("postStream Exception", e.getMessage());
        }
        return readStream(is);
    }

    public static JSONObject getJSONFromUrl(String url) {
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(getStream(url));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }

    public static JSONArray getJSONArrayFromUrl(String url) {
        JSONArray jArray = null;
        try {
            jArray = new JSONArray(getStream(url));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing array " + e.toString());
        }
        return jArray;
    }

}