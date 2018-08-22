package com.and.movies.presenter.movie.list;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.domain.repo.movie.MoviesSortOrder;
import com.and.movies.domain.usecase.resource.ResponseListener;
import com.and.movies.presenter.base.ViewState;

import java.util.List;

public class MoviesListViewState extends ViewModel implements ViewState {
    @NonNull
    private final ObservableList<MovieInfo> mMovieInfoList;
    @NonNull
    private final ObservableBoolean mIsLoadingMovieInfoList;
    @NonNull
    private final ObservableInt mMoviesSortOrder;
    @Nullable
    private ResponseListener<List<? extends MovieInfo>> mGetMovieListListener;

    public MoviesListViewState() {
        mMovieInfoList = new ObservableArrayList<>();
        mIsLoadingMovieInfoList = new ObservableBoolean();
        mMoviesSortOrder = new ObservableInt();
    }

    @NonNull
    public ObservableList<MovieInfo> getMovieInfoList() {
        return mMovieInfoList;
    }

    @NonNull
    public ObservableBoolean getIsLoadingMovieInfoList() {
        return mIsLoadingMovieInfoList;
    }

    @NonNull
    public ObservableInt getMoviesSortOrder() {
        return mMoviesSortOrder;
    }

    public void setMovieInfoList(@NonNull final List<? extends MovieInfo> movieInfoList) {
        mMovieInfoList.clear();
        mMovieInfoList.addAll(movieInfoList);
    }

    public void setIsLoadingMovieInfoList(final boolean isLoadingMovieInfoList) {
        mIsLoadingMovieInfoList.set(isLoadingMovieInfoList);
    }

    public void setMovieSortOrder(@MoviesSortOrder final int movieSortOrder) {
        mMoviesSortOrder.set(movieSortOrder);
    }

    @Nullable
    ResponseListener<List<? extends MovieInfo>> getGetMovieListListener() {
        return mGetMovieListListener;
    }

    void setGetMovieListListener(@Nullable final ResponseListener<List<? extends MovieInfo>>
                                         getMovieListListener) {
        mGetMovieListListener = getMovieListListener;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (mGetMovieListListener != null) {
            mGetMovieListListener.cancel();
            mGetMovieListListener = null;
        }
    }
}
