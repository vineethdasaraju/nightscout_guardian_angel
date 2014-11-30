package com.nightscout.nightscoutga.UI.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.nightscout.nightscoutga.R;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_settings);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("sync_time_list")) {
            Preference connectionPref = new Preference(getActivity());
            connectionPref.setKey(key);
            //findPreference(key);
            // Set summary to be the user-description for the selected value
            connectionPref.setSummary(sharedPreferences.getString(key, "") + " minutes");
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String syncConnPref = sharedPref.getString("sync_time_list", "");
        }
    }
}