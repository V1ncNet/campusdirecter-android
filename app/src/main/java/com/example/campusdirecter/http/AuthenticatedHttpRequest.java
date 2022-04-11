package com.example.campusdirecter.http;

import com.example.campusdirecter.security.model.LoginRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class AuthenticatedHttpRequest extends HttpRequestDecorator implements HttpRequest {

    private final LoginRepository loginRepository;

    public AuthenticatedHttpRequest(HttpRequest delegate, LoginRepository loginRepository) {
        super(delegate);
        this.loginRepository = loginRepository;
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>(super.getHeaders());
        headers.put("Authorization", "Bearer " + loginRepository.getUser().getToken());
        return headers;
    }
}
