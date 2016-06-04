package com.example.administrator.tourapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.tourapp.helper.CustomAdapter;
import com.example.administrator.tourapp.helper.Helper_userData;

public class Activity_main extends AppCompatActivity {

    private ListView m_ListView;
    private CustomAdapter m_Adapter;
    final int MAX = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Activity_login login = (Activity_login) Activity_login.A_login;
        login.finish();

        Helper_userData data = Helper_userData.getInstance();
//        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        m_Adapter = new CustomAdapter(getApplicationContext());
        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.lv_main);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView에 아이템 추가

        for(int i=0; i<MAX; i++){
            if(data.getBox(i)!=-1){
                m_Adapter.add(""+data.getBox(i));
            }
        }
    }


    //Log.d("MSG", ""+user.getId()+":::"+user.getBox0()+user.getBox1()+ user.getBox2()+ user.getBox3());
}
