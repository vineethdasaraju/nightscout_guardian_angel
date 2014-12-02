package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.nightscout.nightscoutga.util.Constants;

import java.io.IOException;
import java.util.List;

public class ReverseGeoCoderAsyncTask extends AsyncTask<Void, Void, Address> {

	Context appContext;
	Address lastKnownAddress;
	Location location;
    Activity app;
    ProgressDialog pd;

	public ReverseGeoCoderAsyncTask(Context context, Location location, Activity app) {
		this.appContext = context;
		this.location = location;
        this.app = app;
	}

	@Override
	protected Address doInBackground(Void... params) {

		List<Address> locations = null;
		Address address = null;

		if (location != null) {

			double Latitude = location.getLatitude();
			double Longitude = location.getLongitude();

			Geocoder geocoder = new Geocoder(appContext);
			Boolean geocoderIsPresent = Geocoder.isPresent();

			if (geocoderIsPresent && geocoder != null) {
				try {
					locations = geocoder
							.getFromLocation(Latitude, Longitude, 1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// Geocoder is not available or supported
			}

			if (locations != null) {
				lastKnownAddress = locations.get(0);
				Constants.lastKnownAddress = lastKnownAddress;
				Constants.latlng = new LatLng(lastKnownAddress.getLatitude(),
						lastKnownAddress.getLongitude());
			}

			// Address[addressLines=[0:"54, 31A Main Rd, PWD Quarters, Sector 1, HSR Layout",1:"Bangalore, Karnataka 560102",2:"India"],feature=54,admin=Karnataka,sub-admin=Bangalore
			// Urban,locality=Bangalore,thoroughfare=31A Main
			// Rd,postalCode=null,countryCode=IN,countryName=India,hasLatitude=true,latitude=12.911012,hasLongitude=true,longitude=77.653807,phone=null,url=null,extras=null]
			// Address[addressLines=[0:"Uzgen District",1:"Kyrgyzstan"],feature=Uzgen
			// District,admin=Osh Province,sub-admin=Uzgen
			// District,locality=null,thoroughfare=null,postalCode=null,countryCode=KG,countryName=Kyrgyzstan,hasLatitude=true,latitude=40.7799603,hasLongitude=true,longitude=73.6065223,phone=null,url=null,extras=null]
			// Address[addressLines=[0:"Russia"],feature=Russia,admin=null,sub-admin=null,locality=null,thoroughfare=null,postalCode=null,countryCode=RU,countryName=Russia,hasLatitude=true,latitude=61.52401,hasLongitude=true,longitude=105.318756,phone=null,url=null,extras=null]
			// Address[addressLines=[0:"Kaoh Touch",1:"Cambodia"],feature=Kaoh
			// Touch,admin=Kampot,sub-admin=Tuek
			// Chhou,locality=null,thoroughfare=null,postalCode=null,countryCode=KH,countryName=Cambodia,hasLatitude=true,latitude=10.7916446,hasLongitude=true,longitude=104.053676,phone=null,url=null,extras=null]
			// Address[addressLines=[0:"Antarctica"],feature=Antarctica,admin=null,sub-admin=null,locality=null,thoroughfare=null,postalCode=null,countryCode=AQ,countryName=Antarctica,hasLatitude=true,latitude=-75.250973,hasLongitude=true,longitude=-0.071389,phone=null,url=null,extras=null]
			// Address[addressLines=[0:"40 Hawthorne Dr",1:"New Providence, NJ 07974",2:"USA"],feature=40,admin=New
			// Jersey,sub-admin=null,locality=New
			// Providence,thoroughfare=Hawthorne
			// Dr,postalCode=07974,countryCode=US,countryName=United
			// States,hasLatitude=true,latitude=40.695701,hasLongitude=true,longitude=-74.413861,phone=null,url=null,extras=null]
		} else {
			// Last Known Location not available
		}
		return lastKnownAddress;
	}

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(app, null, "Getting your location.");
        pd.setCancelable(true);
    }

    @Override
    protected void onPostExecute(Address address) {
        pd.dismiss();
    }
}