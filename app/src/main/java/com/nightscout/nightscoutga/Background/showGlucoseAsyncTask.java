package com.nightscout.nightscoutga.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nightscout.nightscoutga.Models.Glucose;
import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.nightscout.nightscoutga.UI.Fragments.GraphsFragment.updateGraphData;

public class showGlucoseAsyncTask extends AsyncTask <Void, Void, Integer>{

    Context context;
    ProgressDialog pd;
    URL showGlucoseAPI;
    Glucose[] g;
    TextView glucoseLevel;
    boolean success = false;

    public showGlucoseAsyncTask(Context context, TextView glucoseLevel){
        this.context = context;
        this.glucoseLevel = glucoseLevel;
    }

    @Override
    protected void onPreExecute() {
        Functions.toast("Connecting to Server...", context);
    }

    @Override
    protected Integer doInBackground(Void... params) {
        String glucoseAPI = Constants.apiPrefix + "/showGlucose/guardianid/" + Constants.userid + "/patientusername/" + Constants.uName;
        try {
            showGlucoseAPI = new URL(glucoseAPI);
            HttpURLConnection con = (HttpURLConnection) showGlucoseAPI.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            try {
                JSONObject obj = new JSONObject(String.valueOf(response));
                JSONArray jsonArr = obj.getJSONArray("glucoseInfo");
                Gson gson = new Gson();
                /*List jsonObjList = googleJson.fromJson(String.valueOf(jsonArr), List.class);*/
                int i = (jsonArr.length() - 5) > 0 ? jsonArr.length() - 5 : 0;
                g = new Glucose[jsonArr.length() - i];
                for(int j = 0; i < jsonArr.length() && j < g.length; i++, j++){
                    g[j] = gson.fromJson(jsonArr.get(i).toString(), Glucose.class);
                }
                in.close();
                if(g != null) {
                    updateGraphData(g);
                    success = true;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    protected void onPostExecute(Integer responseInt) {
        super.onPostExecute(responseInt);
        if(success == true){
            glucoseLevel.setText("" + g[g.length - 1].getGlucoseValue());
            Functions.toast("Glucose Details retrieved", context);
        }
        else
            Functions.toast("Retrieval Failed", context);
    }
}