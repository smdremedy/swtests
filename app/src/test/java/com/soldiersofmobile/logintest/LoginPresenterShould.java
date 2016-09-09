package com.soldiersofmobile.logintest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterShould {

    private LoginPresenter loginPresenter;
    private LoginView loginView;
    private UserRepository mUserRepository;
    private Api mApi;

    @Before
    public void setUp() throws Exception {
        mApi = mock(Api.class);
        mUserRepository = mock(UserRepository.class);
        loginPresenter = new LoginPresenter(mUserRepository, mApi);
        loginView = mock(LoginView.class);

    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void attachView() throws Exception {
        //given
        //when
        loginPresenter.attachView(loginView);
        //then
        assertThat(loginPresenter.getView(), is(loginView));

    }

    @Test
    public void detachView() throws Exception {
        //given
        loginPresenter.attachView(loginView);
        //when

        loginPresenter.detachView();
        //then
        assertNull(loginPresenter.getView());

    }

    @Test
    public void notAcceptEmptyUsername() throws Exception {
        //given
        loginPresenter.attachView(loginView);
        //when
        loginPresenter.login("", "");
        //then
        verify(loginView).showLoginError(" Login can't be empty");


    }

    @Test
    public void notAcceptEmptyPassword() throws Exception {
        //given
        loginPresenter.attachView(loginView);
        //when
        loginPresenter.login("", "");
        //then
        verify(loginView).showPasswordError(R.string.empty_password);
    }

    @Test
    public void goToLoginWhenNotLogged() throws Exception {
        //given
        loginPresenter.attachView(loginView);

        when(mUserRepository.isNotLogged()).thenReturn(true);
        //when
        loginPresenter.checkIsLogged();
        //then
        verify(loginView).goToLogin();

    }

    @Test
    public void storeUserTokenOnLogin() throws Exception {
        //given
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.token = "TOKEN";

        when(mApi.getLogin("test", "test")).thenReturn(loginResponse);


        //when
        loginPresenter.login("test", "test");
        //then
        verify(mUserRepository).storeToken("TOKEN");

    }
}