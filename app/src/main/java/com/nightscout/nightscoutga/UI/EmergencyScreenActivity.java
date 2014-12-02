package com.nightscout.nightscoutga.UI;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nightscout.nightscoutga.Background.AcknowledgeAsyncTask;
import com.nightscout.nightscoutga.Background.GetPatientDetailsAsyncTask;
import com.nightscout.nightscoutga.R;
import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmergencyScreenActivity extends Activity {

    TextView patientInfo;
    GoogleMap googleMap = null;
    Button acknowledgeButton, viewPatientDetails;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    Context ctx = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_emergency_screen);

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.emergency_screen_map))
                .getMap();
        if (googleMap!=null){
            Marker hamburg = googleMap.addMarker(new MarkerOptions().position(HAMBURG)
                    .title("Hamburg"));
            Marker kiel = googleMap.addMarker(new MarkerOptions()
                    .position(KIEL)
                    .title("Kiel")
                    .snippet("Kiel is cool")
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_appicon)));
        }

        //Move the camera instantly to hamburg with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        patientInfo = (TextView) findViewById(R.id.emergency_screen_info);
        acknowledgeButton = (Button) findViewById(R.id.emergency_screen_acknowledge);
        viewPatientDetails = (Button) findViewById(R.id.emergency_screen_patient_details);

        acknowledgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcknowledgeAsyncTask task = new AcknowledgeAsyncTask(ctx, EmergencyScreenActivity.this, viewPatientDetails, acknowledgeButton);
                task.execute();
            }
        });

        viewPatientDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                JSONObject object = new JSONObject();
                                try {
                                    LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                    double longitude = location.getLongitude();
                                    double latitude = location.getLatitude();

                                    Calendar c = Calendar.getInstance();
                                    System.out.println("Current time => " + c.getTime());

                                    SimpleDateFormat df = new SimpleDateFormat("dd//MM/yyyy");
                                    String formattedDate = df.format(c.getTime());

                                    SimpleDateFormat df2 = new SimpleDateFormat("hh:mm:ss");
                                    String formattedTime = df2.format(c.getTime());

                                    object.put("userId", Constants.userid);
                                    object.put("longitude", longitude);
                                    object.put("latitude", latitude);
                                    object.put("date", formattedDate);
                                    object.put("time", formattedTime);
                                    GetPatientDetailsAsyncTask task = new GetPatientDetailsAsyncTask(ctx, EmergencyScreenActivity.this, object);
                                    task.execute();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void showAlertDialog(){
        Functions.toast(Constants.emergencyPatientName, ctx);
    }
}