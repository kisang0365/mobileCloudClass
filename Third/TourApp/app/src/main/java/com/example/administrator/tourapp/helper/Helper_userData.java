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
 * Created by Administrator on 2016-05-07.
 */
public class Helper_userData {

    private static Helper_userData user = null;

    static int MAX = 4;
    private static String id = "";
    private static String name = "";
    private static int[] Box = new int[MAX];
    private static boolean ok = false;

    public Helper_userData(String id, String name, int box0, int box1, int box2, int box3) {
        this.id = id;
        this.name = name;
        this.Box[0] = box0;
        this.Box[1] = box1;
        this.Box[2] = box2;
        this.Box[3] = box3;

    }

    public static void login_GetData(String id, final Context mContext) {
        if( user == null ) {
            final RequestParams idParams = new RequestParams("id", id);

            Helper_server.post("getProfile_Id.php", idParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.i("myself", "success");
                    Log.i("myself", "success");
                    String id;
                    String name;
                    int box0;
                    int box1;
                    int box2;
                    int box3;
                    try {
                        id = isNull_String(response.get("id"));
                        name = isNull_String(response.get("name"));
                        box0 = isNull_Int(response.get("box0"));
                        box1 = isNull_Int(response.get("box1"));
                        box2 = isNull_Int(response.get("box2"));
                        box3 = isNull_Int(response.get("box3"));

                        Log.d("userData", id);
                        Log.d("userData", name);

                        user = new Helper_userData(id, name, box0, box1, box2, box3);

                        System.out.println("aaaaa : " + user);

                        Helper_listData.Helper_listDatas(mContext);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d("Failed: ", "myself " + statusCode);
                    Log.d("Error : ", "myself " + throwable);
                }
            });
        }
        else{
            Intent intent = new Intent(mContext, Activity_main.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }

    public static Helper_userData getInstance(){
        return user;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String ids) {
        id = ids;
    }

    public static String getName() {
        return name;
    }


    public static void setName(String names) {
        name = names;
    }
    public static int getBox(int index) {
        return Box[index];
    }

    public static void setBox(int index, int value) {
        Box[index]=value;
    }

    public static boolean getOk(){
        return ok;
    }
    public static void setOk(boolean fact){
        ok = fact;
    }

    public static String isNull_String(Object response){
        if(response == null | response.equals(null)) return "";
        else return response.toString().trim();
    }
    public static int isNull_Int(Object response){
        if(response == null || response.equals(null)) return -1;
        else return Integer.parseInt(response.toString().trim());

    }


}