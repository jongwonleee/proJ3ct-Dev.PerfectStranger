package com.proj3ct.perfectstranger;

import android.app.Application;

import java.util.Vector;

public class AppVariables extends Application {
    Profile myProfile;
    Vector<Rule> rules;
    Vector<Profile> friends;

    public Profile getMyProfile() {
        return myProfile;
    }

    public void setMyProfile(Profile myProfile) {
        this.myProfile = myProfile;
    }

    public Vector<Rule> getRules() {
        return rules;
    }

    public void setRules(Vector<Rule> rules) {
        this.rules = rules;
    }

    public Vector<Profile> getFriends() {
        return friends;
    }

    public void setFriends(Vector<Profile> friends) {
        this.friends = friends;
    }
    public void addRule(Rule rule){
        rules.add(rule);
    }
    public void addFriend(Profile p){
        friends.add(p);
    }
}
