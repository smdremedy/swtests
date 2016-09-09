package com.soldiersofmobile.logintest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 23, constants = BuildConfig.class)
public class SharedPreferencesUserRepositoryShould {

    public static final String TOKEN = "TOKEN";
    private SharedPreferencesUserRepository repository;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() throws Exception {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
               RuntimeEnvironment.application);

        repository = new SharedPreferencesUserRepository(sharedPreferences);


    }

    @Test
    public void storeToken() throws Exception {
        //given

        //when
        repository.storeToken(TOKEN);
        //then

        String token = sharedPreferences.getString("token", "");
        assertEquals(TOKEN, token);

    }

    @Test
    public void checkIsNotLogged() throws Exception {
        //given

        //when
        boolean isNotLogged = repository.isNotLogged();

        //then
        assertTrue(isNotLogged);

    }

    @Test
    public void checkIsLogged() throws Exception {
        //given
        sharedPreferences.edit().putString("token", "asdada").apply();

        //when
        boolean isNotLogged = repository.isNotLogged();
        //then
        assertFalse(isNotLogged);

    }
}