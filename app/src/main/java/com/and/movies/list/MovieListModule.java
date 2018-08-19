package com.and.movies.list;

import android.arch.lifecycle.ViewModelProviders;

import com.and.movies.presenter.movie.list.MoviesListView;
import com.and.movies.presenter.movie.list.MoviesListViewState;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieListModule {
    @ContributesAndroidInjector
    abstract MovieListActivity movieListActivity();

    @Binds
    abstract MoviesListView moviesListView(MovieListActivity movieListActivity);

    @Provides
    static MoviesListViewState moviesListViewState(MovieListActivity movieListActivity) {
        return ViewModelProviders.of(movieListActivity).get(MoviesListViewState.class);
    }
}
