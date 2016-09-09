package com.soldiersofmobile.logintest.di;


import com.soldiersofmobile.logintest.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = AppModule.class
)
public interface AppComponent {

    void inject(LoginActivity activity);
}
