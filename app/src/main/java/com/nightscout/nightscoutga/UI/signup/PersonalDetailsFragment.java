package com.nightscout.nightscoutga.UI.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.customviews.FragmentX;

public class PersonalDetailsFragment extends FragmentX {

    static View root = null;
    Button backButton, nextButton;
    EditText signupFieldName, signupFieldEmail, signupFieldPassword, signupFieldPhoneNo,signupFieldUName;
    String name,uName, password, emailID, phoneNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            if(root==null){
                root = inflater.inflate(R.layout.ns_signup_1, null, false);
            }

            Bundle extras = getActivity().getIntent().getExtras();
            String email_ID = extras.getString("Email ID");

            signupFieldName = (EditText) root.findViewById(R.id.signup_name);
            signupFieldUName = (EditText) root.findViewById(R.id.signup_uName);
            signupFieldEmail = (EditText) root.findViewById(R.id.signup_email);
            signupFieldPhoneNo = (EditText) root.findViewById(R.id.signup_phone);
            signupFieldPassword = (EditText) root.findViewById(R.id.signup_pwd);
            nextButton = (Button) root.findViewById(R.id.ns_s1_next);

            signupFieldEmail.setText(email_ID);

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = signupFieldName.getText().toString();
                    uName = signupFieldUName.getText().toString();
                    password = signupFieldPassword.getText().toString();
                    emailID = signupFieldEmail.getText().toString();
                    phoneNum = signupFieldPhoneNo.getText().toString();

                    ((SignUpActivity) getActivity()).name = name;
                    ((SignUpActivity) getActivity()).uName = uName;
                    ((SignUpActivity) getActivity()).password = password;
                    ((SignUpActivity) getActivity()).emailID = emailID;
                    ((SignUpActivity) getActivity()).phoneNum = phoneNum;

                    ((SignUpActivity) getActivity()).slideTo(1);
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
