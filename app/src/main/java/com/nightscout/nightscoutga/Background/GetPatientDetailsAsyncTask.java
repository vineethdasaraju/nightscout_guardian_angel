package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetPatientDetailsAsyncTask extends AsyncTask<Void, Void, String> {

    JSONObject object;
    Context ctx;
    Activity app;
    String response = "";

    public GetPatientDetailsAsyncTask(Context ctx, Activity app, JSONObject obj) {
        this.object = obj;
        this.ctx = ctx;
        this.app = app;
    }

    @Override
    protected String doInBackground(Void... params) {

        String content = object.toString();
        response = getDataFromServer(content, Constants.HTTP_POST, Constants.getEmergencyPatientDetails);
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        Functions.toast(result, ctx);
    }

    public String getDataFromServer(String content, String requestMethod,
                                    String serverUrl) {
        String response = "";
        DataOutputStream outputStream = null;
        try {
            URL new_url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", Constants.BG_SERVICE_CONTENT_TYPE_JSON);
            outputStream = new DataOutputStream(connection.getOutputStream());
            byte[] BytesToBeSent = content.getBytes();
            if (BytesToBeSent != null) {
                outputStream.write(BytesToBeSent, 0, BytesToBeSent.length);
            }
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            try {
                inputStreamReader = new InputStreamReader(
                        connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder responseContent = new StringBuilder();
                String temp = null;
                boolean isFirst = true;
                while ((temp = bufferedReader.readLine()) != null) {
                    if (!isFirst)
                        responseContent.append(Constants.NEW_LINE);
                    responseContent.append(temp);
                    isFirst = false;
                }
                response = responseContent.toString();
            } catch (Exception e) {
            } finally {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                }
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception ex) {
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
            }
        }
        return response;
    }
}