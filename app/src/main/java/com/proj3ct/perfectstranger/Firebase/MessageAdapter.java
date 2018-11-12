package com.proj3ct.perfectstranger.Firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.proj3ct.perfectstranger.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MessageAdapter extends BaseAdapter {
    /* 아이템을 세트로 담기 위한 어레이 */
    public ArrayList<Message> messages = new ArrayList<>();

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_message, parent, false);
        }

        /* 'listview_custom'에 정의된 위젯에 대한 참조 획득 */
        TextView tv_name = convertView.findViewById(R.id.tv_name) ;
        TextView tv_app = convertView.findViewById(R.id.tv_app) ;
        TextView tv_time = convertView.findViewById(R.id.tv_time);
        TextView tv_value = convertView.findViewById(R.id.tv_value);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        Message message = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        tv_name.setText(message.getName());
        tv_app.setText(message.getApp());
        Date time = new Date(message.getTimestamp());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd hh:mm:ss", Locale.KOREA);
        tv_time.setText(sdf.format(time));
        tv_value.setText(message.getValue());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */


        return convertView;
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(Message message) {
        messages.add(message);
    }
}
