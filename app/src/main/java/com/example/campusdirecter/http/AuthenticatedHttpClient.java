package com.example.campusdirecter.http;

import androidx.annotation.NonNull;

import com.example.campusdirecter.security.model.LoginRepository;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class AuthenticatedHttpClient extends HttpClientDecorator implements HttpClient {

    private final LoginRepository loginRepository;

    public AuthenticatedHttpClient(HttpClient delegate, LoginRepository loginRepository) {
        super(delegate);
        this.loginRepository = loginRepository;
    }

    @Override
    public void get(HttpRequest request, HttpResponse response) {
        super.get(authenticatedRequest(request), response);
    }

    @Override
    public void post(HttpRequest request, HttpResponse response) {
        super.post(authenticatedRequest(request), response);
    }

    @NonNull
    private AuthenticatedHttpRequest authenticatedRequest(HttpRequest request) {
        return new AuthenticatedHttpRequest(request, loginRepository);
    }
}
