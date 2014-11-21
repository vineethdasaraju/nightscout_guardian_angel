package com.nightscout.nightscoutga.UI;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.nightscout.nightscoutga.Constants;
import com.nightscout.nightscoutga.R;

public class GraphActivity extends Activity {

    private WebView mWebView;
    private TextView mTextSGV;
    private TextView mTextTimestamp;

    private float currentUnits = 1;

    private Context mContext;
    private String mJSONData;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        mContext = getApplicationContext();

        mTextSGV = (TextView) findViewById(R.id.sgValue);
        mTextSGV.setTag(R.string.display_sgv, -1);
        mTextSGV.setTag(R.string.display_trend, 0);
        mTextTimestamp = (TextView) findViewById(R.id.timeAgo);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setUseWideViewPort(false);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setBackgroundColor(0);
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("GraphActivity", "onPaused called.");
        mWebView.pauseTimers();
        mWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("GraphActivity", "onResumed called.");
        mWebView.onResume();
        mWebView.resumeTimers();

        // Set and deal with mmol/L<->mg/dL conversions
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        Log.d("GraphActivity","display_options_units: "+prefs.getString("display_options_units", "0"));
        currentUnits = prefs.getString("display_options_units", "0").equals("0") ? 1 : Constants.MG_DL_TO_MMOL_L;
        int sgv = (Integer) mTextSGV.getTag(R.string.display_sgv);

        int direction = (Integer) mTextSGV.getTag(R.string.display_trend);
        if (sgv != -1) {
            mTextSGV.setText(getSGVStringByUnit(sgv, Constants.TREND_ARROW_VALUES.values()[direction]));
        }


        mWebView.loadUrl("javascript:updateUnits(" + Boolean.toString(currentUnits == Constants.MG_DL_TO_MMOL_L) +  ")");

        //mHandler.post(updateTimeAgo);
    }

    private String getSGVStringByUnit(int sgv,Constants.TREND_ARROW_VALUES trend){
        String sgvStr;
        if (currentUnits!=1)
            sgvStr=String.format("%.1f",sgv*currentUnits);
        else
            sgvStr=String.valueOf(sgv);
        String responseSGVStr = (sgv!=-1)?
                (Constants.SPECIALBGVALUES_MGDL.isSpecialValue(sgv))?Constants.SPECIALBGVALUES_MGDL.getEGVSpecialValue(sgv).toString():sgvStr+" "+trend.Symbol():"---";
        return responseSGVStr;
    }

    @Override
    protected void onDestroy() {
        Log.d("GraphActivity", "onDestroy called.");
        super.onDestroy();
        //unregisterReceiver(mCGMStatusReceiver);
        //unregisterReceiver(mDeviceStatusReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
        outState.putString("saveJSONData", mJSONData);
        outState.putString("saveTextSGV", mTextSGV.getText().toString());
        outState.putString("saveTextTimestamp", mTextTimestamp.getText().toString());
        //outState.putBoolean("saveImageViewUSB", statusBarIcons.getUSB());
        //outState.putBoolean("saveImageViewUpload", statusBarIcons.getUpload());
        //outState.putBoolean("saveImageViewTimeIndicator", statusBarIcons.getTimeIndicator());
        //outState.putInt("saveImageViewBatteryIndicator", statusBarIcons.getBatteryIndicator());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state of the WebView
        mWebView.restoreState(savedInstanceState);
        mJSONData = savedInstanceState.getString("mJSONData");
        mTextSGV.setText(savedInstanceState.getString("saveTextSGV"));
        mTextTimestamp.setText(savedInstanceState.getString("saveTextTimestamp"));
        //statusBarIcons.setUSB(savedInstanceState.getBoolean("saveImageViewUSB"));
        //statusBarIcons.setUpload(savedInstanceState.getBoolean("saveImageViewUpload"));
        //statusBarIcons.setTimeIndicator(savedInstanceState.getBoolean("saveImageViewTimeIndicator"));
        //statusBarIcons.setBatteryIndicator(savedInstanceState.getInt("saveImageViewBatteryIndicator"));
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
