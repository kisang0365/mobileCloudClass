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
    public static int sum=0;

    public Helper_log(){

    }



    public void valueAdd(double latValue, double lngValue){
        lat.add(latValue);
        lng.add(lngValue);
        sum++;
        Log.d("aaaaa", ""+latValue);
        Log.d("aaaaa", ""+lngValue);
    }

   public static Helper_log getInstance(){
       return instance;
   }


}
