package com.proj3ct.perfectstranger.Rule;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.proj3ct.perfectstranger.R;

/**
 * Created by Administrator on 2018-11-14.
 */
public class rulesViewHolder extends RecyclerView.ViewHolder {
    ConstraintLayout layout_bg;
    OnListItemClickListener itemListener;
    TextView text_rule;
    boolean inSet;
    Rule rule;
    CheckBox check_edit;
    public rulesViewHolder(final View itemView) {
        super(itemView);
        layout_bg = (ConstraintLayout)itemView.findViewById(R.id.listview_rule_bg);
        text_rule = (TextView)itemView.findViewById(R.id.text_rule);
        check_edit = (CheckBox)itemView.findViewById(R.id.check_edit);

        layout_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onEditButtonClick(getAdapterPosition());
            }
        });

    }
    public rulesViewHolder(View itemView,boolean bool) {
        super(itemView);
    }
    /**
     * ruleType=1 : n번째 메세지 온 사람 벌칙, detail_i
     * ruleType=2 : 연속으로 n번 온 사람 벌칙, detail_i
     * ruleType=3 : 금지어 포함 메세지 벌칙, detail_s
     * ruleType=4 : n분동안 연락 안 온사람 벌칙, detail_i
     * ruleType=5 : 일정 앱 알림 시 벌칙, detail_s
     */
    public void initiallizeSetting(Rule rule){
        this.rule=rule;
        switch (this.rule.ruleType)
        {
            case 0:
                text_rule.setText(rule.detail_s);
                break;
            case 1:
                text_rule.setText(rule.detail_i+"번째 메세지 온 사람 벌칙");
                break;
            case 2:
                text_rule.setText("연속으로 "+rule.detail_i+"번 연락 온 사람 벌칙");
                break;
            case 3:
                text_rule.setText("\""+rule.detail_s +"\" 포함 메세지 온 사람 벌칙");
                break;
            case 4:
                text_rule.setText(rule.detail_i+"분동안 연락 안 온사람 벌칙");
                break;
            case 5:
                text_rule.setText(rule.detail_s+" 알림 시 벌칙");
                break;
        }
        itemListener.onRuleChanged(getAdapterPosition(),rule);
    }
/*    *//**
     * type=1 : 숫자패드 사용
     * type=2 : 에딧 사용
     * type=3 : textview 이용한 스피너사용
     *//*
    private void change_setdetail(int type){
        if(type==1)
        {
            set_num.setVisibility(View.VISIBLE);
            but_down.setEnabled(true);
            but_up.setEnabled(true);
            text_num.setText(rule.detail_i+"");
            set_text.setVisibility(View.GONE);
            edit_text.setEnabled(false);
            set_type.setVisibility(View.GONE);
            text_type.setEnabled(false);
        }else if(type==2)
        {
            set_num.setVisibility(View.GONE);
            but_down.setEnabled(false);
            but_up.setEnabled(false);
            set_text.setVisibility(View.VISIBLE);
            edit_text.setEnabled(true);
            edit_text.setText(rule.detail_s);
            set_type.setVisibility(View.GONE);
            text_type.setEnabled(false);
        }else if(type==3)
        {
            set_num.setVisibility(View.GONE);
            but_down.setEnabled(false);
            but_up.setEnabled(false);
            set_text.setVisibility(View.GONE);
            edit_text.setEnabled(false);
            set_type.setVisibility(View.VISIBLE);
            text_type.setEnabled(true);
        }
    }*/
    public void ChangeSetMode(boolean set){
        inSet=set;
        if(set){
            check_edit.setChecked(true);
        }else{
            check_edit.setChecked(false);
        }
    }
    public void setOnItemClickListener(rulesViewHolder.OnListItemClickListener onListItemClickListener){
        itemListener = onListItemClickListener;
    }
    public interface OnListItemClickListener{
        public void onEditButtonClick(int position);
        public void onNewButtonClick(int position);
        public void onDeleteButtonClick(int position);
        public void onRuleChanged(int position,Rule rule);
    }
}