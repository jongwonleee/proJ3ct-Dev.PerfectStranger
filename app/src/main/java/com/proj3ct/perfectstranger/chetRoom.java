package com.proj3ct.perfectstranger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.proj3ct.perfectstranger.Firebase.FirebaseDB;

import org.w3c.dom.Text;

public class chetRoom extends AppCompatActivity {
    private String roomkey;
    private FirebaseDB firebaseDB = new FirebaseDB();

    // View component
    private RecyclerView list_chet;
    private Button but_back;
    private TextView but_friends,but_rules,but_newMessage;

    // BroadcasRecevier : service를 감시하여 값을 받아서 firebaseDB 방아온 메세지를 넘겨줌
    // 받아오는 메세지 : 앱이름 / MainText / subText / 시간 / text / 프로필( 예정 ) 정도.
    private BroadcastReceiver onNotice = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String mainTitle = intent.getStringExtra("mainTitle");
            String subTitle = intent.getStringExtra("subTitle");
            String mainText = intent.getStringExtra("mainText");
            String appName = intent.getStringExtra("appName");
            firebaseDB.sendMessage(participant.getName(),mainTitle,subTitle,mainText,appName);
        }
    };

    private Participant participant = new Participant();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chet_room);
        Intent intent = getIntent();

        but_back=(Button)findViewById(R.id.but_back);
        but_friends=(TextView)findViewById(R.id.text_friends);
        but_rules=(TextView)findViewById(R.id.text_rules);
        list_chet = (RecyclerView)findViewById(R.id.listview_chat);
        but_newMessage = (TextView)findViewById(R.id.but_newMessage);
        //fireBaseDB 설정
        firebaseDB.setList_chet(list_chet,getApplicationContext(),but_newMessage);

        // callback 함수
        // LocalBroadcastManager( Local를 사용한 이유 : 다른앱의 서비스의 방해를 방지 )
        // 값을 받아오면 onNotice함수를 실행( "Msg"태그의 intent를 함께 전달 )
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));

        // intent에 roomkey 저장되어 있음.
        if(intent!=null){
            roomkey = intent.getStringExtra("roomkey");
            participant = (Participant)intent.getSerializableExtra("participant");
            firebaseDB.enterRoom(roomkey);
            firebaseDB.setParticipant(participant);
        }

        but_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        but_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chetRoom.this,waitingRoom.class);
                intent.putExtra("roomKey", firebaseDB.getRoomKey());
                startActivity(intent);
            }
        });
        but_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chetRoom.this,RulesActivity.class);
                startActivity(intent);
            }
        });
        but_newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_chet.smoothScrollToPosition(list_chet.getAdapter().getItemCount()-1);
                but_newMessage.setVisibility(View.GONE);
            }
        });
    }
}
