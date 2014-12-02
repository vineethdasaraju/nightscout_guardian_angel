package com.nightscout.nightscoutga.UI.Fragments;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nightscout.nightscoutga.Background.showGlucoseAsyncTask;
import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.util.Functions;
public class DashboardFragment extends Fragment {
    public DashboardFragment(){}
    View graphsButton, patientsButton, settingsButton;
    TextView glucoseLevel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        graphsButton = rootView.findViewById(R.id.dashboard_button_graphs);
        patientsButton = rootView.findViewById(R.id.dashboard_button_patients);
        settingsButton = rootView.findViewById(R.id.dashboard_button_settings);
        glucoseLevel = (TextView) rootView.findViewById(R.id.dashboard_text_glucose_level);
        glucoseLevel.setText("100");
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
        showGlucoseAsyncTask task = new showGlucoseAsyncTask(getActivity(), glucoseLevel);
        if (Functions.isNetworkStatusAvialable(getActivity())) {
            task.execute();
        } else {
            Functions.toast(
                    "Please check your internet connectivity.",
                    getActivity());
        }
        return rootView;
    }
}