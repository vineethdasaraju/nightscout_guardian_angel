package com.nightscout.nightscoutga.Background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class updateProfileAsyncTask extends AsyncTask<Void, Void, Integer> {
    JSONObject object = null;
    Context ctx;
    int responseCode;
    ProgressDialog pd = null;

    public updateProfileAsyncTask(Context ctx, JSONObject obj) {
        this.ctx = ctx;
        this.object = obj;
    }
    @Override
    protected Integer doInBackground(Void... params) {
        String response = "";
        String content = object.toString();
        DataOutputStream outputStream = null;
        URL new_url;
        try {
            new_url = new URL(Constants.updateProfile);
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
        pd = ProgressDialog.show(ctx, "Please Wait", "Updating your Profile...");
    }

    @Override
    protected void onPostExecute(Integer responseCode) {
        pd.dismiss();
        if(responseCode==200){
            Functions.toast("Your details have been updated", ctx);
        } else {
            Functions.toast("Error updating your details. Please try later.", ctx);
        }
    }
}
