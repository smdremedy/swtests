package com.soldiersofmobile.logintest;


import android.app.Application;

import com.byoutline.androidstubserver.AndroidStubServer;
import com.byoutline.mockserver.NetworkType;
import com.soldiersofmobile.logintest.di.AppComponent;
import com.soldiersofmobile.logintest.di.AppModule;
import com.soldiersofmobile.logintest.di.DaggerAppComponent;

public class App extends Application {

    public static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidStubServer.start(this, NetworkType.EDGE);
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }
}
