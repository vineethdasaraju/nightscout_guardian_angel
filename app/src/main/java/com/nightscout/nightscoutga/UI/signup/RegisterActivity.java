package com.nightscout.nightscoutga.UI.signup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nightscout.nightscoutga.R;

public class RegisterActivity extends FragmentX {

    static View root = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            root = inflater.inflate(R.layout.activity_register_1, null, false);

            Bundle extras = getActivity().getIntent().getExtras();
            String email_ID = extras.getString("Email ID");

            Button backButton, nextButton;
            EditText signupFieldName, signupFieldEmail, signupFieldPassword, signupFieldPhoneNo, signupFieldAddress;

            signupFieldName = (EditText) root.findViewById(R.id.signup_name);
            signupFieldEmail = (EditText) root.findViewById(R.id.signup_email);
            signupFieldPhoneNo = (EditText) root.findViewById(R.id.signup_phone);
            signupFieldPassword = (EditText) root.findViewById(R.id.signup_pwd);
            backButton = (Button) root.findViewById(R.id.ns_s1_back);
            nextButton = (Button) root.findViewById(R.id.ns_s1_next);

            signupFieldEmail.setText(email_ID);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.alert_exit)
                            .setPositiveButton(R.string.option_yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .setNegativeButton(R.string.option_no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
        return root;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getValue(int id) {
        return null;
    }

    @Override
    public void setValue(int id, String val) {

    }
}
