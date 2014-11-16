package com.nightscout.nightscoutga.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vinny on 11/16/14.
 */
public class Functions {

    public static boolean toastFlag = false;

    public static void toast(String mesg, Context ctx) {
        if (toastFlag) {
            Toast.makeText(ctx, mesg, Toast.LENGTH_SHORT).show();
            toastFlag = false;
            (new Thread() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    toastFlag = true;
                }
            }).start();
        }
    }



}
