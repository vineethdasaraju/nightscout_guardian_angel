package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

public class AddressUpdateAsyncTask extends AsyncTask<Void, Void, Address>{
	
	Context appContext;
	Address currentAddress;
	double latitude = -1, longitude = -1;
	ProgressDialog pd 	= null;
	Activity activity = null;
	public AddressUpdateAsyncTask(Context context, double latitude, double longitude, Activity activity){
		this.appContext = context;
		this.latitude = latitude;
		this.longitude = longitude;
		this.activity = activity;
	}

	@Override
	protected Address doInBackground(Void... params) {
		
			List<Address> locations = null;
			Address address = null;
			
			if (latitude != -1 && longitude != -1){
				
				Geocoder geocoder = new Geocoder(appContext);
				Boolean geocoderIsPresent = Geocoder.isPresent();
				
				if (geocoderIsPresent && geocoder != null) {
					try {
						locations = geocoder.getFromLocation(latitude,
								longitude, 1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					//	Geocoder is not available or supported
				}
				
				if(locations!=null){
					currentAddress = locations.get(0);
				}
			} else {
				// Last Known Location not available
			}
		return currentAddress;
	}

	@Override
	protected void onPostExecute(Address result) {
		super.onPostExecute(result);
		pd.dismiss();
        //TODO: Update UI based on response
		activity.finish();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pd= ProgressDialog.show(appContext, null, "Updating Your Address");
	}
}