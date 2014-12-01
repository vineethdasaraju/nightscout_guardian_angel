package com.nightscout.nightscoutga.Background;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.nightscout.nightscoutga.util.Constants;
import com.nightscout.nightscoutga.util.Functions;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class changePasswordAsyncTask extends AsyncTask<Void, Void, Void> {

	Context context;
	JSONObject obj;
	ProgressDialog pd = null;
	boolean success = false;
	Activity app = null;

	public changePasswordAsyncTask(Context context, JSONObject obj, Activity app) {
		this.context = context;
		this.obj = obj;
		this.app = app;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		pd.dismiss();
		if (success) {
			Functions.toast("Your password has been updated.", context);
			app.finish();
		} else {
			Functions.toast("The password you entered is incorrect.", context);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pd = ProgressDialog.show(context, "Please Wait",
				"Updating your Password...");
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		String response = "";
		String content = obj.toString();
		DataOutputStream outputStream = null;
		URL new_url;
		try {
			new_url = new URL(Constants.ChangePassword);
			HttpURLConnection connection = (HttpURLConnection) new_url
					.openConnection();
			connection.setRequestMethod(Constants.HTTP_POST);
			connection.setRequestProperty("Content-Type",
					Constants.BG_SERVICE_CONTENT_TYPE_JSON);
			connection.setRequestProperty("Connection", "Keep-Alive");
			outputStream = new DataOutputStream(connection.getOutputStream());
			byte[] BytesToBeSent = content.getBytes();
			if (BytesToBeSent != null) {
				outputStream.write(BytesToBeSent, 0, BytesToBeSent.length);
			}
			int responseCode = connection.getResponseCode();
			if (responseCode == 200 || responseCode == 202) {
				success = true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
