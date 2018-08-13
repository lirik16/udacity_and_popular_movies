package com.and.movies.presenter.movie.list;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.domain.repo.movie.MoviesSortOrder;
import com.and.movies.domain.usecase.GetMovieListUseCase;
import com.and.movies.domain.usecase.resource.ResourceStatus;
import com.and.movies.presenter.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

public class MoviesListPresenter extends BasePresenter<MoviesListView> {
    @NonNull
    private final GetMovieListUseCase mGetMovieListUseCase;
    @NonNull
    private final MoviesListViewState mMoviesListViewState;

    public MoviesListPresenter(@NonNull final WeakReference<MoviesListView> view,
                               @NonNull final GetMovieListUseCase getMovieListUseCase,
                               @NonNull final MoviesListViewState moviesListViewState) {
        super(view);
        mGetMovieListUseCase = getMovieListUseCase;
        mMoviesListViewState = moviesListViewState;
    }

    @UiThread
    public void loadMoviesList() {
        final GetMovieListUseCase.Request request = GetMovieListUseCase.Request.create(MoviesSortOrder.MOST_POPULAR);
        mGetMovieListUseCase.execute(request, resource -> {
            switch (resource.getStatus()) {
                case ResourceStatus.FAILED:
                    mMoviesListViewState.setIsLoadingMovieInfoList(false);
                    performViewAction(view -> {
                        //TODO: replace stub message
                        view.showErrorMessage("Error message");
                    });
                    break;
                case ResourceStatus.IN_PROGRESS:
                    mMoviesListViewState.setIsLoadingMovieInfoList(true);
                    break;
                case ResourceStatus.SUCCEED:
                    mMoviesListViewState.setIsLoadingMovieInfoList(false);
                    final List<? extends MovieInfo> result = resource.getResult();
                    if (result != null) {
                        mMoviesListViewState.setMovieInfoList(result);
                    } else {
                        mMoviesListViewState.setMovieInfoList(Collections.emptyList());
                    }
                    break;
            }

        });
    }
}
