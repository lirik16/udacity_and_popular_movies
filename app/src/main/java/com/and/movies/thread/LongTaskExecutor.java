package com.and.movies.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LongTaskExecutor implements Executor {
    @NonNull
    private final Executor mExecutor;

    @Inject
    public LongTaskExecutor() {
        mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void execute(@NonNull final Runnable command) {
        mExecutor.execute(command);
    }
}
