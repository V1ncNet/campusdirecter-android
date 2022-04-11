package com.example.campusdirecter.security.model;

import com.example.campusdirecter.security.support.LoginDataSource;
import com.example.campusdirecter.security.support.Result;

import lombok.RequiredArgsConstructor;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void login(String username, String password, LoginResultCallback callback) {
        dataSource.login(username, password, new UserSettingLoginResultCallbackDecorator(callback));
    }


    @RequiredArgsConstructor
    private final class UserSettingLoginResultCallbackDecorator implements LoginResultCallback {

        private final LoginResultCallback delegate;

        @Override
        public void onResponse(Result<LoggedInUser> result) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
            delegate.onResponse(result);
        }

        @Override
        public void onError(Result<Void> result) {
            delegate.onError(result);
        }
    }
}