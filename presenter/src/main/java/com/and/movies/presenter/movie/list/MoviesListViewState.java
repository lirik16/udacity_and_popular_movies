package com.and.movies.presenter.movie.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.presenter.base.ViewState;

import java.util.List;

public class MoviesListViewState extends ViewModel implements ViewState {
    @NonNull
    private final ObservableList<MovieInfo> mMovieInfoList = new ObservableArrayList<>();
    @NonNull
    private final ObservableBoolean mIsLoadingMovieInfoList = new ObservableBoolean();

    @NonNull
    public ObservableList<MovieInfo> getMovieInfoList() {
        return mMovieInfoList;
    }

    @NonNull
    public ObservableBoolean getIsLoadingMovieInfoList() {
        return mIsLoadingMovieInfoList;
    }

    public void setMovieInfoList(@NonNull final List<? extends MovieInfo> movieInfoList) {
        mMovieInfoList.clear();
        mMovieInfoList.addAll(movieInfoList);
    }

    public void setIsLoadingMovieInfoList(final boolean isLoadingMovieInfoList) {
        mIsLoadingMovieInfoList.set(isLoadingMovieInfoList);
    }
}
