package com.nightscout.nightscoutga.Background;

/**
 * Created by shivam on 11/28/2014.
/**
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.util.Log;

public class HttpManager {
    public static String getData(RequestPackage p) {
        final String TAG = "MyActivity";
        BufferedReader reader = null;
        String uri = null;

            uri = p.getUri();

        if (p.getMethod().equals("GET")) {
            uri += "?" + p.getEncodedParams();
        }

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod(p.getMethod());

            JSONObject jso = new JSONObject(p.getMap());
            String params = jso.toString();
           // Log.d(TAG,params);
            //String params = "{\"userName\":\"Shia\",\"password\":\"123456\"}";

            if (p.getMethod().equals("POST")) {
                con.setDoOutput(true);
                OutputStreamWriter ostrm = new OutputStreamWriter(con.getOutputStream());
                ostrm.write(params);
                ostrm.flush();
                ostrm.close();
            }

            int response = con.getResponseCode();
            Log.d(TAG, "The response is: " + response);

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
               // Log.d(TAG, line + "\n");
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

        }
    }
}

