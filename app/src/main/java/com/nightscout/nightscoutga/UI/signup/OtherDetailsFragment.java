package com.nightscout.nightscoutga.UI.signup;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.customviews.FragmentX;
import com.nightscout.nightscoutga.util.Functions;

public class OtherDetailsFragment extends FragmentX {


    View root;
    static EditText street, country, city, state, pincode;
    Button nextButton, backButton;
    ImageButton mapViewButton;
    String streetNumberValue, stateValue, cityValue, countryValue, pincodeValue, address;

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
        country = (EditText) root.findViewById(R.id.ns_s2_signup_country);
        city = (EditText) root.findViewById(R.id.ns_s2_signup_city);
        state = (EditText) root.findViewById(R.id.ns_s2_signup_state);
        pincode = (EditText) root.findViewById(R.id.ns_s2_signup_pin);
        backButton = (Button) root.findViewById(R.id.ns_s2_back);
        nextButton = (Button) root.findViewById(R.id.ns_s2_next);
        mapViewButton = (ImageButton) root.findViewById(R.id.ns_s2_signup_location_crosshair);

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
                countryValue = country.getText().toString();
                cityValue = city.getText().toString();
                stateValue = state.getText().toString();
                pincodeValue = pincode.getText().toString();
                address = streetNumberValue + ", " + cityValue + ", " + stateValue + ", " + countryValue + ", " + pincodeValue;
                ((SignUpActivity) getActivity()).address = address;

                ((SignUpActivity) getActivity()).attemptRegistration();
            }
        });

        mapViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), MapViewDialog.class);
                getActivity().startActivity(it);
            }
        });

        return root;
    }

    public static boolean updateBasedOnMostRecentLocation(Address address) {
        if (address!=null) {
            String lastKnownCountry = address.getCountryName();
            String lastKnownState = address.getAdminArea();
            String lastKnownCity = address.getLocality();
            String lastKnownPin = address.getPostalCode();

            if (!Functions.isNullOrEmpty(lastKnownCountry)) {
                country.setText(lastKnownCountry);
            }

            if (!Functions.isNullOrEmpty(lastKnownState)) {
                state.setText(lastKnownState);
            }

            if (!Functions.isNullOrEmpty(lastKnownCity)) {
                city.setText(lastKnownCity);
            }

            if (!Functions.isNullOrEmpty(lastKnownPin)) {
                pincode.setText(lastKnownPin);
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean updateCurrentLocation(Address address) {
        if (address!=null) {
            // Address[addressLines=[0:"Haralur Rd, PWD Quarters, Sector 1, Harlur",1:"Bangalore, Karnataka 560103",2:"India"],feature=Haralur Rd,admin=Karnataka,sub-admin=Bangalore Urban,locality=Bangalore,thoroughfare=Haralur Rd,postalCode=560103,countryCode=IN,countryName=India,hasLatitude=true,latitude=12.9113477,hasLongitude=true,longitude=77.6642781,phone=null,url=null,extras=null]
            // Address[addressLines=[0:"JLT Exit",1:"Dubai",2:"United Arab Emirates"],feature=JLT Exit,admin=Dubai,sub-admin=null,locality=Dubai,thoroughfare=JLT Exit,postalCode=null,countryCode=AE,countryName=United Arab Emirates,hasLatitude=true,latitude=25.079518,hasLongitude=true,longitude=55.1483235,phone=null,url=null,extras=null]

            String CurrentCountry = address.getCountryName();
            String CurrentState = address.getAdminArea();
            String CurrentCity = address.getLocality();
            String CurrentStreet = address.getThoroughfare();
            String CurrentPin = address.getPostalCode();

            if (!Functions.isNullOrEmpty(CurrentCountry)) {
                country.setText(CurrentCountry);
            }else {
                country.setText("");
            }

            if (!Functions.isNullOrEmpty(CurrentState)) {
                state.setText(CurrentState);
            }else {
                state.setText("");
            }

            if (!Functions.isNullOrEmpty(CurrentCity)) {
                city.setText(CurrentCity);
            } else {
                city.setText("");
            }

            if (!Functions.isNullOrEmpty(CurrentStreet)) {
                street.setText(CurrentStreet);
            }else {
                street.setText("");
            }

            if(!Functions.isNullOrEmpty(CurrentPin)){
                pincode.setText(CurrentPin);
            }else {
                pincode.setText("");
            }
            return true;
        } else {
            return false;
        }
    }
}