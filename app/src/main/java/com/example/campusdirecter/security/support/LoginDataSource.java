package com.example.campusdirecter.security.support;

import androidx.annotation.NonNull;

import com.example.campusdirecter.ServiceLocator;
import com.example.campusdirecter.http.AbstractHttpRequest;
import com.example.campusdirecter.http.HttpClient;
import com.example.campusdirecter.http.HttpResponse;
import com.example.campusdirecter.security.model.LoggedInUser;
import com.example.campusdirecter.security.model.LoginResultCallback;

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
public class LoginDataSource {

    private final HttpClient client = ServiceLocator.getInstance().getHttpClient();

    public void login(String username, String password, LoginResultCallback callback) {
        AbstractHttpRequest request = createRequest(username, password);
        client.post(request, new LoginCallbackAdapter(username, callback));
    }

    @NonNull
    private AbstractHttpRequest createRequest(String username, String password) {
        Map<String, String> credentials = new HashMap<>(2);
        credentials.put("username", username);
        credentials.put("password", password);

        URL url = loginUrl();
        JSONObject json = new JSONObject(credentials);
        return new AbstractHttpRequest(url, json) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>(super.getHeaders());
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
    }

    @SneakyThrows
    private URL loginUrl() {
        return new URL("https://srv-dev01.campusdirecter.vinado.de/login");
    }

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
}