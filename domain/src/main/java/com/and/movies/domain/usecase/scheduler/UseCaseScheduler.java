package com.and.movies.domain.usecase.scheduler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.and.movies.domain.usecase.base.BaseUseCase;
import com.and.movies.domain.usecase.resource.ResponseListener;

public interface UseCaseScheduler {
    <Request, Response> void execute(@NonNull final BaseUseCase<Request, Response> useCase,
                                     @Nullable final Request request,
                                     @Nullable final ResponseListener<Response> responseListener);
}
