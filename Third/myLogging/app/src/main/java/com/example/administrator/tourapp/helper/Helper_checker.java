package com.example.administrator.tourapp.helper;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-05-08.
 */

//TODO 전화번호 검사를 해줘야함
public class Helper_checker {
    public static final int MIN=5;
    public static final int MAX=20;
    public static final int NAMEMIN = 3;
    public static final int NAMEMAX = 20;

    public static boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return b;
    }
    public static boolean validName(String name){
        if( name == null ) return false;
        if( name.length() <NAMEMIN || name.length()>NAMEMAX) return false;
        return true;
    }

    public static boolean validId(String id){
        //TODO 아이디 중복 검사
        if( id == null ) return false;
        if( id.length() < MIN || id.length() > MAX ) return false;
        return true;
    }
    public static boolean onlyNumberId(String id){
        if(Pattern.matches("^[0-9]+$", id)) return false;
        return true;
    }


    public static boolean validPassword(String pw){
        if( pw == null ) return false;
        if( pw.length() < MIN ) return false;
        return true;
    }

    public static boolean validId_context(Context context, String id){
        if( !validId(id) ){
            Toast.makeText(context, "아이디는 5글자이상 20글자 이하여야합니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!onlyNumberId(id)){
            Toast.makeText(context, "아이디는 숫자로만 이루어지면 안됩니다. ", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public static boolean id_check_ok(Context context, boolean id_check){
        if(!id_check){
            Toast.makeText(context, "중복된 아이디 입니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    public static boolean validJoin(Context context, String name, String id, String pw){
        if( !validId(id) ){
            Toast.makeText(context, "아이디는 5글자이상 20글자 이하여야합니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!onlyNumberId(id)){
            Toast.makeText(context, "아이디는 숫자로만 이루어지면 안됩니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        if( !validPassword(pw) ){
            Toast.makeText(context, "비밀번호는 5글자 이상이어야합니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!validName(name)) {
            Toast.makeText(context, "이름은 3글자이상 20글자 이하여야합니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
