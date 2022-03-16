package com.example.campusdirecter.util;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
        throw new UnsupportedOperationException();
    }

    public static <R> R rethrow(Throwable throwable) {
        return ExceptionUtils.sneakyThrows(throwable);
    }

    @SuppressWarnings("unchecked")
    private static <R, T extends Throwable> R sneakyThrows(final Throwable throwable) throws T {
        throw (T) throwable;
    }
}
