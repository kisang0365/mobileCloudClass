package com.example.administrator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] mButton = new Button[10];
    Button calc_add, calc_sub, calc_multi, calc_div;
    Integer[] btnId = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    EditText edit_value1, edit_value2;
    TextView tv_result;
    Integer result;
    String num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_value1 = (EditText) findViewById(R.id.value1);
        edit_value2 = (EditText) findViewById(R.id.value2);

        calc_add = (Button) findViewById(R.id.add);
        calc_sub = (Button) findViewById(R.id.sub);
        calc_multi = (Button) findViewById(R.id.Mutiply);
        calc_div = (Button) findViewById(R.id.Divide);

        tv_result = (TextView) findViewById(R.id.tv_result);

        edit_value1.setInputType(0);
        edit_value2.setInputType(0);

        calc_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit_value1.getText().toString();
                num2 = edit_value2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                tv_result.setText("Result : " + result.toString());
            }
        });
        calc_sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit_value1.getText().toString();
                num2 = edit_value2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                tv_result.setText("Result : " + result.toString());
            }
        });
        calc_multi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit_value1.getText().toString();
                num2 = edit_value2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                tv_result.setText("Result : " + result.toString());
            }
        });
        calc_div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = edit_value1.getText().toString();
                num2 = edit_value2.getText().toString();
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                tv_result.setText("Result : " + result.toString());
            }
        });

        for(int i =0; i<mButton.length; i++){
            mButton[i] = (Button) findViewById(btnId[i]);
        }

        for(int i = 0; i<btnId.length; i++){

            final int index = i;

            mButton[index].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(edit_value1.isFocused()==true) {
                        num1 = edit_value1.getText().toString()
                                + mButton[index].getText().toString();
                        edit_value1.setText(num1);
                    }else if(edit_value2.isFocused() == true) {
                        num2 = edit_value2.getText().toString()
                                + mButton[index].getText().toString();
                        edit_value2.setText(num2);
                    } else{
                        return;
                    }
                }
            });
        }
    }
}