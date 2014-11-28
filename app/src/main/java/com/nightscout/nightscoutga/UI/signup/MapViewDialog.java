package com.nightscout.nightscoutga.UI.signup;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.nightscout.nightscoutga.Background.AddressUpdateAsyncTask;
import com.nightscout.nightscoutga.R;

public class MapViewDialog extends FragmentActivity {
	
	Context context = this;
	Activity activity = this;
	TextView tv;
	LatLng latlng;
	Button cancelButton, okButton;
	double lat, lon;
	GoogleMap googleMap = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signup_mapview_dialog);
		
		googleMap=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.dialog_map)).getMap();
		googleMap.setMyLocationEnabled(false);
		googleMap.getUiSettings().setMyLocationButtonEnabled(true);
		
		cancelButton = (Button) findViewById(R.id.mapview_cancel);
		okButton 	 = (Button) findViewById(R.id.mapview_confirm);
		
//		latlng = Constants.latlng;
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16));

		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LatLng coordinates=googleMap.getCameraPosition().target;
				double latitude = coordinates.latitude;
				double longitude = coordinates.longitude;
				AddressUpdateAsyncTask task = new AddressUpdateAsyncTask(context, latitude, longitude, activity);
				task.execute();
//				Constants.latlng = new LatLng(latitude, longitude);
			}
		});
		
		
		
		// Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        LocationListener locationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				drawMarker(location);
			}

			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}

	
        };
        locationManager.requestLocationUpdates(provider, 20000, 0, (LocationListener) locationListener);
        
        
	}
	
	
	private void drawMarker(Location location){
	    googleMap.clear();
	    LatLng currentPosition = new LatLng(location.getLatitude(),location.getLongitude());
	    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 16));
	}
}