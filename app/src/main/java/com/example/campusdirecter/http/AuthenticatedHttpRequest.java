package com.example.campusdirecter.http;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class AuthenticatedHttpRequest extends HttpRequestDecorator implements HttpRequest {

    private final Supplier<String> tokenProvider;

    public AuthenticatedHttpRequest(HttpRequest delegate, Supplier<String> tokenProvider) {
        super(delegate);
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>(super.getHeaders());
        headers.put("Authorization", "Bearer " + tokenProvider.get());
        return headers;
    }
}
