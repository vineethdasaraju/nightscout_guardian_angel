package com.nightscout.nightscoutga.UI.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nightscout.nightscoutga.R;

public class MyProfileFragment extends Fragment implements View.OnClickListener{

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
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("guardianName");
                editor.putString("guardianName", name.getText().toString());
                //} else if (id == phoneNum.getId()) {
                editor.remove("guardianPhone");
                editor.putString("guardianPhone", phoneNum.getText().toString());
                //Constants.phoneNumber = phoneNum.getText().toString();
                //} else if (id == guardianEmail.getId()) {
                editor.remove("guardianEmail");
                editor.putString("guardianEmail", guardianEmail.getText().toString());
                //Constants.userEmail = guardianEmail.getText().toString();
                //} else if (id == guardianAddress.getId()) {
                editor.remove("guardianAddress");
                editor.putString("guardianAddress", guardianAddress.getText().toString());
                //Constants.userAddress = guardianAddress.getText().toString();
                //} else if (id == guardianFbPage.getId()) {
                editor.remove("guardianFbPage");
                editor.putString("guardianFbPage", guardianFbPage.getText().toString());
                //Constants.fbPage = guardianFbPage.getText().toString();
                //}
                editor.commit();
            }
        });

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        name.setText(sharedPref.getString("guardianName", null));
        phoneNum.setText(sharedPref.getString("guardianPhone", null));
        guardianEmail.setText(sharedPref.getString("guardianEmail", null));
        guardianAddress.setText(sharedPref.getString("guardianAddress", null));
        guardianFbPage.setText(sharedPref.getString("guardianFbPage", null));

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        /*SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(id == saveButton.getId()) {
            //if (id == name.getId()) {
            editor.remove("guardianName");
            editor.putString("guardianName", name.getText().toString());
            //} else if (id == phoneNum.getId()) {
            editor.remove("guardianPhone");
            editor.putString("guardianPhone", phoneNum.getText().toString());
            //Constants.phoneNumber = phoneNum.getText().toString();
            //} else if (id == guardianEmail.getId()) {
            editor.remove("guardianEmail");
            editor.putString("guardianEmail", guardianEmail.getText().toString());
            //Constants.userEmail = guardianEmail.getText().toString();
            //} else if (id == guardianAddress.getId()) {
            editor.remove("guardianAddress");
            editor.putString("guardianAddress", guardianAddress.getText().toString());
            //Constants.userAddress = guardianAddress.getText().toString();
            //} else if (id == guardianFbPage.getId()) {
            editor.remove("guardianFbPage");
            editor.putString("guardianFbPage", guardianFbPage.getText().toString());
            //Constants.fbPage = guardianFbPage.getText().toString();
            //}
            editor.commit();
        }*/
    }
}
