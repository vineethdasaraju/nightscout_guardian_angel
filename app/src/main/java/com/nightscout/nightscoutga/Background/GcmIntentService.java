package com.nightscout.nightscoutga.Background;

import android.R;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nightscout.nightscoutga.UI.Fragments.MainFragmentActivity;
import com.nightscout.nightscoutga.util.Constants;

public class GcmIntentService  extends IntentService  {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
//        Object title = extras.get("data");
//        String message = extras.getString("message");
//
//        for (String key: extras.keySet())
//        {
//            Log.d("myApplication", " key: " + key);
//            Log.d("myApplication", " value: " + extras.get(key));
//        }

//        Log.d("myApplication", title.toString());

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle

            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                sendNotification(""+extras.get(Constants.MSG_KEY));
            }
        }

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wl.acquire();

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }


    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainFragmentActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.btn_star)
                        .setContentTitle("Alert!")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                        .setContentText(msg)
                        .setTicker("Your friend needs you immediately.")
                        .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                        .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND);

        mBuilder.setContentIntent(contentIntent);
       // mBuilder.flag |= Notification.FLAG_INSISTENT;
        Notification note = mBuilder.build();
        note.flags |= Notification.FLAG_INSISTENT;
        mNotificationManager.notify(NOTIFICATION_ID, note);
    }
}
