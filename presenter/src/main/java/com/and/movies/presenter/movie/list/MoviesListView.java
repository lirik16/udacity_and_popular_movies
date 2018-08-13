package com.and.movies.presenter.movie.list;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.presenter.base.BaseView;

public interface MoviesListView extends BaseView<MoviesListViewState> {
    @UiThread
    void showErrorMessage(@NonNull final String errorMessage);

    @UiThread
    void openMovieDetail(@NonNull final MovieInfo movieInfo);
}
