package com.example.campusdirecter.common;

import android.widget.Toast;

import com.example.campusdirecter.ContextAwareApplication;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class ToastErrorCallback implements ErrorCallback {

    private final String message;

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(ContextAwareApplication.getContext(), message, Toast.LENGTH_LONG)
                .show();
    }
}
