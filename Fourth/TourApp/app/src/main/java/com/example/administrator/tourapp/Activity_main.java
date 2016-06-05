package com.example.administrator.tourapp;

import android.content.Context;
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

public class Activity_main extends AppCompatActivity {

    Button btn_showMyLog;
    LocationManager manager;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        btn_showMyLog = (Button) findViewById(R.id.btn_main_show);
        tv = (TextView) findViewById(R.id.tv_main_tourlist);
        btn_showMyLog.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {


                LocationListener locationListener = new LocationListener() {
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
                     //   appendText(location.toString());
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

                appendText("로깅버튼눌렀음..");
            }

        });


    }
    private void appendText(String msg) {
        tv.append(msg + "\n");
    }






}