package com.nightscout.nightscoutga.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightscout.nightscoutga.R;


public class PatientsFragment extends Fragment {

    public PatientsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patients, container, false);
        return rootView;
    }
}
