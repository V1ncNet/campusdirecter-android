package com.example.campusdirecter.http;

import org.json.JSONObject;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public abstract class AbstractHttpRequest implements HttpRequest {

    private final URL url;
    private final JSONObject body;
    private final Map<String, String> headers = new HashMap<>();

    public AbstractHttpRequest(URL url) {
        this(url, null);
    }

    public AbstractHttpRequest(URL url, JSONObject body) {
        this.url = url;
        this.body = body;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public JSONObject getBody() {
        return body;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public void addHeader(String name, Object value) {
        headers.put(name, Objects.toString(value));
    }

    public void removeHeader(String name) {
        headers.remove(name);
    }
}
