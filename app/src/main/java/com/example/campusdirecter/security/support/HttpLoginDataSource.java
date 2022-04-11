package com.example.campusdirecter.security.support;

import androidx.annotation.NonNull;

import com.example.campusdirecter.http.AbstractHttpRequest;
import com.example.campusdirecter.http.AuthenticatedHttpRequest;
import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.HttpRequest;
import com.example.campusdirecter.http.HttpResponse;
import com.example.campusdirecter.security.model.LoggedInUser;
import com.example.campusdirecter.security.model.LoginDataSource;
import com.example.campusdirecter.security.model.LoginResultCallback;
import com.example.campusdirecter.security.model.TokenResultCallback;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
@RequiredArgsConstructor
public class HttpLoginDataSource implements LoginDataSource {

    private final HttpClient client;

    @Override
    public void login(String username, String password, LoginResultCallback callback) {
        AbstractHttpRequest request = createRequest(username, password);
        client.post(request, new LoginCallbackAdapter(username, callback));
    }

    @Override
    public void login(String token, TokenResultCallback callback) {
        HttpRequest request = createRequest(token);
        client.get(request, new TokenCallbackAdapter(token, callback));
    }

    @NonNull
    private AbstractHttpRequest createRequest(String username, String password) {
        Map<String, String> credentials = new HashMap<>(2);
        credentials.put("username", username);
        credentials.put("password", password);

        URL url = loginUrl();
        JSONObject json = new JSONObject(credentials);
        return new AbstractHttpRequest(url, json) {
        };
    }

    private HttpRequest createRequest(String token) {
        HttpRequest request = HttpRequest.sneaky(() -> new URL("https://srv-dev01.campusdirecter.vinado.de/me"));
        return new AuthenticatedHttpRequest(request, () -> token);
    }

    @SneakyThrows
    private URL loginUrl() {
        return new URL("https://srv-dev01.campusdirecter.vinado.de/login");
    }

    @Override
    public void logout() {
        // TODO: revoke authentication
    }


    @RequiredArgsConstructor
    private static final class LoginCallbackAdapter implements HttpResponse {

        private final String userId;
        private final LoginResultCallback callback;

        @SneakyThrows
        @Override
        public void onSuccess(JSONObject response) {
            String token = response.getString("token");

            LoggedInUser user = new LoggedInUser(userId, token);
            Result.Success<LoggedInUser> result = new Result.Success<>(user);
            callback.onResponse(result);
        }

        @Override
        public void onError(Throwable error) {
            callback.onError(new Result.Error(new IOException("Error logging in", error)));
        }
    }

    @RequiredArgsConstructor
    private static final class TokenCallbackAdapter implements HttpResponse {

        private final String token;
        private final TokenResultCallback callback;

        @SneakyThrows
        @Override
        public void onSuccess(JSONObject response) {
            String userId = response.getString("username");

            LoggedInUser user = new LoggedInUser(userId, token);
            Result.Success<LoggedInUser> result = new Result.Success<>(user);
            callback.onOk(result);
        }

        @Override
        public void onError(Throwable error) {
            callback.onUnauthorized(new Result.Error(new IOException("Unauthorized", error)));
        }
    }
}