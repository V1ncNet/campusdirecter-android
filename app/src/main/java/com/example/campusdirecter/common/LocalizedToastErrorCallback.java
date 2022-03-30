package com.example.campusdirecter.common;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.campusdirecter.ContextAwareApplication;

import lombok.RequiredArgsConstructor;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public class LocalizedToastErrorCallback implements ErrorCallback {

    private final @StringRes int resId;

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(ContextAwareApplication.getContext(), resId, Toast.LENGTH_LONG)
                .show();
    }
}
