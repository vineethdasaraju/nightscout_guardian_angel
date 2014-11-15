package com.nightscout.nightscoutga;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class LandingActivity extends Activity {
    Button landingButton;
    RelativeLayout uNameCheckStatus;
    EditText landingEmail;
    boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        landingButton = (Button) findViewById(R.id.landing_button);
        uNameCheckStatus = (RelativeLayout) findViewById(R.id.domain_check_pd);
        landingEmail = (EditText) findViewById(R.id.landing_email);

        landingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uNameCheckStatus.setVisibility(View.VISIBLE);
            }
        });

        landingEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String enteredEmail = landingEmail.getText().toString();
                if(!firstRun) {
                    firstRun = false;
                    if (enteredEmail.equals("vineethd123@gmail.com")) {
                        landingButton.setText(R.string.button_login);
                        landingButton.setVisibility(View.VISIBLE);
                    } else {
                        landingButton.setText(R.string.button_register);
                        landingButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
