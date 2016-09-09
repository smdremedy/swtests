package com.soldiersofmobile.logintest.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.soldiersofmobile.logintest.Api;
import com.soldiersofmobile.logintest.LoginPresenter;
import com.soldiersofmobile.logintest.LoginResponse;
import com.soldiersofmobile.logintest.SharedPreferencesUserRepository;
import com.soldiersofmobile.logintest.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {

        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    UserRepository provideUserRepository(SharedPreferences preferences) {
        return new SharedPreferencesUserRepository(preferences);
    }

    @Provides
    Api provideApi() {
        return new Api() {
            @Override
            public LoginResponse getLogin(String username, String password) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.token = "TOKEN1";
                return loginResponse;
            }
        };
    }

    @Singleton
    @Provides
    LoginPresenter provideLoginPresenter(UserRepository repository, Api api) {
        return new LoginPresenter(repository, api);
    }

}
