package com.soldiersofmobile.logintest;


public class LoginPresenter {

    private LoginView view;
    private UserRepository userRepository;
    private final Api mApi;

    public LoginPresenter(UserRepository userRepository, Api api) {
        this.userRepository = userRepository;
        mApi = api;
    }

    public void attachView(LoginView loginView) {

        view = loginView;
    }


    public LoginView getView() {
        return view;
    }

    public void detachView() {
        view = null;

    }

    public void login(String username, String password) {

        if (username.isEmpty()) {
            view.showLoginError("Login can't be empty");
        }
        if (password.isEmpty()) {
            view.showPasswordError(R.string.empty_password);
        }
        LoginResponse login = mApi.getLogin(username, password);
        if(login != null) {
            userRepository.storeToken(login.token);
        }
    }

    public void checkIsLogged() {

        if (userRepository.isNotLogged()) {
            view.goToLogin();
        }

    }
}
