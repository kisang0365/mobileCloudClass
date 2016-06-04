package com.example.administrator.tourapp;

import android.content.Context;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_main extends AppCompatActivity {

    Button btn_showMyLog;
    LocationManager mLM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_showMyLog = (Button) findViewById(R.id.btn_main_show);

        btn_showMyLog.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {



            }
        });



    }
}