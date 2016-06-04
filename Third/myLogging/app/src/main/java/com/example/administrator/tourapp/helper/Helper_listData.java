package com.example.administrator.tourapp.helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.tourapp.Activity_main;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016-05-15.
 */
public class Helper_listData {

    private static Helper_listData listData = null;

    private static int MAX = 4;
    private static String[] name = new String[MAX];
    private static double[] lat = new double[MAX];
    private static double[] lng = new double[MAX];
    private static String[] explain = new String[MAX];
    private static int viewPoint = -1;

    public Helper_listData(){

    }

    public static void setData(String name, double lat, double lng, String explain, int index) {
        setName(index, name);
        setLat(index, lat);
        setLng(index, lng);
        setexplain(index, explain);
    }
    public static void Helper_listDatas(final Context mContext) {

        if(listData == null) {
            final RequestParams idParams = new RequestParams("id", "111");
            Helper_server.post("getListData.php", idParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {

                        for (int i = 0; i < MAX; i++) {
                            String name = response.get("name" + i).toString().trim();
                            double lat = Double.parseDouble(response.get("lat" + i).toString().trim());
                            double lng = Double.parseDouble(response.get("lng" + i).toString().trim());
                            String explain = response.get("explain" + i).toString().trim();
                            listData = new Helper_listData();
                            listData.setData(name, lat, lng, explain, i);
                            System.out.println(name + lat + lng + explain);
                        }

                        Intent intent = new Intent(mContext, Activity_main.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d("Failed: ", "myself " + statusCode);
                    Log.d("Error : ", "myself " + throwable);
                }
            });
            System.out.println("ListData finish");
        }
    }

    public static Helper_listData getInstance(){
        return listData;
    }

    public static String getName(int index) {
        return name[index];
    }

    public static void setName(int index, String value) {
        name[index]=value;
    }
    public static double getLat(int index) {
        return lat[index];
    }

    public static void setLat(int index, double value) {
        lat[index]=value;
    }
    public static double getLng(int index) {
        return lng[index];
    }

    public static void setLng(int index, double value) {
        lng[index]=value;
    }


    public static String getexplain(int index) {
        return explain[index];
    }

    public static void setexplain(int index, String value) {
        explain[index]=value;
    }

    public static int getViewPoint() {
        return viewPoint;
    }

    public static void setViewPoint(int value) {
        viewPoint = value;
    }
}
