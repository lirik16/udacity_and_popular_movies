package com.and.movies.domain.usecase.resource;

import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ResponseListener<Response> {
    @NonNull
    private final AtomicBoolean mIsCanceled = new AtomicBoolean(false);

    public final void response(@NonNull final Resource<Response> resource) {
        if (!isCanceled()) {
            onResponse(resource);
        }
    }

    @UiThread
    public void cancel() {
        mIsCanceled.set(true);
    }

    @AnyThread
    public boolean isCanceled() {
        return mIsCanceled.get();
    }

    @UiThread
    public abstract void onResponse(@NonNull final Resource<Response> resource);
}
