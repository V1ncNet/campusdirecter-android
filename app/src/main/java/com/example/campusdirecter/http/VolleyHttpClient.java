package com.example.campusdirecter.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * @author jureao
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class VolleyHttpClient implements HttpClient {

    private static VolleyHttpClient instance;
    private static Context context;

    private RequestQueue queue;

    public VolleyHttpClient(Context context) {
        VolleyHttpClient.context = context;
    }

    protected final RequestQueue getRequestQueue() {
        if (null == queue) {
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

    public static synchronized VolleyHttpClient getInstance(Context context) {
        if (null == instance) {
            instance = new VolleyHttpClient(context);
        }
        return instance;
    }

    @Override
    public void get(HttpRequest request, HttpResponse response) {
        JsonObjectRequest volleyRequest = request(Method.GET, request, response);
        getRequestQueue().add(volleyRequest);
    }

    private JsonObjectRequest request(int method, HttpRequest request, HttpResponse response) {
        return new JsonObjectRequest(method,
                request.getUrl().toString(), request.getBody(),
                response::onSuccess,
                response::onError) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return request.getHeaders();
            }
        };
    }
}
