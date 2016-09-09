package com.soldiersofmobile.logintest;

public interface UserRepository {
    boolean isNotLogged();

    void storeToken(String token);
}
