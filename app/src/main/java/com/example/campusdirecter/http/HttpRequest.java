package com.example.campusdirecter.http;

import com.example.campusdirecter.util.ExceptionUtils;

import org.json.JSONObject;

import java.net.MalformedURLException;
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

    static HttpRequest sneaky(Throwing constructor) {
        return () -> {
            try {
                return constructor.getUrl();
            } catch (Throwable e) {
                return ExceptionUtils.rethrow(e);
            }
        };
    }


    @FunctionalInterface
    interface Throwing {

        URL getUrl() throws MalformedURLException;
    }
}
