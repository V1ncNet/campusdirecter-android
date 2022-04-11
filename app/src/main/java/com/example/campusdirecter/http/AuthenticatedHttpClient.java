package com.example.campusdirecter.http;

import androidx.annotation.NonNull;

import java.util.function.Supplier;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class AuthenticatedHttpClient extends HttpClientDecorator implements HttpClient {

    private final Supplier<String> tokenProvider;

    public AuthenticatedHttpClient(HttpClient delegate, Supplier<String> tokenProvider) {
        super(delegate);
        this.tokenProvider = tokenProvider;
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
        return new AuthenticatedHttpRequest(request, tokenProvider);
    }
}
