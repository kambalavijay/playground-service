package com.example.testvalidation;

import java.util.concurrent.CompletableFuture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public final class HttpUtil {
    private HttpUtil() throws IllegalAccessException {
        throw new IllegalAccessException("HttpUtil class must not be instantiated");
    }

    public static <T> CompletableFuture<T> toCompletableFuture(Call<T> call) {
        CompletableFuture<T> cf = new CompletableFuture();
        call.enqueue(convertToCallback(cf));
        return cf;
    }

    private static <T> Callback<T> convertToCallback(final CompletableFuture<T> cf) {
        return new Callback<T>() {
            @EverythingIsNonNull
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    cf.complete(response.body());
                } else {
                    int httpStatus = response.code();
                    String errorBody = HttpUtil.getErrorResponseString(response);
                    cf.completeExceptionally(new HttpApiException(httpStatus, errorBody));
                }

            }

            @EverythingIsNonNull
            public void onFailure(Call<T> call, Throwable throwable) {
                cf.completeExceptionally(new HttpApiException("Request processing failed", throwable));
            }
        };
    }

    private static <T> String getErrorResponseString(Response<T> response) {
        if (!response.isSuccessful() && response.errorBody() != null) {
            try {
                return response.errorBody().string();
            } catch (Exception var2) {
                return null;
            }
        } else {
            return null;
        }
    }
}
