package com.proj3ct.perfectstranger;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SharedPref {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }
    public String getRoomKey() {
        SharedPreferences pref = context.getSharedPreferences("room", Context.MODE_PRIVATE);
        return pref.getString("roomKey", null);
    }
    public User getUser() {
        SharedPreferences pref = context.getSharedPreferences("room", Context.MODE_PRIVATE);
        String strUser = pref.getString("user", null);
        if(strUser != null) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(strUser, User.class);
        }
        else
            return null;
    }
    public void setPref(String roomKey, User user) {
        SharedPreferences pref = context.getSharedPreferences("room", Context.MODE_PRIVATE);

        Gson gson = new GsonBuilder().create();
        String strUser = gson.toJson(user, User.class);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("roomKey", roomKey);
        editor.putString("user", strUser);
        editor.apply();
    }
    public void destroy() {
        SharedPreferences pref = context.getSharedPreferences("room", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }
}
