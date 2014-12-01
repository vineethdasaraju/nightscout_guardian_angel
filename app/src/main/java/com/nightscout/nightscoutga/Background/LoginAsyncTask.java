package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.nightscout.nightscoutga.UI.Fragments.MainFragmentActivity;
import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {

    private Activity appContext = null;
    ProgressDialog pd = null;
    String Username = null, Password = null, responseMessage = "";
    Boolean success = false;
    int responseCode = 0;

    public LoginAsyncTask(Activity context, String Username, String Password) {
        this.appContext = context;
        this.Username = Username;
        this.Password = Password;
    }

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(appContext, null, "...");
    }

    @Override
    protected void onPostExecute(String result) {
        pd.dismiss();
        Log.d("response", result);
        if (!Functions.isNullOrEmpty(result)) {
            JSONObject data;
            try {
                data = new JSONObject(result);
                responseCode = data.getInt("responseCode");
                if (responseCode == 200) {
                    storeData(data);
                    Intent it = new Intent(appContext, MainFragmentActivity.class);
                    appContext.startActivity(it);
                    appContext.finish();
                } else if (responseCode == 400){
                    Functions.toast("Please Enter a Valid Username/Password", appContext);
                } else {
                    Functions.toast("Error reaching the server, please try after some time.", appContext);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }// end of if condition
    }

    @Override
    protected String doInBackground(Void... params) {
        String response = "";

        try {
            JSONObject obj = new JSONObject();
            try {
                obj.put("password", Password);
                obj.put("emailId", Username);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String content = obj.toString();
            response = getDataFromServer(content, Constants.HTTP_POST,
                    Constants.apiLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
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
            connection.setRequestProperty("Content-Type",Constants.BG_SERVICE_CONTENT_TYPE_JSON);
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

    private void storeData(JSONObject data) {
        if(!Functions.isNullOrEmpty(data.toString())){
            try {
                String userDetails = data.getString("user");
                JSONObject userData;
                userData = new JSONObject(userDetails);
                String userEmailID = userData.getString("emailId");
                String userID = userData.getString("userId");
                String userName = userData.getString("userName");
                String fullName = userData.getString("fullName");
                String phoneNumber = userData.getString("phoneNum");
                String userAddress = userData.getString("address");

                Functions.savePreferences(Constants.KEY_userid, userID, appContext);
                Functions.savePreferences(Constants.KEY_username, userName, appContext);
                Functions.savePreferences(Constants.KEY_userEmail, userEmailID, appContext);
                Functions.savePreferences(Constants.KEY_userAddress, userAddress, appContext);
                Functions.savePreferences(Constants.KEY_phoneNumber, phoneNumber, appContext);
                Functions.savePreferences(Constants.KEY_userLng, "", appContext);
                Functions.savePreferences(Constants.KEY_userLat, "", appContext);
                Functions.savePreferences(Constants.KEY_fullname, fullName, appContext);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
