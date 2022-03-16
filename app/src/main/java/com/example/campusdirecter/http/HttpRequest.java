package com.example.campusdirecter.http;

import org.json.JSONObject;

import java.net.URL;
import java.util.Collections;
import java.util.Map;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@FunctionalInterface
public interface HttpRequest {

    URL getUrl();

    default JSONObject getBody() {
        return null;
    }

    default Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }
}
