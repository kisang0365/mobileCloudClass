package com.example.administrator.calculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016-04-19.
 */
public class teleActivity extends Activity {

    EditText et_phoneUri;
    Button btn_call;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele);

        et_phoneUri = (EditText) findViewById(R.id.teleuri);
        btn_call = (Button) findViewById(R.id.call);


        btn_call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String uri = "tel:";
                uri+=et_phoneUri.getText().toString();
                Intent callActivity = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                startActivity(callActivity);
            }
        });

    }
}