package com.nightscout.nightscoutga.customviews;


import android.app.Fragment;

/**
 * Created by vinny on 11/18/14.
 */
abstract public class FragmentX extends Fragment {
    public abstract boolean isValid();
    public abstract String getValue(int id);
    public abstract void setValue(int id,String val);
}

