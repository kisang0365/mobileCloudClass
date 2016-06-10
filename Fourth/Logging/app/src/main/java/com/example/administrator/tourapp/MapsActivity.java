package com.example.administrator.tourapp;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.tourapp.helper.Helper_log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Helper_log log = Helper_log.getInstance();

        double maxLat=1000;
        double minLat = -1000;
        double maxLng=1000;
        double minLng = -1000;

        for(int i=0; i<log.lat.size(); i++){
            LatLng sydney = new LatLng(log.lat.get(i), log.lng.get(i));
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney" + i));
            if(maxLat<log.lat.get(i)) maxLat = log.lat.get(i);
            if(minLat>log.lat.get(i)) minLat = log.lat.get(i);
            if(maxLng<log.lng.get(i)) maxLng = log.lng.get(i);
            if(minLng>log.lng.get(i)) maxLng = log.lng.get(i);
        }
        Location locationA = new Location("point A");

        locationA.setLatitude(maxLat);
        locationA.setLongitude(maxLng);

        Location locationB = new Location("point B");

        locationB.setLatitude(minLat);
        locationB.setLongitude(minLng);

        distance = locationA.distanceTo(locationB);
        System.out.println(distance + "");

        LatLng sydney = new LatLng((maxLat+minLat)/2, (maxLng+minLng)/2);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12));

//        mMap.addMarker(new MarkerOptions()
////                .position(new LatLng(11,10.123))
////                .title("Hello world2"));


        // Add a marker in Sydney and move the camera

    }
}
