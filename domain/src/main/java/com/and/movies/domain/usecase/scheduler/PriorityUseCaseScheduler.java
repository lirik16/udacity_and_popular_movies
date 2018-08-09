package com.and.movies.domain.usecase.scheduler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.and.movies.domain.usecase.base.BaseUseCase;
import com.and.movies.domain.usecase.base.UseCaseDuration;
import com.and.movies.domain.usecase.resource.ResponseListener;

import java.util.concurrent.Executor;

public class PriorityUseCaseScheduler implements UseCaseScheduler {
    @NonNull
    private final Executor mUIThread;
    @NonNull
    private final Executor mShortRunning;
    @NonNull
    private final Executor mLongRunning;

    public PriorityUseCaseScheduler(@NonNull final Executor uiThread,
                                    @NonNull final Executor shortRunning,
                                    @NonNull final Executor longRunning) {
        mUIThread = uiThread;
        mShortRunning = shortRunning;
        mLongRunning = longRunning;
    }

    @Override
    public <Request, Response> void execute(@NonNull final BaseUseCase<Request, Response> useCase,
                                            @Nullable final Request request,
                                            @Nullable final ResponseListener<Response> responseListener) {
        final Runnable runnable = () -> {
            useCase.onExecute(request, resource -> {
                if (responseListener != null) {
                    mUIThread.execute(() -> responseListener.onResponse(resource));
                }
            });
        };

        switch (useCase.getUseCaseType()) {
            case UseCaseDuration.LONG_RUNNING:
                mLongRunning.execute(runnable);
                break;
            case UseCaseDuration.SHORT_RUNNING:
                mShortRunning.execute(runnable);
                break;
        }
    }
}
