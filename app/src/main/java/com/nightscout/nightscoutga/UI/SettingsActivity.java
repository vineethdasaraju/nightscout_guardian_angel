package com.nightscout.nightscoutga.UI;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import com.nightscout.nightscoutga.R;

/**
 * Created by User on 17/11/2014.
 */
public class SettingsActivity extends PreferenceActivity implements SettingsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsFragment sf = new SettingsFragment(this);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, sf).commit();
        //addPreferencesFromResource(R.xml.activity_settings);
        //sf.
        //setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String syncConnPref = sharedPref.getString("sync_time_list", "");
        Log.e("CHGDDD", syncConnPref);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String syncConnPref = sharedPref.getString("sync_time_list", "");
        Log.e("CHG", syncConnPref);

    }
}
