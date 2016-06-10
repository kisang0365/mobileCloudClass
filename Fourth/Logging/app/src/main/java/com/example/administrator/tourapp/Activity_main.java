package com.example.administrator.tourapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.tourapp.helper.Helper_log;
import com.example.administrator.tourapp.helper.Helper_server;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Activity_main extends AppCompatActivity {

    Button btn_showMyLog;
    TextView tv;
    Helper_log log = new Helper_log();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        btn_showMyLog = (Button) findViewById(R.id.btn_main_show);
        tv = (TextView) findViewById(R.id.tv_main_tourlist);

        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //appendText("onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location", location.toString());
                RequestParams params = new RequestParams();
                params.add("lat", "" + location.getLatitude());
                params.add("lng", "" + location.getLongitude());

                log.valueAdd(location.getLatitude(), location.getLongitude());

                Helper_server.post("log.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.d("aaaa", "success");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

                appendText("" + location.getLatitude());
            }
        };

        if (ActivityCompat.checkSelfPermission(Activity_main.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Activity_main.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener); //와이파이망을 이용한 위치정보획득
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);     //GPS를 사용한 gps 획득

        btn_showMyLog.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(Activity_main.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Activity_main.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.removeUpdates(locationListener);
                RequestParams params = new RequestParams();
                Helper_server.post("getLog.php", params, new JsonHttpResponseHandler() {
                    @Override

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.i("Msg", "success");
                        try {
                            int sum = Integer.parseInt(response.get("sum").toString());
                            Log.d("sumsumsum",""+sum);
                            for (int i = 0; i< sum; i++) {
                                System.out.println("comecome");
                                double lat = Double.parseDouble(response.get("lat"+i).toString());
                                double lng = Double.parseDouble(response.get("lng"+i).toString());
                                log.valueAdd(lat, lng);
                                Log.d("aaaaa"+i, "lat:" + lat + "lng" + lng);
                            }
                            Intent intent = new Intent(Activity_main.this, MapsActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Log.d("Failed: ", "" + statusCode);
                        Log.d("Error : ", "" + throwable);
                    }
                });
            }

        });

    }
    private void appendText(String msg) {
        tv.append(msg + "\n");
    }

}