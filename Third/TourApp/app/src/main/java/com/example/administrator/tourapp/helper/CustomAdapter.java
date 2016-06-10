package com.example.administrator.tourapp.helper;

/**
 * Created by Administrator on 2016-05-15.
 */
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tourapp.Activity_main;
import com.example.administrator.tourapp.MapsActivity;
import com.example.administrator.tourapp.R;

public class CustomAdapter extends BaseAdapter {

    // 문자열을 보관 할 ArrayList
    private ArrayList<String>   m_List;
    private Context mContext;
    private Helper_listData data = Helper_listData.getInstance();
    public int[] picture = {R.drawable.paris, R.drawable.tokyo, R.drawable.rome, R.drawable.kookmin};
    // 생성자
    public CustomAdapter(Context mContext) {
        this.mContext = mContext;
        m_List = new ArrayList<String>()    ;
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        Log.d("testtest", ""+pos);
        Log.d("testtest", ""+data.getName(0));
        Log.d("testtest", ""+data.getexplain(1));
        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, parent, false);

            // TextView에 현재 position의 문자열 추가
            TextView text_name = (TextView) convertView.findViewById(R.id.text_name);
            text_name.setText(data.getName(Integer.parseInt(m_List.get(position).toString().trim())));

            TextView text_explain = (TextView) convertView.findViewById(R.id.text_explain);
            text_explain.setText(data.getexplain(Integer.parseInt(m_List.get(position).toString().trim())));

            ImageView img = (ImageView) convertView.findViewById(R.id.img_flag);
            img.setImageResource(picture[Integer.parseInt(m_List.get(position).toString().trim())]);
            // 리스트 아이템을 터치 했을 때 이벤트 발생
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    data.setViewPoint(Integer.parseInt(m_List.get(pos).toString().trim()));
                    Intent mapOn = new Intent(mContext, MapsActivity.class);
                    mapOn.addFlags(mapOn.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(mapOn);
                }
            });
        }
        return convertView;
    }

    // 외부에서 아이템 추가 요청 시 사용
    public void add(String _msg) {
        m_List.add(_msg);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }
}