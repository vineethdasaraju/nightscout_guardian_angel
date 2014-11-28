package com.nightscout.nightscoutga.UI.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.customviews.FragmentX;

public class OtherDetailsFragment extends FragmentX {


    View root;
    EditText street, locality, city, state, pincode;
    Button nextButton, backButton;
    String streetNumberValue, localityValue, cityValue, countryValue, pincodeValue, address;

    @Override
    public boolean isValid() {
        return false;    }

    @Override
    public String getValue(int id) {
        return null;
    }

    @Override
    public void setValue(int id, String val) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(root == null) {
            root = inflater.inflate(R.layout.ns_signup_2, null, false);
        }

        street = (EditText) root.findViewById(R.id.ns_s2_signup_street);
        locality = (EditText) root.findViewById(R.id.ns_s2_signup_locality);
        city = (EditText) root.findViewById(R.id.ns_s2_signup_city);
        state = (EditText) root.findViewById(R.id.ns_s2_signup_country);
        pincode = (EditText) root.findViewById(R.id.ns_s2_signup_pin);
        backButton = (Button) root.findViewById(R.id.ns_s2_back);
        nextButton = (Button) root.findViewById(R.id.ns_s2_next);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignUpActivity) getActivity()).slideTo(0);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streetNumberValue = street.getText().toString();
                localityValue = locality.getText().toString();
                cityValue = city.getText().toString();
                countryValue = state.getText().toString();
                pincodeValue = pincode.getText().toString();
                address = streetNumberValue + ", " + localityValue + ", " + cityValue + ", " + countryValue + ", " + pincodeValue;
                ((SignUpActivity) getActivity()).address = address;

                ((SignUpActivity) getActivity()).attemptRegistration();
            }
        });

        return root;
    }
}