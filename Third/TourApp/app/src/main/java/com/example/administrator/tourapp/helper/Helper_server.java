package com.example.administrator.tourapp.helper;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.*;
import org.json.JSONArray;

import java.util.List;

import cz.msebera.android.httpclient.cookie.Cookie;

/**
 * Created by Administrator on 2016-05-07.
 */

public class Helper_server {

    public static String myJSON;
    public static JSONArray peoples = null;
    public static Helper_userData userData;
    private static String id = null;

    private static final String BASE_URL = "http://54.199.191.229/Tour/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static AsyncHttpClient getInstance() {
        return Helper_server.client;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static boolean login(PersistentCookieStore myCookieStore){
        List<Cookie> cookieList = myCookieStore.getCookies();
        if (!cookieList.isEmpty()) {
            for (int i = 0; i < cookieList.size(); i++) {
                // cookie = cookies.get(i);
                String cookieString = cookieList.get(i).getName() + "="
                        + cookieList.get(i).getValue();
                Log.e("surosuro", cookieString);

                if(cookieList.get(i).getName().equals("isLogin")){
                    if(cookieList.get(i).getValue().equals("true")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static String getCookieValue(PersistentCookieStore myCookieStore, String name){
        List<Cookie> cookieList = myCookieStore.getCookies();
        if (!cookieList.isEmpty()) {
            for (int i = 0; i < cookieList.size(); i++) {
                // cookie = cookies.get(i);
                String cookieString = cookieList.get(i).getName() + "="
                        + cookieList.get(i).getValue();
                Log.e("surosuro", cookieString);

                if(cookieList.get(i).getName().equals(name)){
                    return cookieList.get(i).getValue();
                }
            }
        }
        return "";
    }

    public String cookieId(){
        return id;
    }

    public static void logout(PersistentCookieStore myCookieStore, Context mContext){
        myCookieStore.clear();
        client.setCookieStore(myCookieStore);
    }



    //로그인해서 정보 얻을때
    public static String isLogIn(Context mContext){
        //TODO 회원가입할때 아이디는 숫자만으로 가입 불가능하게 만들어야한다!

        //쿠키로 로그인 정보 확인 찾으면 아이디를 리턴


        //리턴한 값으로 디비에 페이스북 아이디와 그냥 유저아이디에서 검색을 때려 둘중 하나가 나올테니 그걸로 한다.

        //못 찾으면 null 리턴
        return null;
    }


    public static String getAbsoluteUrl(String relativeUrl){
        return BASE_URL + relativeUrl;
    }
}
