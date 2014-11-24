package com.nightscout.nightscoutga.UI.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightscout.nightscoutga.R;

public class DashboardFragment extends Fragment {

    public DashboardFragment(){}

    View graphsButton, patientsButton, settingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        graphsButton = rootView.findViewById(R.id.dashboard_button_graphs);
        patientsButton = rootView.findViewById(R.id.dashboard_button_patients);
        settingsButton = rootView.findViewById(R.id.dashboard_button_settings);

        graphsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragmentActivity) getActivity()).displayView(2);
            }
        });

        patientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragmentActivity) getActivity()).displayView(1);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFragmentActivity) getActivity()).displayView(3);
            }
        });

        return rootView;
    }
}