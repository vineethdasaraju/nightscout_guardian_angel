package com.nightscout.nightscoutga.UI.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.nightscout.nightscoutga.Background.LoginAsyncTask;
import com.nightscout.nightscoutga.Background.checkEmailIDAsyncTask;
import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.UI.signup.SignUpActivity;
import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

/**
 * A login screen that offers login via email/password and via Google+ sign in.
 * <p/>
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */
public class LoginActivity extends PlusBaseActivity {

    private EditText mEmailView, mPasswordView;
    private View mEmailLoginFormView;
    private View mProgressView;
    private SignInButton mPlusSignInButton;
    private View mLoginFormView;
    Button mEmailSignInButton;
    private Context ctx = this;
    private String userEmail, userPassword;
    int i = 0;

    TextView mDisplay, userPrompt;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        checkForPreviousLogin();

        mPlusSignInButton = (SignInButton) findViewById(R.id.plus_sign_in_button);
        if (supportsGooglePlayServices()) {
            mPlusSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
        } else {
            mPlusSignInButton.setVisibility(View.GONE);
            return;
        }

        userPrompt = (TextView) findViewById(R.id.login_user_prompt);
        mEmailView = (EditText) findViewById(R.id.email);
        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String mailID = mEmailView.getText().toString();
                updateUIElements(mailID);
            }
        });

        mProgressView = findViewById(R.id.login_progress);
        mPasswordView = (EditText) findViewById(R.id.password);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String functionality = mEmailSignInButton.getText().toString();
                if (functionality.equals(getString(R.string.login_activity_title))) {
                    userEmail = mEmailView.getText().toString();
                    userPassword = mPasswordView.getText().toString();
                    attemptLogin(userEmail, userPassword);
                } else {
                    userEmail = mEmailView.getText().toString();
                    startRegistration(userEmail);
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mEmailLoginFormView = findViewById(R.id.email_login_form);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void onPlusClientSignIn() {
//        Update UI based on the details received
//        attemptLogin();
    }

    @Override
    protected void onPlusClientBlockingUI(boolean show) {
        showProgress(show);
    }

    @Override
    protected void updateConnectButtonState() {
        //TODO: Update this logic to also handle the user logged in by email.
        boolean connected = getPlusClient().isConnected();

        mPlusSignInButton.setVisibility(connected ? View.GONE : View.VISIBLE);
        mEmailLoginFormView.setVisibility(connected ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onPlusClientRevokeAccess() {
        // TODO: Access to the user's G+ account has been revoked.  Per the developer terms, delete
        // any stored user data here.
    }

    @Override
    protected void onPlusClientSignOut() {
    }

    private boolean supportsGooglePlayServices() {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) ==
                ConnectionResult.SUCCESS;
    }

    private void updateUIElements(String userEmail){
        if (Functions.isNullOrEmpty(userEmail)) {
            mEmailView.setError(getString(R.string.error_field_required));
            mPasswordView.setVisibility(View.GONE);
            mEmailSignInButton.setVisibility(View.GONE);
        } else if (Functions.isEmailValid(userEmail)) {
            mEmailView.setError(null);
            checkDBForAccount(userEmail);
        } else {
            mEmailView.setError(getString(R.string.error_invalid_email));
            mPasswordView.setVisibility(View.GONE);
            mEmailSignInButton.setVisibility(View.GONE);
        }
    }

    public void attemptLogin(String mailID, String password) {
        LoginAsyncTask task = new LoginAsyncTask(this, mailID, password);
        task.execute();
    }

    private void checkDBForAccount(String emailID) {
        checkEmailIDAsyncTask task = new checkEmailIDAsyncTask(ctx, LoginActivity.this, emailID, mEmailSignInButton, mPasswordView, userPrompt, mPlusSignInButton, mEmailView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            task.execute();
        }
    }

    private void startRegistration(String emailID) {
        Intent it = new Intent(ctx, SignUpActivity.class);
        it.putExtra("Email ID", emailID);
        startActivity(it);
        finish();
    }

    private void checkForPreviousLogin() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        Constants.userid = sharedPreferences.getString(Constants.KEY_userid, "");
        if(!Functions.isNullOrEmpty(Constants.userid)){
            Intent it = new Intent(this, SplashScreen.class);
            startActivity(it);
            finish();
        }
    }
}