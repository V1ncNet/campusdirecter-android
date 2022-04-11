package com.example.campusdirecter.security.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.campusdirecter.R;
import com.example.campusdirecter.security.model.LoggedInUser;
import com.example.campusdirecter.security.model.LoginRepository;
import com.example.campusdirecter.security.model.LoginResultCallback;
import com.example.campusdirecter.security.support.Result;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        loginRepository.login(username, password, new LoginResultCallback() {
            @Override
            public void onResponse(Result<LoggedInUser> result) {
                LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
                loginResult.setValue(new LoginResult(new LoggedInUserView(data.getToken())));
            }

            @Override
            public void onError(Result<Void> result) {
                LoginResultCallback.super.onError(result);
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }

        return !username.trim().isEmpty();
    }
}