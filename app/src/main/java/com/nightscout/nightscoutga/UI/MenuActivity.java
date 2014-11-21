package com.nightscout.nightscoutga.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.nightscout.nightscoutga.R;

/**
 * Created by User on 17/11/2014.
 */
public class MenuActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        //PreferenceManager.setDefaultValues(this, R.xml.advanced_preferences, false);
        ImageButton graph = (ImageButton)findViewById(R.id.imageButton);
        graph.setOnClickListener(this);
        ImageButton setting = (ImageButton)findViewById(R.id.imageButton2);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageButton){
            Intent i = new Intent();
            i.setClass(this, Graph.class);
            startActivity(i);
        }
        if(v.getId() == R.id.imageButton2){
            Intent i = new Intent();
            i.setClass(this, SettingsActivity.class);
            startActivity(i);
        }
    }
}
