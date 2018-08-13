package com.and.movies.domain.usecase.resource;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Resource<Result> {
    @ResourceStatus
    private final int mStatus;
    @Nullable
    private final Result mResult;
    @Nullable
    private final Exception mException;

    public static <Result> Resource<Result> inProgress() {
        return new Resource<Result>(ResourceStatus.IN_PROGRESS, null, null);
    }

     public static <Result> Resource<Result> succeed(@Nullable final Result result) {
        return new Resource<Result>(ResourceStatus.SUCCEED, result, null);
    }

    public static <Result> Resource<Result> failed(@NonNull final Exception exception) {
        return new Resource<Result>(ResourceStatus.FAILED, null, exception);
    }

    private Resource(final int status,
                    @Nullable final Result result,
                    @Nullable final Exception exception) {
        mStatus = status;
        mResult = result;
        mException = exception;
    }

    @ResourceStatus
    public int getStatus() {
        return mStatus;
    }

    @Nullable
    public Result getResult() {
        return mResult;
    }

    @Nullable
    public Exception getException() {
        return mException;
    }
}
