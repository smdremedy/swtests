package com.soldiersofmobile.logintest;


import android.content.SharedPreferences;

public class SharedPreferencesUserRepository implements UserRepository {

    private SharedPreferences mPreferences;

    public SharedPreferencesUserRepository(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    @Override
    public boolean isNotLogged() {
        return mPreferences.getString("token","").isEmpty();
    }

    @Override
    public void storeToken(String token) {

        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putString("token", token);
        edit.apply();

    }

}
