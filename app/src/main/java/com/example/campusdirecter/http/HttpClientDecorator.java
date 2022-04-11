package com.example.campusdirecter.http;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
@RequiredArgsConstructor
public abstract class HttpClientDecorator implements HttpClient {

    @Getter
    @Delegate
    private final HttpClient delegate;
}
