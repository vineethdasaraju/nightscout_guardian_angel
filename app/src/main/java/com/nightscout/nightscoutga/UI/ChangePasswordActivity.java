package com.nightscout.nightscoutga.UI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nightscout.nightscoutga.Background.changePasswordAsyncTask;
import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswordActivity extends Activity {

    EditText old_pwd, new_pwd, confirm_pwd;
    Button okButton, cancelButton;
    String oldPass, newPass, confirmPass;
    ImageView confirmChecker;
    Context context = this;
    Boolean confirmCheckerActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_change_password);

        okButton = (Button) findViewById(R.id.change_pwd_button_ok);
        cancelButton = (Button) findViewById(R.id.change_pwd_button_cancel);

        old_pwd = (EditText) findViewById(R.id.change_pwd_old_pwd);
        new_pwd = (EditText) findViewById(R.id.change_pwd_new_pwd);
        confirm_pwd = (EditText) findViewById(R.id.change_pwd_confirm_pwd);

        confirmChecker = (ImageView) findViewById(R.id.confirm_pwd_status_img);

        confirm_pwd.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newPwd = new_pwd.getText().toString();
                String confirmPwd = confirm_pwd.getText().toString();
                confirmCheckerActive = true;
                if (confirmPwd.equals(newPwd)) {
                    confirmChecker.setVisibility(View.VISIBLE);
                    confirmChecker
                            .setBackgroundResource(R.drawable.domain_available);
                } else {
                    confirmChecker.setVisibility(View.VISIBLE);
                    confirmChecker
                            .setBackgroundResource(R.drawable.domain_not_available);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
        });

        new_pwd.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (confirmCheckerActive) {
                    String newPwd = new_pwd.getText().toString();
                    String confirmPwd = confirm_pwd.getText().toString();
                    if (confirmPwd.equals(newPwd)) {
                        confirmChecker.setBackgroundResource(R.drawable.domain_available);
                    } else {
                        confirmChecker.setBackgroundResource(R.drawable.domain_not_available);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = old_pwd.getText().toString();
                newPass = new_pwd.getText().toString();
                confirmPass = confirm_pwd.getText().toString();
                Boolean confirm = newPass.equals(confirmPass);

                if (newPass.length() > 5) {
                    if (confirm) {
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("currentPassword", oldPass);
                            obj.put("newPassword", newPass);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        changePasswordAsyncTask task = new changePasswordAsyncTask(
                                context, obj, ChangePasswordActivity.this);
                        if (Functions.isNetworkStatusAvialable(context)) {
                            task.execute();
                        } else {
                            Functions.toast(
                                    "Please check your internet connectivity.",
                                    context);
                        }

                    } else {
                        Functions.toast("Both the passwords don't match.", context);
                    }
                } else {
                    Functions.toast("Min 6 characters are required.", context);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
