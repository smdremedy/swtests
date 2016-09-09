package com.soldiersofmobile.logintest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.usernameET)
    EditText mUsernameET;
    @BindView(R.id.passwordET)
    EditText mPasswordET;
    @BindView(R.id.signInButton)
    Button mSignInButton;
    @BindView(R.id.activity_login)
    LinearLayout mActivityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.sAppComponent.inject(this);
        mLoginPresenter.attachView(this);

        mLoginPresenter.checkIsLogged();


        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }

    @OnClick(R.id.signInButton)
    public void onClick() {
        String username = mUsernameET.getText().toString();
        String password = mUsernameET.getText().toString();

        mLoginPresenter.login(username, password);
    }

    @Override
    public void showLoginError(String error) {
        mUsernameET.setError(error);
    }

    @Override
    public void showPasswordError(int errorResource) {

        mPasswordET.setError(getString(errorResource));
    }

    @Override
    public void goToLogin() {

        Toast.makeText(this, "GO TO Login", Toast.LENGTH_SHORT).show();
        //startActivity()
    }
}
