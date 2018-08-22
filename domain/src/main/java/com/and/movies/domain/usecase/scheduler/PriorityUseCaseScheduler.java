package com.and.movies.domain.usecase.scheduler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.and.movies.domain.thread.ThreadUtil;
import com.and.movies.domain.usecase.base.BaseUseCase;
import com.and.movies.domain.usecase.base.UseCaseDuration;
import com.and.movies.domain.usecase.resource.Resource;
import com.and.movies.domain.usecase.resource.ResponseListener;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class PriorityUseCaseScheduler implements UseCaseScheduler {
    @NonNull
    private final Executor mUIThread;
    @NonNull
    private final Executor mShortRunning;
    @NonNull
    private final Executor mLongRunning;

    @Inject
    public PriorityUseCaseScheduler(@Named(ThreadUtil.UI_THREAD) @NonNull final Executor uiThread,
                                    @Named(ThreadUtil.SHORT_TASK_THREAD) @NonNull final Executor shortRunning,
                                    @Named(ThreadUtil.LONG_TASK_THREAD) @NonNull final Executor longRunning) {
        mUIThread = uiThread;
        mShortRunning = shortRunning;
        mLongRunning = longRunning;
    }

    @Override
    public <Request, Response> void execute(@NonNull final BaseUseCase<Request, Response> useCase,
                                            @Nullable final Request request,
                                            @Nullable final ResponseListener<Response> responseListener) {
        final Runnable runnable = () -> {
            useCase.onExecute(request, new ResponseListener<Response>() {
                @Override
                public void onResponse(@NonNull final Resource<Response> resource) {
                    if (responseListener != null) {
                        mUIThread.execute(() -> responseListener.response(resource));
                    }
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
