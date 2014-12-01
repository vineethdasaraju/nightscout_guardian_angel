package com.nightscout.nightscoutga.UI.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.UI.Fragments.MainFragmentActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SplashScreen extends Activity {

    private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        Runnable task = new Runnable() {
            public void run() {
                Intent it = new Intent(SplashScreen.this, MainFragmentActivity.class);
                startActivity(it);
                finish();
            }
        };
        worker.schedule(task, 3, TimeUnit.SECONDS);
    }
}

