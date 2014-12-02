package com.nightscout.nightscoutga.UI.signup;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.nightscout.nightscoutga.Adapter.SignUpPagerAdapter;
import com.nightscout.nightscoutga.Background.createUserAsyncTask;
import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.customviews.CustomViewPager;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends FragmentActivity {

    CustomViewPager pager = null;
    SignUpPagerAdapter pagerAdapter = null;
    int progress = 0;
    String address = null;
    String name = null, uName = null, password = null, emailID = null, phoneNum = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        pager = (CustomViewPager) findViewById(R.id.signup_viewpager);
        android.app.FragmentManager fm = getFragmentManager();
        pagerAdapter = new SignUpPagerAdapter(fm);
        pager.setAdapter(pagerAdapter);
    }


    public void slideTo(int i) {
        if (progress < i) {
            progress = i;
        }
        if (i < 0) {
            showDialog();
        } else
            pager.setCurrentItem(i, true);
    }


    @Override
    public void onBackPressed() {
        int currentPage = pager.getCurrentItem();

        switch (currentPage) {
            case 0:
                slideTo(-1);
                break;
            case 1:
                slideTo(0);
                break;
        }
    }

    public void attemptRegistration(){

//        String userName,String password,String emailId,long phoneNum,String address,String fullName

        JSONObject obj = new JSONObject();
        try {
            obj.put("userName", uName);
            obj.put("password", password);
            obj.put("emailId", emailID);
            obj.put("phoneNum", phoneNum);
            obj.put("address", address);
            obj.put("fullName", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createUserAsyncTask task = new createUserAsyncTask(obj, SignUpActivity.this);
        task.execute();
    }

    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_exit)
                .setPositiveButton(R.string.option_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.option_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.show();

//        final Dialog dialog = new Dialog(this);
//        //tell the Dialog to use the dialog.xml as it's layout description
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.exit_dialog);
//        Button backButton = (Button) dialog.findViewById(R.id.ed_gotToGo);
//        Button okButton = (Button) dialog.findViewById(R.id.ed_ok);
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                finish();
//                Intent intent = new Intent(SignupActivity.this, PreSignupActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        okButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
    }
}
