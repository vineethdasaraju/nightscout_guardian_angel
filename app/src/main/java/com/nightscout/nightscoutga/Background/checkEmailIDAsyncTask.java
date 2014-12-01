package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.util.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class checkEmailIDAsyncTask extends AsyncTask <Void, Void, Integer>{

    Context context;
    boolean success = false;
    Activity app = null;
    URL checkEmailIDapi = null;
    String emailID;
    Button mEmailSignInButton;
    View mPasswordView, mPlusSignInButton;
    TextView userPrompt, mEmailView;
    ProgressDialog pd;

    public checkEmailIDAsyncTask(Context context, Activity app, String emailID, Button mEmailSignInButton, View mPasswordView, TextView userPrompt, View mPlusSignInButton, TextView mEmailView){
        this.context = context;
        this.app = app;
        this.emailID = emailID;
        this.mEmailSignInButton = mEmailSignInButton;
        this.mPasswordView = mPasswordView;
        this.userPrompt = userPrompt;
        this.mPlusSignInButton = mPlusSignInButton;
        this.mEmailView = mEmailView;
    }

    @Override
    protected void onPreExecute() {
        pd= ProgressDialog.show(app, null, "Checking our database for your emailID.");
        pd.setCancelable(true);
    }

    @Override
    protected Integer doInBackground(Void... params) {
        try {
            String registerAPI = Constants.apiPrefix + "/ga/"+ emailID +"/register1";
            checkEmailIDapi = new URL(registerAPI);

            HttpURLConnection con = (HttpURLConnection) checkEmailIDapi.openConnection();
            // optional default is GET
            con.setRequestMethod(Constants.HTTP_GET);
            //add request header

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            if(response.toString().equals("{\"responseCode\":400,\"responseMessage\":\"No person has invited you. Your Registration denied!\"}")){
                return 0;
            } else if(response.toString().equals("{\"responseCode\":400,\"responseMessage\":\"You have already registered. Please login!\"}")){
                return 1;
            } else if(response.toString().equals("{\"responseCode\":200,\"responseMessage\":\"Please enter further details\"}")){
                return 2;
             }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer responseInt) {
        pd.dismiss();
        switch (responseInt) {
            case 0:
                mPlusSignInButton.setVisibility(View.GONE);
                userPrompt.setText("Oops! You still don't have an invite to join the community. Please try again after you get one.");
                userPrompt.setVisibility(View.VISIBLE);
                mPasswordView.setVisibility(View.GONE);
                mEmailSignInButton.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager)app.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEmailView.getWindowToken(), 0);
                break;
            case 1:
                mPlusSignInButton.setVisibility(View.GONE);
                userPrompt.setVisibility(View.GONE);
                mPasswordView.setVisibility(View.VISIBLE);
                mEmailSignInButton.setText(app.getString(R.string.login_activity_title));
                mEmailSignInButton.setVisibility(View.VISIBLE);
                mPasswordView.requestFocus();
                break;
            case 2:
                mPasswordView.setVisibility(View.GONE);
                userPrompt.setVisibility(View.GONE);
                mEmailSignInButton.setText(app.getString(R.string.landing_page_signup));
                mEmailSignInButton.setVisibility(View.VISIBLE);
                mEmailSignInButton.requestFocus();
                break;
        }
    }
}