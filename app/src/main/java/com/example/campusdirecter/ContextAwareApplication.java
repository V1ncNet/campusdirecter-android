package com.example.campusdirecter;

import android.app.Application;
import android.content.Context;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class ContextAwareApplication extends Application {

    private static ContextAwareApplication instance;

    public static synchronized ContextAwareApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
