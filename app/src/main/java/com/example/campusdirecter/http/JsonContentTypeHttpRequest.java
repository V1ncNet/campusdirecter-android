package com.example.campusdirecter.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
class JsonContentTypeHttpRequest extends HttpRequestDecorator implements HttpRequest {

    public JsonContentTypeHttpRequest(HttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>(super.getHeaders());
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
