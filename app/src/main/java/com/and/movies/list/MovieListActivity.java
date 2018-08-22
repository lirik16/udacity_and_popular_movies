package com.and.movies.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;

import com.and.movies.R;
import com.and.movies.base.BaseActivity;
import com.and.movies.databinding.MovieListActivityBinding;
import com.and.movies.domain.repo.movie.MovieInfo;
import com.and.movies.presenter.movie.list.MoviesListPresenter;
import com.and.movies.presenter.movie.list.MoviesListView;
import com.and.movies.presenter.movie.list.MoviesListViewState;

import javax.inject.Inject;

public class MovieListActivity extends BaseActivity implements MoviesListView {
    @Inject
    @NonNull
    MoviesListPresenter mMoviesListPresenter;
    @NonNull
    private MovieListActivityBinding mMovieListActivityBinding;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieListActivityBinding = DataBindingUtil.setContentView(this, R.layout.movie_list_activity);

        setSupportActionBar(mMovieListActivityBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mMovieListActivityBinding.itemList.setAdapter(new MovieListAdapter());
        mMovieListActivityBinding.itemList.setLayoutManager(new GridLayoutManager(this, 2));
        mMoviesListPresenter.init();

        mMoviesListPresenter.loadMoviesList(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMoviesListPresenter.clear();
    }

    @Override
    public void showErrorMessage(@NonNull final String errorMessage) {
        Snackbar.make(mMovieListActivityBinding.getRoot(), errorMessage, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void openMovieDetail(@NonNull final MovieInfo movieInfo) {

    }

    @Override
    public void setViewState(@NonNull final MoviesListViewState viewState) {
        mMovieListActivityBinding.setViewState(viewState);
        //TODO: mb databinding should depend on presenter interface
        mMovieListActivityBinding.setPresenter(mMoviesListPresenter);
    }
}
