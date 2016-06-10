package com.example.administrator.tourapp.helper;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-06-04.
 */
public class Helper_log {

    private static Helper_log instance;
    public static ArrayList<Double> lat = new ArrayList<Double>();
    public static ArrayList<Double> lng = new ArrayList<Double>();

    public Helper_log(){

    }

    public void valueAdd(double latValue, double lngValue){
        this.lat.add(latValue);
        this.lng.add(lngValue);
    }

   public static Helper_log getInstance(){
       return instance;
   }


}
