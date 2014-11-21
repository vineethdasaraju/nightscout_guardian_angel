package com.nightscout.nightscoutga.UI.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nightscout.nightscoutga.R;

import org.json.JSONArray;

/**
 * Created by vineethd on 11/19/14.
 */
public class MainFragment extends SlidingFragmentActivity {

    private SharedPreferences pref = null;
    SharedPreferences.Editor prefsEditor;
    CharSequence[] items;
    JSONArray pageList;
    int size = 0;
    String keywords[] = null;
    int backCount = 0;
    public static int RESULT_NOT_UPDATED_ON_SERVER = 100002;
    boolean backFlag = true;
    int count;
    boolean flag = false;
    Fragment currentFragment = null;

    boolean toggle = true;
    DashboardFragment dashboardFragment = null;
    SideMenuFragment sideMenu = null;
    PatientsFragment patientsFragment = null;
    SettingsFragment settingsFragment = null;
    GraphsFragment graphsFragment = null;

    public static int chDashboard = 1;
    public static int chPatients = 2;
    public static int chGraphs = 3;
    public static int chSettings = 4;

    int deepLinkCount;
    Handler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String targetScreen = extras.getString("ScreenName");
            if(targetScreen!=null){
                if(targetScreen.equals("Dashboard")){
                    deepLinkCount = chDashboard;
                    switchToContent(deepLinkCount, false);
                }
                else if(targetScreen.equals("Patients")){
                    deepLinkCount = chPatients;
                    switchToContent(deepLinkCount, false);
                }
                else if(targetScreen.equals("Graphs")){
                    deepLinkCount = chGraphs;
                    switchToContent(deepLinkCount, false);
                }
                else if(targetScreen.equals("Settings")){
                    deepLinkCount = chSettings;
                    switchToContent(deepLinkCount, false);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void switchToContent(int choice, boolean createNew) {
        Fragment frag = null;
        if (choice < 0) {
            showContent();
            return;
        }
        switch (choice) {
            case 1:
                frag = dashboardFragment;
                break;
            case 2:
                if (patientsFragment == null) {
                    patientsFragment = new PatientsFragment();
                }
                flag = true;
                frag = patientsFragment;
                break;

            case 3:
                if (graphsFragment == null) {
                    graphsFragment = new GraphsFragment();
                }
                flag = true;
                frag = graphsFragment;
                break;

            case 4:
                if (settingsFragment == null) {
                    settingsFragment = new SettingsFragment();
                }
                flag = true;
                frag = settingsFragment;
                break;

            default:
                break;
        }
        if (frag != null) {
            try {
                currentFragment = frag;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, frag)
                        .commitAllowingStateLoss();
                showContent();

                if(flag == false){
                    //if(currentFragment==fragmentOne){
                    Fragment f = new SideMenuFragment();
                    Bundle bdl = new Bundle();
                    bdl.putInt("FRAGMENT", choice);
                    f.setArguments(bdl);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.left_menu, f).commitAllowingStateLoss();
                    count++;
                    //}
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
