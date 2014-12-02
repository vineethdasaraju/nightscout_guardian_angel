package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import com.nightscout.nightscoutga.util.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AcknowledgeAsyncTask extends AsyncTask<Void, Void, Integer>{

    int responseCode;
    ProgressDialog pd = null;
    Context ctx;
    Activity appContext;
    Button viewPatientDetails, acknowledge;

    public AcknowledgeAsyncTask(Context ctx, Activity appContext, Button viewPatientDetails, Button acknowledge) {
        this.ctx = ctx;
        this.appContext = appContext;
        this.viewPatientDetails = viewPatientDetails;
        this.acknowledge = acknowledge;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        String url = Constants.acknowledgeEmergency;
        try {
            URL acknowledgeAPI = new URL(url);
            HttpURLConnection con = (HttpURLConnection) acknowledgeAPI.openConnection();
            con.setRequestMethod(Constants.HTTP_GET);
            responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(appContext, null, "Sending Confirmation....");
    }

    @Override
    protected void onPostExecute(Integer responseCode) {
        pd.dismiss();
        if(responseCode == 200){
            acknowledge.setVisibility(View.GONE);
        }
    }
}
