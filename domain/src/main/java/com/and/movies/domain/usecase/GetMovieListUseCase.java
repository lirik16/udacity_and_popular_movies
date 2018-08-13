package com.and.movies.domain.usecase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.and.movies.domain.repo.error.RepoException;
import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.domain.repo.movie.MovieRepo;
import com.and.movies.domain.repo.movie.MoviesSortOrder;
import com.and.movies.domain.usecase.base.BaseUseCase;
import com.and.movies.domain.usecase.base.UseCaseDuration;
import com.and.movies.domain.usecase.resource.Resource;
import com.and.movies.domain.usecase.resource.ResponseListener;
import com.and.movies.domain.usecase.scheduler.UseCaseScheduler;
import com.google.auto.value.AutoValue;

import java.util.List;

public class GetMovieListUseCase extends BaseUseCase<GetMovieListUseCase.Request, List<? extends MovieInfo>> {
    @NonNull
    private final MovieRepo mMovieRepo;

    protected GetMovieListUseCase(@NonNull final UseCaseScheduler useCaseScheduler,
                                  @NonNull final MovieRepo movieRepo) {
        super(useCaseScheduler, UseCaseDuration.LONG_RUNNING);
        mMovieRepo = movieRepo;
    }

    @Override
    @WorkerThread
    public void onExecute(@Nullable final Request request,
                          @NonNull final ResponseListener<List<? extends MovieInfo>> responseListener) {
        if (request == null) {
            responseListener.onResponse(Resource.failed(new IllegalArgumentException("Request is null")));
            return;
        }
        responseListener.onResponse(Resource.inProgress());

        try {
            final List<? extends MovieInfo> movies = mMovieRepo.getMovies(request.getSortOrder());
            responseListener.onResponse(Resource.succeed(movies));
        } catch (RepoException | IllegalArgumentException e) {
            responseListener.onResponse(Resource.failed(e));
        }
    }


    @AutoValue
    public abstract static class Request {
        public static Request create(@MoviesSortOrder final int sortOrder) {
            return new AutoValue_GetMovieListUseCase_Request(sortOrder);
        }

        @MoviesSortOrder
        abstract int getSortOrder();

    }
}
