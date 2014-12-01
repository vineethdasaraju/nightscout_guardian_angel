package com.nightscout.nightscoutga.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

public class MyProfileFragment extends Fragment {

    View rootView = null;
    EditText name, phoneNum, guardianEmail, guardianAddress, guardianFbPage;
    Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);
        }

        name = (EditText) rootView.findViewById(R.id.guardianName);
        phoneNum = (EditText) rootView.findViewById(R.id.guardianPhone);
        guardianEmail = (EditText) rootView.findViewById(R.id.guardianEmail);
        guardianAddress = (EditText) rootView.findViewById(R.id.guardianAddress);
        guardianFbPage = (EditText) rootView.findViewById(R.id.guardianFbPage);
        saveButton = (Button) rootView.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                storeData();
            }
        });

        name.setText(Constants.fullname);
        phoneNum.setText(Constants.phoneNumber);
        guardianEmail.setText(Constants.userEmail);
        guardianAddress.setText(Constants.userAddress);
        guardianFbPage.setText(Constants.fbPage);

        // Inflate the layout for this fragment
        return rootView;
    }

    private void storeData() {
        String userEmailID = guardianEmail.getText().toString();
        String fullName = name.getText().toString();
        String phoneNumber = phoneNum.getText().toString();
        String userAddress = guardianAddress.getText().toString();
        String fbPage = guardianFbPage.getText().toString();

        Functions.savePreferences(Constants.KEY_userEmail, userEmailID, getActivity());
        Functions.savePreferences(Constants.KEY_userAddress, userAddress, getActivity());
        Functions.savePreferences(Constants.KEY_phoneNumber, phoneNumber, getActivity());
        Functions.savePreferences(Constants.KEY_fullname, fullName, getActivity());
        Functions.savePreferences(Constants.KEY_fbPage, fbPage, getActivity());
    }
}
