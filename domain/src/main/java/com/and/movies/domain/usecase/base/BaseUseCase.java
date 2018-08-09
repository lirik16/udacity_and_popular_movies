package com.and.movies.domain.usecase.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import com.and.movies.domain.usecase.resource.ResponseListener;
import com.and.movies.domain.usecase.scheduler.UseCaseScheduler;

public abstract class BaseUseCase<Request, Response> {

    @NonNull
    private final UseCaseScheduler mUseCaseScheduler;
    @UseCaseDuration
    private final int mUseCaseType;

    protected BaseUseCase(@NonNull final UseCaseScheduler useCaseScheduler,
                          @UseCaseDuration final int useCaseType) {
        mUseCaseScheduler = useCaseScheduler;
        mUseCaseType = useCaseType;
    }

    @UiThread
    public void execute(@Nullable final Request request,
                        @Nullable final ResponseListener<Response> responseListener) {
        mUseCaseScheduler.execute(this, request, responseListener);
    }

    @WorkerThread
    abstract public void onExecute(@Nullable final Request request,
                                   @NonNull final ResponseListener<Response> responseListener);

    @UseCaseDuration
    public int getUseCaseType() {
        return mUseCaseType;
    }
}
