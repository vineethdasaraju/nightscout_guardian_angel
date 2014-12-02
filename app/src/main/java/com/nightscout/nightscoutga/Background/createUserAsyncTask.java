package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class createUserAsyncTask extends AsyncTask<Void, Void, Integer> {

    JSONObject obj;
    ProgressDialog pd;
    Activity app;
    Context callActivity;
    String password;
    int responseCode;

    public createUserAsyncTask(JSONObject obj, Activity app) {
        this.obj = obj;
        this.app = app;
        try {
            password = obj.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer doInBackground(Void... params) {
        String response = "";
        String content = obj.toString();
        DataOutputStream outputStream = null;

        URL new_url;
        try {
            new_url = new URL(Constants.apiRegister2);
            HttpURLConnection connection = (HttpURLConnection) new_url
                    .openConnection();
            connection.setRequestMethod(Constants.HTTP_POST);
            connection.setRequestProperty("Content-Type",
                    Constants.BG_SERVICE_CONTENT_TYPE_JSON);
            connection.setRequestProperty("Connection", "Keep-Alive");
            outputStream = new DataOutputStream(connection.getOutputStream());
            byte[] BytesToBeSent = content.getBytes();
            if (BytesToBeSent != null) {
                outputStream.write(BytesToBeSent, 0, BytesToBeSent.length);
            }
            responseCode = connection.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(app, null, "Creating your account.");
        pd.setCancelable(true);
    }

    @Override
    protected void onPostExecute(Integer responseC) {
        pd.dismiss();
        if(responseC == 200){
            String userEmail = null;
            try {
                userEmail = obj.getString("emailId");
                Functions.toast("Successfully registered, loggin you in.", app);
                LoginAsyncTask task = new LoginAsyncTask(app, userEmail, password);
                task.execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Functions.toast("An error has occured while creating the account. Please try again later.", app);
        }
    }
}