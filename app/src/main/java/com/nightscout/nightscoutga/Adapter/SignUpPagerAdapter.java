package com.nightscout.nightscoutga.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.nightscout.nightscoutga.UI.signup.OtherDetailsFragment;
import com.nightscout.nightscoutga.UI.signup.PersonalDetailsFragment;

public class SignUpPagerAdapter extends FragmentPagerAdapter {

    PersonalDetailsFragment pdetailsfrag;
    OtherDetailsFragment oDetailsfrag;

    public SignUpPagerAdapter(FragmentManager fm) {
        super(fm);

        pdetailsfrag = new PersonalDetailsFragment();
        oDetailsfrag = new OtherDetailsFragment();
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        if (i == 0) {
            fragment = pdetailsfrag;
        } else if (i == 1) {
            fragment = oDetailsfrag;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
