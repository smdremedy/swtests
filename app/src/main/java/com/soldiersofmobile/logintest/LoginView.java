package com.soldiersofmobile.logintest;

public interface LoginView {
    void showLoginError(String error);

    void showPasswordError(int errorResource);

    void goToLogin();
}
