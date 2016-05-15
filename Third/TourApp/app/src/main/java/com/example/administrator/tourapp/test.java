package com.example.administrator.tourapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class test extends AppCompatActivity {
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        LatLng KANGNAM = new LatLng(37.5172360, 127.0473250);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.addMarker(new MarkerOptions().position(KANGNAM).title("ggari").snippet("hello"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(KANGNAM, 15));
    }
}