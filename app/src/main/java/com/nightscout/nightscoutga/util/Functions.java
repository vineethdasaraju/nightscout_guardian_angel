package com.nightscout.nightscoutga.util;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vinny on 11/16/14.
 */
public class Functions {


    public static boolean isEmailValid(String email) {

        boolean isValid = false;

        if(Constants.isDotCom){

            if(email.contains("@")){
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                CharSequence inputStr = email;

                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(inputStr);
                if (matcher.matches()) {
                    isValid = true;
                }
                return isValid;
            }else{
                return false;
            }

        } else {
            if(email.contains(".co")||email.contains(".ed")){
                Constants.isDotCom = true;
            }
            return false;
        }
    }


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
                        e.printStackTrace();
                    }
                    toastFlag = true;
                }
            }).start();
        }
    }



}
