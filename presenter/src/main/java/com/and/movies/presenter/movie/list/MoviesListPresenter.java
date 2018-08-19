package com.and.movies.presenter.movie.list;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.domain.repo.movie.MoviesSortOrder;
import com.and.movies.domain.usecase.GetMovieListUseCase;
import com.and.movies.domain.usecase.resource.ResourceStatus;
import com.and.movies.presenter.base.BasePresenter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MoviesListPresenter extends BasePresenter<MoviesListView> {
    @NonNull
    private final GetMovieListUseCase mGetMovieListUseCase;
    @NonNull
    private final MoviesListViewState mMoviesListViewState;

    @Inject
    public MoviesListPresenter(@NonNull final MoviesListView moviesListView,
                               @NonNull final GetMovieListUseCase getMovieListUseCase,
                               @NonNull final MoviesListViewState moviesListViewState) {
        super(moviesListView);
        mGetMovieListUseCase = getMovieListUseCase;
        mMoviesListViewState = moviesListViewState;
    }

    @Override
    public void init() {
        super.init();

        performViewAction(view -> {
            view.setViewState(mMoviesListViewState);
        });
    }

    @UiThread
    public void loadMoviesList() {
        final GetMovieListUseCase.Request request = GetMovieListUseCase.Request.create(MoviesSortOrder.MOST_POPULAR);
        mGetMovieListUseCase.execute(request, resource -> {
            switch (resource.getStatus()) {
                case ResourceStatus.FAILED:
                    performViewAction(view -> {
                        mMoviesListViewState.setIsLoadingMovieInfoList(false);
                        view.showErrorMessage("Error message");
                    });
                    break;
                case ResourceStatus.IN_PROGRESS:
                    performViewAction(view -> {
                        mMoviesListViewState.setIsLoadingMovieInfoList(true);
                    });
                    break;
                case ResourceStatus.SUCCEED:
                    performViewAction(view -> {
                        mMoviesListViewState.setIsLoadingMovieInfoList(false);
                        final List<? extends MovieInfo> result = resource.getResult();
                        if (result != null) {
                            mMoviesListViewState.setMovieInfoList(result);
                        } else {
                            mMoviesListViewState.setMovieInfoList(Collections.emptyList());
                        }
                    });
                    break;
            }

        });
    }
}
