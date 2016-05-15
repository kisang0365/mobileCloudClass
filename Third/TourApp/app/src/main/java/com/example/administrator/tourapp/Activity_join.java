package com.example.administrator.tourapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.tourapp.helper.Helper_checker;
import com.example.administrator.tourapp.helper.Helper_server;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016-05-07.
 */


//TODO 입력이 제대로 됐는지 체크
public class Activity_join extends Activity {

    public static final int Max = 4;
    boolean id_check_ok = false;
    Integer[] checkBoxId = {R.id.checkbox1, R.id.checkbox2, R.id.checkbox3, R.id.checkbox4};
    CheckBox[] mCheckBox = new CheckBox[Max];
    String[] isCheck = new String[Max];

    private form_basic form_basic;
    class form_basic {
        EditText et_id;
        EditText et_password;
        EditText et_name;

        Button btn_submit;

        TextView tv_idCheck;

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_join); // 항상 제공되는
        // activity_layout.xml을 복사해서
        // 만듦
        final form_basic form_basic = new form_basic();

        form_basic.et_id = (EditText) findViewById(R.id.et_join_id);
        form_basic.et_password = (EditText) findViewById(R.id.et_join_password);
        form_basic.et_name = (EditText) findViewById(R.id.et_join_name);

        form_basic.btn_submit = (Button) findViewById(R.id.btn_join_submit);
        //this.overridePendingTransition( R.anim.anim_slide_in_left, R.anim.anim_slide_in_right);

        form_basic.tv_idCheck = (TextView) findViewById(R.id.tv_join_idcheck);

        form_basic.et_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false) {
                    RequestParams idParams = new RequestParams();
                    String id = form_basic.et_id.getText().toString();
                    idParams.put("id", id);
                    if (!Helper_checker.validId_context(Activity_join.this, id)) {
                        id_check_ok = false;
                        form_basic.tv_idCheck.setText("사용불가");
                        return;
                    } else {
                        Helper_server.post("idCheck.php", idParams, new JsonHttpResponseHandler() {
                            @Override

                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                Log.i("Msg", "success");
                                String data = "";
                                try {
                                    data = response.get("ok").toString();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.d("ok", "" + data);
                                if (data.equals("true")) {
                                    form_basic.tv_idCheck.setText("사용가능");
                                    id_check_ok = true;
                                    return;
                                } else {
                                    form_basic.tv_idCheck.setText("이미있는아이디");
                                    id_check_ok = false;
                                    return;
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
                } else {
                    form_basic.tv_idCheck.setText("");
                    id_check_ok = false;

                }
            }
        });


        for(int i =0; i<mCheckBox.length; i++){
            mCheckBox[i] = (CheckBox) findViewById(checkBoxId[i]);
        }










        View.OnClickListener requestJoin = new View.OnClickListener() {
            public void onClick(View v) {


                    RequestParams params = new RequestParams();
                    Log.d("idcheck", "" + id_check_ok);
                    String id = form_basic.et_id.getText().toString();
                    String password = form_basic.et_password.getText().toString();
                    String name = form_basic.et_name.getText().toString();
                    if (!Helper_checker.validJoin(Activity_join.this, name, id, password)) {
                        return;
                    }
                    if(id_check_ok==false) {
                        RequestParams idParams = new RequestParams();
                        idParams.put("id", id);

                        Helper_server.post("idCheck.php", idParams, new JsonHttpResponseHandler() {
                            @Override

                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                Log.i("Msg", "success");
                                String data = "";
                                try {
                                    data = response.get("ok").toString();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.d("ok", "" + data);
                                if (data.equals("true")) {
                                    form_basic.tv_idCheck.setText("사용가능");
                                    id_check_ok = true;
                                } else {
                                    form_basic.tv_idCheck.setText("이미있는아이디");
                                    id_check_ok = false;
                                    Helper_checker.id_check_ok(Activity_join.this, id_check_ok);
                                    return;
                                }
                            }
                        });
                    }


                Log.i("Msg", "id : " + id + "pwd : " + password + "name : " + name);

                params.put("id", id);
                params.put("password", password);
                params.put("name", name);
                int sum=0;
                for(int i=0; i<Max; i++){
                        if(mCheckBox[i].isChecked()){
                            isCheck[i] = ""+i;
                            Log.i("Msg", "check"+i);
                            params.put("box"+sum, i);
                            sum++;
                        }
                }
                params.put("sum", sum);


                    Helper_server.post("member_Insert.php", params, new AsyncHttpResponseHandler() {
                        @Override

                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Log.i("Msg", "success");
                            joinAlert();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.i("Msg", "fali");
                        }
                    });
                }
            // Codes.InitApp(this); // *
        };

        form_basic.btn_submit.setOnClickListener(requestJoin);
//        form_basic.et_email.setOnKeyListener(new View.OnKeyListener() {
//                                                 @Override
//                                                 public boolean onKey(View v, int keyCode, KeyEvent event) {
//                                                     //Enter key Action
//                                                     if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                                                         InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//                                                         imm.hideSoftInputFromWindow(form_basic.et_email.getWindowToken(), 0);    //hide keyboard
//
//
//                                                             RequestParams params = new RequestParams();
//                                                             Log.d("idcheck", "" + id_check_ok);
//                                                             String id = form_basic.et_id.getText().toString();
//                                                             String password = form_basic.et_password.getText().toString();
//                                                             String name = form_basic.et_name.getText().toString();
//                                                             String phoneNumber = form_basic.et_phoneNumber.getText().toString();
//                                                             String email = form_basic.et_email.getText().toString();
//
//                                                             if (!Helper_checker.validJoin(Activity_join.this, name, id, password)) {
//                                                                 return false;
//                                                             }
//
//                                                             Log.i("Msg", "id : " + id + "pwd : " + password + "name : " + name);
//
//                                                             params.put("id", id);
//                                                             params.put("password", password);
//                                                             params.put("name", name);
//
//                                                             Helper_server.post("member_Insert.php", params, new AsyncHttpResponseHandler() {
//                                                                 @Override
//                                                                 public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                                                                     Log.i("Msg", "success");
//                                                                     joinAlert();
//                                                                 }
//
//                                                                 @Override
//                                                                 public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                                                                     Log.i("Msg", "fail");
//                                                                 }
//                                                             });
//                                                         }
//                                                         return true;
//                                                     }
//                                                 }
//
//        );
    } //onCreate 종료
        //email 입력후 엔터눌렀을때 회원가입 처리
//

    public void joinAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Activity_join.this);
        alert.setTitle("성공");
        alert.setMessage("가입이 완료되셨습니다.");
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Activity_join.this, Activity_login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });
        alert.show();
    }

    public static String urlEncodeUTF8(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(Activity_join.this, Activity_login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

}